package org.dreamcat.graphetl.schema.conf;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Jerry Will
 * @version 2022-08-29
 */
@Getter
@Setter
public class ExecutionConf {

    private int parallelism;
    private int maxParallelism;
}
