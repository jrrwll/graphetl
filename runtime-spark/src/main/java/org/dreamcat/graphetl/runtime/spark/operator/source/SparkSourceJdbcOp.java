package org.dreamcat.graphetl.runtime.spark.operator.source;

import java.util.Map;
import java.util.Properties;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.dreamcat.graphetl.runtime.spark.SparkRuntime;
import org.dreamcat.graphetl.schema.conf.SourceJdbcConf;
import org.dreamcat.graphetl.schema.operator.Operator;

/**
 * @author Jerry Will
 * @version 2022-08-30
 */
@Operator.Define(Operator.source_jdbc)
public class SparkSourceJdbcOp {

    final SparkSession session;
    final SourceJdbcConf conf;

    public SparkSourceJdbcOp(SparkRuntime runtime, SourceJdbcConf conf) {
        this.session = runtime.getSession();
        this.conf = conf;
    }

    public Dataset<Row> run() {
        Map<String, String> props = conf.getProperties();
        Properties properties;
        if (props != null) {
            properties = new Properties(props.size() + 2);
            properties.putAll(props);
        } else {
            properties = new Properties();
        }
        properties.put("user", conf.getUser());
        properties.put("password", conf.getPassword());

        return session.read()
                .jdbc(conf.getUrl(), conf.getTableName(), properties);
    }
}
