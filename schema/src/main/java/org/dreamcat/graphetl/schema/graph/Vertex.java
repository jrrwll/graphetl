package org.dreamcat.graphetl.schema.graph;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.dreamcat.graphetl.schema.conf.ExecutionConf;
import org.dreamcat.graphetl.schema.conf.OperatorConf;
import org.dreamcat.graphetl.schema.operator.Operator;

/**
 * @author Jerry Will
 * @version 2022-08-29
 */
@Getter
@RequiredArgsConstructor
@JsonInclude(Include.NON_DEFAULT)
public class Vertex {

    private final int id;
    private final Operator operator;
    private OperatorConf operatorConf;
    private ExecutionConf executionConf;
}
