package com.tektak.iloop.rmodel.query;

import com.tektak.iloop.rmodel.RmodelException;
import com.tektak.iloop.rmodel.driver.Sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Dipak Malla
 * Date: 6/30/14
 */
interface Query {
    public void InitPreparedStatement() throws RmodelException.SqlException, RmodelException.CommonException;
    public PreparedStatement getPreparedStatement();
    public void setPreparedStatement(PreparedStatement preparedStatement);
    public int Dml() throws RmodelException.SqlException;
    public ResultSet Drl() throws RmodelException.SqlException;
    public void setSql(Sql sql);
    public void Close() throws RmodelException.SqlException;
    public void setQuery(String query);
}
