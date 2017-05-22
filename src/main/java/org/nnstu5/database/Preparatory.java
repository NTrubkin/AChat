package org.nnstu5.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by trubk on 22.05.2017.
 */
public interface Preparatory {
    PreparedStatement createPreparedStatement(String sql) throws SQLException;
}
