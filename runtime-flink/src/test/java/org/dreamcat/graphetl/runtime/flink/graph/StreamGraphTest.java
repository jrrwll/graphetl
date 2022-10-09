package org.dreamcat.graphetl.runtime.flink.graph;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.graph.StreamGraph;
import org.junit.jupiter.api.Test;

/**
 * @author Jerry Will
 * @version 2022-08-29
 */
class StreamGraphTest {

    @Test
    void test() {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.addSource(null, "", TypeInformation.of(String.class));

        env.fromSource(null, null,
                "", TypeInformation.of(String.class));

        StreamGraph s = null;

        s.addSource(1, "", "", null,
                TypeInformation.of(String.class), TypeInformation.of(String.class), "");
        s.addEdge(1, 1, 1);
    }
}
