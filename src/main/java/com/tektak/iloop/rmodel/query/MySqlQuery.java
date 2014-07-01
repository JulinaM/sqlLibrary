package com.tektak.iloop.rmodel.query;

import com.tektak.iloop.rmodel.RmodelException;
import com.tektak.iloop.rmodel.driver.MySql;
import com.tektak.iloop.rmodel.driver.Sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dipak Malla
 * Date: 7/1/14
 */
public class MySqlQuery implements Query {
    private PreparedStatement preparedStatement;
    private MySql sql;
    private String query;

    public MySqlQuery()
    {}
    public MySqlQuery(Sql sql, String query) throws RmodelException.SqlException {
        this.sql = (MySql)sql;
        this.query = query;
        try {
            this.preparedStatement = this.sql.getSqlConnection().prepareStatement(this.query);
        } catch (SQLException e) {
            throw new RmodelException.SqlException(RmodelException.SQL_EXCEPTION,e);
        }
    }

    @Override
    public void InitPreparedStatement() throws RmodelException.SqlException,
            RmodelException.CommonException {
        if(this.query == null || this.query.isEmpty())
            throw  new RmodelException.CommonException(RmodelException.QUERY_INVALID);
        if(this.sql == null)
            throw new RmodelException.CommonException(String.format(RmodelException.FORMATED_NULL_ERROR,"MySql Object"));
        try {
            this.preparedStatement = this.sql.getSqlConnection().prepareStatement(this.query);
        } catch (SQLException e) {
            throw new RmodelException.SqlException(RmodelException.SQL_EXCEPTION,e);
        }
    }

    @Override
    public PreparedStatement getPreparedStatement() {
        return this.preparedStatement;
    }

    @Override
    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    @Override
    public int Dml() throws RmodelException.SqlException {
        try {
            return this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RmodelException.SqlException(RmodelException.SQL_EXCEPTION,e);
        }
    }

    @Override
    public ResultSet Drl() throws RmodelException.SqlException {
        try {
            return this.preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RmodelException.SqlException(RmodelException.SQL_EXCEPTION,e);
        }
    }

    @Override
    public void setSql(Sql sql) {
        this.sql = (MySql)sql;
    }

    @Override
    public void Close() throws RmodelException.SqlException {
        this.sql.CloseConnection();
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }
}
