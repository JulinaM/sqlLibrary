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
 * @author Dipak Malla
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

    /**
     * Initilizes prepared statement
     * @throws RmodelException.SqlException
     * @throws RmodelException.CommonException
     */
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

    /**
     * Run DML Query
     * @return Rows Affected
     * @throws RmodelException.SqlException
     */
    @Override
    public int Dml() throws RmodelException.SqlException {
        try {
            return this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RmodelException.SqlException(RmodelException.SQL_EXCEPTION,e);
        }
    }

    /**
     * Runs DRL Query
     * @return Result Set
     * @throws RmodelException.SqlException
     */
    @Override
    public ResultSet Drl() throws RmodelException.SqlException {
        try {
            ResultSet resultSet = this.preparedStatement.executeQuery();
            if(resultSet.last())
                return resultSet;
            else
                throw new RmodelException.SqlException(RmodelException.RESULT_SET_EMPTY);
        } catch (SQLException e) {
            throw new RmodelException.SqlException(RmodelException.SQL_EXCEPTION,e);
        }
    }

    @Override
    public void setSql(Sql sql) {
        this.sql = (MySql)sql;
    }

    /**
     * Closes database connections
     * @throws RmodelException.SqlException
     */
    @Override
    public void Close() throws RmodelException.SqlException {
        this.sql.CloseConnection();
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }
}
