package org.dreamcat.graphetl.runtime.spark;

import org.dreamcat.common.io.ClassPathUtil;
import org.dreamcat.common.json.JsonUtil;
import org.dreamcat.graphetl.schema.graph.Graph;

/**
 * @author Jerry Will
 * @version 2022-08-30
 */
public class SparkTest {

    public static void main(String[] args) throws Exception {
        Graph dag = JsonUtil.fromJson(ClassPathUtil.getResourceAsString(
                "dag-batch1.json"), Graph.class);
        SparkRuntime runtime = new SparkRuntime();
        runtime.configure(dag);
    }
}
