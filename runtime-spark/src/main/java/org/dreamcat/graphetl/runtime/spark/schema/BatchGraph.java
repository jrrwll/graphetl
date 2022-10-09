package org.dreamcat.graphetl.runtime.spark.schema;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.spark.sql.SparkSession;
import org.dreamcat.graphetl.schema.graph.Graph;

/**
 * @author Jerry Will
 * @version 2022-10-09
 */
public class BatchGraph {

    private Map<Integer, BatchVertex> vertices;
    private List<List<Integer>> edges;
    private Set<Integer> sources;
    private Set<Integer> sinks;

    public BatchGraph(Graph graph) {

    }

    public void configure(SparkSession session) throws Exception {

    }


}
