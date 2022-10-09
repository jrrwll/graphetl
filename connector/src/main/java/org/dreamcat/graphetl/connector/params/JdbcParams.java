package org.dreamcat.graphetl.connector.params;

import java.util.Map;
import lombok.Data;

/**
 * @author Jerry Will
 * @version 2022-09-07
 */
@Data
public class JdbcParams {

    private String url;
    private String user;
    private String password;
    private String tableName;
    private Map<String, String> properties;
}
