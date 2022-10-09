package org.dreamcat.graphetl.schema.operator;

/**
 * @author Jerry Will
 * @version 2022-08-29
 */
public enum Operator {
    source,
    sink,
    filter,
    map,
    flat_map,
    top_n,
    // group
    group_by,
    ;

    public @interface Define {

        Operator value();
    }
}
