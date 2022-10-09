package org.dreamcat.graphetl.runtime.spark;

import lombok.Getter;
import org.apache.spark.sql.SparkSession;
import org.dreamcat.graphetl.runtime.spark.schema.BatchGraph;
import org.dreamcat.graphetl.schema.graph.Graph;

/**
 * @author Jerry Will
 * @version 2022-08-30
 */
@Getter
public class SparkRuntime {

    final SparkSession session = SparkSession.builder().getOrCreate();

    public void configure(Graph graph) throws Exception {
        BatchGraph batchGraph = new BatchGraph(graph);
        batchGraph.configure(session);
    }
}
