package com.tektak.iloop.rmodel.driver;


import com.tektak.iloop.rmodel.RmodelException;
import com.tektak.iloop.rmodel.connection.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Dipak Malla
 * Date: 6/30/14
 * @author Dipak Malla
 */
public class MySql implements Sql {
    private java.sql.Connection sqlConnection;
    private Connection connection;

    /**
     * Init the connection
     * @return Sql Connection
     * @throws RmodelException.CommonException
     * @throws RmodelException.SqlException
     */
    @Override
    public java.sql.Connection InitConnection() throws RmodelException.CommonException,
            RmodelException.SqlException {
        if(this.connection ==  null)
            throw new RmodelException.SqlException(RmodelException.CONNECTION_NULL);
        try {
            Class.forName(connection.getDriver()).newInstance();
            this.sqlConnection = DriverManager.getConnection(connection.getUrl()+connection.getDatabaseName(),
                    connection.getUsername(),connection.getPassword());
            return this.sqlConnection;
        } catch (InstantiationException e) {
            throw  new RmodelException.CommonException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw  new RmodelException.CommonException(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            throw  new RmodelException.CommonException(e.getMessage(), e);
        } catch (SQLException e) {
           throw new RmodelException.SqlException(RmodelException.SQL_EXCEPTION,e);
        }

    }
    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void CloseConnection() throws RmodelException.SqlException {
        if(this.sqlConnection != null)
            try {
                this.sqlConnection.close();
            } catch (SQLException e) {
                throw new RmodelException.SqlException(RmodelException.SQL_EXCEPTION,e);
            }
    }

    /**
     * Return sql connection
     * @return Sql Connection
     */
    @Override
    public java.sql.Connection getSqlConnection() throws RmodelException.SqlException {
        if(this.sqlConnection == null)
            throw new RmodelException.SqlException(RmodelException.SQL_EXCEPTION);
        return this.sqlConnection;
    }
}
