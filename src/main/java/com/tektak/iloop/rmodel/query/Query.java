package com.tektak.iloop.rmodel.query;

import com.tektak.iloop.rmodel.RmodelException;
import com.tektak.iloop.rmodel.driver.Sql;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Dipak Malla
 * Date: 6/30/14
 */
public interface Query {
    public void InitPreparedStatement() throws RmodelException.SqlException, RmodelException.CommonException;
    public void InitPreparedStatement(int type) throws RmodelException.SqlException, RmodelException.CommonException;
    public void InitCallableStatement() throws RmodelException.SqlException, RmodelException.CommonException;
    public PreparedStatement getPreparedStatement();
    public void setPreparedStatement(PreparedStatement preparedStatement);
    public CallableStatement getCallableStatement();
    public void setCallableStatement(CallableStatement callableStatement);
    public int Dml() throws RmodelException.SqlException;
    public int DmlWithGeneratedId() throws RmodelException.SqlException;
    public ResultSet Drl() throws RmodelException.SqlException;
    public void setSql(Sql sql);
    public void Close() throws RmodelException.SqlException;
    public void setQuery(String query);
}
