package org.dreamcat.graphetl.runtime.spark.test;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.apache.spark.sql.SparkSession;
import org.dreamcat.common.io.ClassPathUtil;

/**
 * @author Jerry Will
 * @version 2022-08-26
 */
public class SparkUtil {

    public static SparkSession createSparkSession() {
        SparkSession.Builder builder = SparkSession.builder()
                .master("local[1]")
                .appName("example");
        // builder.enableHiveSupport();
        return builder.getOrCreate();
    }

    @SneakyThrows
    public static Map<String, String> loadConfig(String resource) {
        // URL url  =SparkUtil.class.getClassLoader().getResource(resource);
        // String content = Files.readString(Path.of(url.toURI()));
        String content = ClassPathUtil.getResourceAsString(resource);
        return Arrays.stream(content.split("\n"))
                .map(String::trim)
                .map(s -> s.split(": ", 2))
                .collect(Collectors.toMap(
                        s -> s[0].trim(), s -> s[1].trim()));
    }

}
