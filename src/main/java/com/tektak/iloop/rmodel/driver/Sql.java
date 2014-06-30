package com.tektak.iloop.rmodel.driver;

import com.tektak.iloop.rmodel.RmodelException;
import com.tektak.iloop.rmodel.connection.Connection;

/**
 * Created by Dipak Malla
 * Date: 6/30/14
 */
public interface Sql {
    public java.sql.Connection InitConnection() throws RmodelException.CommonException, RmodelException.SqlException;
    public void setConnection(Connection connection);
    public void CloseConnection() throws RmodelException.SqlException;
    public java.sql.Connection getSqlConnection();
}
