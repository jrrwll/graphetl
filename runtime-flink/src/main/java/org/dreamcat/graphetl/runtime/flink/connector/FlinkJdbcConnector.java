package org.dreamcat.graphetl.runtime.flink.connector;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.dreamcat.graphetl.connector.params.JdbcParams;
import org.dreamcat.graphetl.runtime.flink.support.JdbcSourceFunction;
import org.dreamcat.graphetl.schema.operator.ConnectorOperator;

/**
 * @author Jerry Will
 * @version 2022-08-30
 */
@ConnectorOperator.Define(ConnectorOperator.jdbc)
public class FlinkJdbcConnector {

    StreamExecutionEnvironment env;
    JdbcParams params;

    void v() {
        JdbcSourceFunction source = new JdbcSourceFunction(
                params.getUrl(), params.getUser(), params.getPassword(),
                "", 1, 1000);
        env.addSource(source);
    }
}
