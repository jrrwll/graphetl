package org.dreamcat.graphetl.schema.operator;

/**
 * @author Jerry Will
 * @version 2022-08-30
 */
public enum ConnectorOperator {
    jdbc,
    click_house,
    elastic_search,
    http,
    ;

    public @interface Define {

        ConnectorOperator value();

        boolean onlySource() default false;

        boolean onlySink() default false;
    }
}
