package org.dreamcat.graphetl.runtime.flink;

import java.util.Set;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.dreamcat.graphetl.schema.graph.Graph;
import org.dreamcat.graphetl.schema.graph.Vertex;
import org.dreamcat.graphetl.schema.operator.Operator;

/**
 * @author Jerry Will
 * @version 2022-08-29
 */
public class FlinkRuntime {

    final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

    public void configure(Graph graph) throws Exception {

        addSources(graph, env);

        env.execute(graph.getJobName());
    }

    private void addSources(Graph graph, StreamExecutionEnvironment env) {
        Set<Integer> sources = graph.getSources();
        for (Integer sourceId : sources) {
            Vertex source = graph.getVertices().get(sourceId);

            Operator op = source.getOperator();

            source.getId();
        }
    }
}
