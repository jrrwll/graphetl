package org.dreamcat.graphetl.runtime.flink.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

/**
 * @author Jerry Will
 * @version 2022-08-31
 */
@Slf4j
@RequiredArgsConstructor
public class JdbcSourceFunction extends RichSourceFunction<List<Object>> {

    final String jdbcUrl;
    final String username;
    final String password;
    final String sql;
    final int fieldCount;
    final int intervalMs;

    private volatile boolean run = true;

    @Override
    public void close() throws Exception {
        this.cancel();
    }

    @Override
    public void cancel() {
        run = false;
    }

    @Override
    public void run(SourceContext<List<Object>> ctx) throws Exception {
        while (run) {
            try {
                List<List<Object>> rows = fetch();
                if (rows != null) {
                    for (List<Object> row : rows) {
                        ctx.collect(row);
                    }
                    if (log.isDebugEnabled()) {
                        log.info("emit new rows {}",
                                rows.size() < 10 ? rows : rows.size() + " <rows>");
                    }
                } else {
                    log.warn("fetch a empty rows through jdbc");
                }
                Thread.sleep(intervalMs);
            } catch (Exception e) {
                log.error("cannot fetch rows jdbc", e);
            }
        }
    }


    public List<List<Object>> fetch() throws SQLException {
        try (Connection connection = openConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                return parseResultSet(rs);
            }
        }
    }

    private List<List<Object>> parseResultSet(ResultSet rs) throws SQLException {
        List<List<Object>> rows = new ArrayList<>();
        while (rs.next()) {
            List<Object> row = new ArrayList<>(fieldCount);
            for (int i = 1; i <= fieldCount; i++) {
                Object rawCol = rs.getObject(i);
                row.add(rawCol);
            }
            rows.add(row);
        }
        return rows;
    }

    private Connection openConnection() {
        try {
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
