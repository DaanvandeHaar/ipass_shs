package nl.hu.ipass.project.persistence;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public abstract class PostgresBaseDao {

    protected final Connection getConnection() {
        Connection result = null;
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/PostgresDS");
            result = ds.getConnection();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }
}

