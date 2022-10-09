package org.dreamcat.graphetl.schema.conf;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jerry Will
 * @version 2022-08-31
 */
@Getter
@Setter
public class SourceJdbcConf extends OperatorConf {
    private String url;
    private String driver;
    private String user;
    private String password;
    private String tableName;
    private Map<String, String> properties;
}
