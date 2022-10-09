package org.dreamcat.graphetl.runtime.spark.test;

import java.util.Map;
import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * @author Jerry Will
 * @version 2022-08-26
 */
public class JdbcOp {

    public static void main(String[] args) {
        Map<String, String> options = SparkUtil.loadConfig("jdbc-op.properties");
        System.out.println("load options: " + options);

        SparkSession sparkSession = SparkUtil.createSparkSession();

        DataFrameReader reader = sparkSession.read().format("jdbc");
        reader.options(options);

        Dataset<Row> rows = reader.load();
        System.out.println(rows);
    }
}
