package org.dreamcat.graphetl.common.util;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.dreamcat.common.io.IOUtil;
import org.dreamcat.common.util.ObjectUtil;
import org.dreamcat.graphetl.common.Env;

/**
 * @author Jerry Will
 * @version 2022-10-09
 */
@Slf4j
public class HdfsUtil {

    private final static Configuration conf = new Configuration();

    static {
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());

        if (ObjectUtil.isNotBlank(Env.CORE_SITE_FILE_PATH)) {
            conf.addResource(new Path(Env.CORE_SITE_FILE_PATH));
        }
        if (ObjectUtil.isNotBlank(Env.HDFS_SITE_FILE_PATH)) {
            conf.addResource(new Path(Env.HDFS_SITE_FILE_PATH));
        }
    }

    public static boolean mkdir(String hdfsDir) throws IOException {
        FileSystem hdfs = FileSystem.get(URI.create(hdfsDir), conf);
        return hdfs.mkdirs(new Path(hdfsDir));
    }

    public static boolean rmdir(String hdfsDir) throws IOException {
        FileSystem hdfs = FileSystem.get(URI.create(hdfsDir), conf);
        return hdfs.delete(new Path(hdfsDir), true);
    }

    public static boolean exists(String hdfsPath) throws IOException {
        FileSystem hdfs = FileSystem.get(URI.create(hdfsPath), conf);
        return hdfs.exists(new Path(hdfsPath));
    }

    public static boolean isDir(String hdfsPath) throws IOException {
        FileSystem hdfs = FileSystem.get(conf);
        FileStatus status = hdfs.getFileStatus(new Path(hdfsPath));
        return status.isDirectory();
    }

    public static long getSize(String hdfsPath) throws IOException {
        FileSystem hdfs = FileSystem.get(conf);
        FileStatus[] statuses = hdfs.listStatus(new Path(hdfsPath));
        long size = 0;
        for (FileStatus status : statuses) {
            size += status.getLen();
        }
        return size;
    }

    public static void touch(String hdfsFile) throws IOException {
        FileSystem hdfs = FileSystem.get(conf);
        Path path = new Path(hdfsFile);
        if (!hdfs.exists(path)) {
            FSDataOutputStream out = hdfs.create(new Path(hdfsFile), false);
            out.close();
        }
    }

    public static String readAsString(String hdfsFile) throws IOException {
        FileSystem hdfs = FileSystem.get(conf);
        try (FSDataInputStream in = hdfs.open(new Path(hdfsFile))) {
            return IOUtil.readAsString(in, StandardCharsets.UTF_8);
        }
    }

    public static void upload(String localFile, String hdfsFile, boolean overwrite) throws IOException {
        upload(localFile, hdfsFile, overwrite, false);
    }

    public static void uploadAndDelSrc(String localFile, String hdfsFile, boolean overwrite) throws IOException {
        upload(localFile, hdfsFile, overwrite, true);
    }

    private static void upload(String localFile, String hdfsFile, boolean overwrite, boolean delSrc)
            throws IOException {
        FileSystem hdfs = FileSystem.get(conf);
        hdfs.copyFromLocalFile(delSrc, overwrite, new Path(localFile), new Path(hdfsFile));
    }

    public static void download(String hdfsFile, String localFile) throws IOException {
        download(hdfsFile, localFile, false);
    }

    public static void downloadAndDelSrc(String hdfsFile, String localFile) throws IOException {
        download(hdfsFile, localFile, true);
    }

    private static void download(String hdfsFile, String localFile, boolean delSrc) throws IOException {
        FileSystem hdfs = FileSystem.get(conf);
        hdfs.copyToLocalFile(delSrc, new Path(hdfsFile), new Path(localFile));
    }
}