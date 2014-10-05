package com.tektak.iloop.rmodel.query;

import com.tektak.iloop.rmodel.RmodelException;
import com.tektak.iloop.rmodel.driver.MySql;
import com.tektak.iloop.rmodel.driver.Sql;

import java.sql.*;

/**
 * Created by Dipak Malla
 * Date: 7/30/14
 */
public class MsSqlQuery implements Query {
    private PreparedStatement preparedStatement;
    private CallableStatement callableStatement;
    private Sql sql;
    private String query;
    private Connection connection;
    public MsSqlQuery(){}
    public MsSqlQuery(Sql sql, String query) throws RmodelException.SqlException {
        this.sql = sql;
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
            throw new RmodelException.CommonException(String.format(RmodelException.FORMATED_NULL_ERROR,"MSSql Object"));
        try {
            this.connection = this.sql.getSqlConnection();
            this.preparedStatement = this.connection.prepareStatement(this.query, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);


        } catch (SQLException e) {
            throw new RmodelException.SqlException(RmodelException.SQL_EXCEPTION,e);
        }
    }

    @Override
    public void InitPreparedStatement(int type) throws RmodelException.SqlException, RmodelException.CommonException {
        if(this.query == null || this.query.isEmpty())
            throw  new RmodelException.CommonException(RmodelException.QUERY_INVALID);
        if(this.sql == null)
            throw new RmodelException.CommonException(String.format(RmodelException.FORMATED_NULL_ERROR,"MSSql Object"));
        try {
            this.connection = this.sql.getSqlConnection();
            this.preparedStatement = this.connection.prepareStatement(this.query, type);
        } catch (SQLException e) {
            throw new RmodelException.SqlException(RmodelException.SQL_EXCEPTION,e);
        }
    }

    @Override
    public void InitCallableStatement() throws RmodelException.SqlException, RmodelException.CommonException {
        if(this.query == null || this.query.isEmpty())
            throw  new RmodelException.CommonException(RmodelException.QUERY_INVALID);
        if(this.sql == null)
            throw new RmodelException.CommonException(String.format(RmodelException.FORMATED_NULL_ERROR,"MSSql Object"));
        try {
            this.connection = this.sql.getSqlConnection();
            this.callableStatement = this.connection.prepareCall(this.query, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
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
    public CallableStatement getCallableStatement() {
        return this.callableStatement;
    }

    @Override
    public void setCallableStatement(CallableStatement callableStatement) {
        this.callableStatement = callableStatement;
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

    @Override
    public int DmlWithGeneratedId() throws RmodelException.SqlException {
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
            return this.preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RmodelException.SqlException(RmodelException.SQL_EXCEPTION,e);
        }
    }

    @Override
    public void setSql(Sql sql) {
        this.sql = sql;
    }

    /**
     * Closes database connections
     * @throws RmodelException.SqlException
     */
    @Override
    public void Close() throws RmodelException.SqlException {
        if(this.connection != null)
            try {
                this.connection.close();
            } catch (SQLException e) {}
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }
}
