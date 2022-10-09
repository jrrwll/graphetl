package org.dreamcat.graphetl.schema.operator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Jerry Will
 * @version 2022-08-29
 */
@Getter
@RequiredArgsConstructor
public enum Operator {
    /// source & sink
    source_jdbc(Type.source),
    sink_jdbc(Type.sink),
    source_elastic_search(Type.source),
    sink_elastic_search(Type.sink),

    /// transform
    join,
    union,
    filter,
    map,
    flat_map,
    top,
    agg,
    ;

    Operator() {
        this(Type.transform);
    }

    private final Type type;

    public @interface Define {

        Operator value();
    }

    public enum Type {
        source,
        sink,
        transform
    }
}
