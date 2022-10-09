package org.dreamcat.graphetl.runtime.spark;

import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.SparkSession;
import org.dreamcat.graphetl.schema.graph.Graph;

/**
 * @author Jerry Will
 * @version 2022-08-30
 */
public class SparkRuntime {

    final SparkSession session = SparkSession.builder().getOrCreate();

    public void configure(Graph graph) throws Exception {
        DataFrameReader dfr = new DataFrameReader(session);
        // todo impl

        session.read()
                .jdbc()
                .format("jdbc")
                .load()
    }
}
