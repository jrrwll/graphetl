package org.dreamcat.graphetl.runtime.spark.operator.sink;

import java.util.Map;
import java.util.Properties;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.dreamcat.graphetl.runtime.spark.SparkRuntime;
import org.dreamcat.graphetl.schema.conf.SinkJdbcConf;
import org.dreamcat.graphetl.schema.operator.Operator;

/**
 * @author Jerry Will
 * @version 2022-10-09
 */
@Operator.Define(Operator.sink_jdbc)
public class SparkSinkJdbcOp {

    final SparkSession session;
    final SinkJdbcConf conf;

    public SparkSinkJdbcOp(SparkRuntime runtime, SinkJdbcConf conf) {
        this.session = runtime.getSession();
        this.conf = conf;
    }

    public void run(Dataset<Row> in) {
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

        in.write().jdbc(conf.getUrl(), conf.getTableName(), properties);
    }

}
