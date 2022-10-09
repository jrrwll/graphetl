package org.dreamcat.graphetl.runtime.spark.connector;

import java.util.Map;
import java.util.Properties;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.dreamcat.graphetl.connector.params.JdbcParams;
import org.dreamcat.graphetl.schema.operator.ConnectorOperator;

/**
 * @author Jerry Will
 * @version 2022-08-30
 */
@ConnectorOperator.Define(ConnectorOperator.jdbc)
public class SparkJdbcConnector {

    SparkSession session;
    JdbcParams params;

    public Dataset<Row> run() {
        Map<String, String> props = params.getProperties();
        Properties properties;
        if (props != null) {
            properties = new Properties(props.size() + 2);
            properties.putAll(props);
        } else {
            properties = new Properties();
        }
        properties.put("user", params.getUser());
        properties.put("password", params.getPassword());

        return session.read()
                .jdbc(params.getUrl(), params.getTableName(), properties);
    }
}
