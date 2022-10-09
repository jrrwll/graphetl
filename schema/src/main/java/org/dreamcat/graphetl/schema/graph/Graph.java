package org.dreamcat.graphetl.schema.graph;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import org.dreamcat.common.util.MapUtil;

/**
 * @author Jerry Will
 * @version 2022-08-29
 */
@Getter
public class Graph implements Serializable {

    private static final long serialVersionUID = 1L;

    private String jobName;
    private JobType jobType = JobType.BATCH;

    private List<Vertex> vertices;
    private List<List<Integer>> edges;
    private Set<Integer> sources;
    private Set<Integer> sinks;

    private transient Map<Integer, Vertex> vertexMap;

    public synchronized Map<Integer, Vertex> getVertexMap() {
        if (vertexMap == null) {
            vertexMap = MapUtil.toMap(vertices, Vertex::getId);
        }
        return vertexMap;
    }

}
