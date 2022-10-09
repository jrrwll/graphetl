package org.dreamcat.graphetl.schema.graph;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lombok.Getter;
import org.dreamcat.common.util.StreamUtil;

/**
 * @author Jerry Will
 * @version 2022-08-29
 */
@Getter
public class Graph implements Serializable {

    private static final long serialVersionUID = 1L;

    private String jobName;
    private JobType jobType = JobType.STREAMING;

    private List<Vertex> vertices;
    private Set<Integer> sources;
    private Set<Integer> sinks;

    private transient Map<Integer, Vertex> vertexMap;

    public synchronized Map<Integer, Vertex> getVertexMap() {
        if (vertexMap == null) {
            // todo vertexMap = StreamUtil.toMap(vertices, Vertex::getId);
            vertexMap = vertices.stream().collect(Collectors.toMap(
                    Vertex::getId, Function.identity()));
        }
        return vertexMap;
    }

}
