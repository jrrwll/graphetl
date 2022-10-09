package org.dreamcat.graphetl.common;

import lombok.extern.slf4j.Slf4j;
import org.dreamcat.common.util.SystemUtil;

/**
 * @author Jerry Will
 * @version 2022-10-09
 */
@Slf4j
public class Env {

    // the absolute path of core-site.xml, hdfs-site.xml, hive-site.xml
    public static final String CORE_SITE_FILE_PATH = getEnv("CORE_SITE_FILE_PATH");
    public static final String HDFS_SITE_FILE_PATH = getEnv("HDFS_SITE_FILE_PATH");
    public static final String HIVE_SITE_FILE_PATH = getEnv("HIVE_SITE_FILE_PATH");

    private static <T> T getEnv(String key) {
        return getEnv(key, null);
    }

    @SuppressWarnings("unchecked")
    private static <T> T getEnv(String key, Object defaultValue) {
        Object value;
        if (defaultValue instanceof Integer) {
            value = SystemUtil.getEnv(key, (int) defaultValue);
        } else if (defaultValue instanceof Long) {
            value = SystemUtil.getEnv(key, (long) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            value = SystemUtil.getEnv(key, (boolean) defaultValue);
        } /*else if (defaultValue instanceof Double) {
            value = SystemUtil.getEnv(key, (double) defaultValue);
        } */ else {
            value = SystemUtil.getEnv(key, (String) defaultValue);
        }
        log.info("load env {}={}", key, value);
        return (T) value;
    }
}