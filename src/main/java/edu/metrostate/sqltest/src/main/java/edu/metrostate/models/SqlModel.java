package edu.metrostate.models;

import edu.metrostate.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class SqlModel {

    private Integer id;

    public SqlModel(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    abstract public PreparedStatement insertStatement(Connection connection) throws SQLException;
    abstract public PreparedStatement updateStatement(Connection connection) throws SQLException;

    public Boolean insert(Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = insertStatement(connection);
            Boolean result = statement.execute();
            if (result) {
                ResultSet resultSet = statement.getResultSet();
                Integer id = resultSet.getInt("id");
                this.setId(id);
                Util.closeQuietly(resultSet);
                return true;
            }
            return false;
        } catch (SQLException ex) {
            return false;
        } finally {
            Util.closeQuietly(statement);
        }
    }

    public Boolean update(Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = updateStatement(connection);
            Boolean result = statement.execute();
            return result;
        } catch (SQLException ex) {
            return false;
        } finally {
            Util.closeQuietly(statement);
        }
    }
}
