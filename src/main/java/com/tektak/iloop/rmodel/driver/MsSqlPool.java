package com.tektak.iloop.rmodel.driver;

import com.tektak.iloop.rmodel.RmodelException;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dipak Malla
 * Date: 8/11/14
 */
public class MsSqlPool implements Sql  {
    private com.tektak.iloop.rmodel.connection.Connection connection;
    private DataSource dataSource;
    @Override
    public Connection InitConnection() throws RmodelException.CommonException, RmodelException.SqlException {
        if(this.connection ==  null)
            throw new RmodelException.SqlException(RmodelException.CONNECTION_NULL);
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl(this.connection.getUrl()+";DatabaseName="+ connection.getDatabaseName());
        poolProperties.setDriverClassName(this.connection.getDriver());
        poolProperties.setUsername(this.connection.getUsername());
        poolProperties.setPassword(this.connection.getPassword());
        poolProperties.setJmxEnabled(true);
        poolProperties.setTestWhileIdle(false);
        poolProperties.setTestOnBorrow(true);
        poolProperties.setValidationQuery("SELECT 1");
        poolProperties.setValidationInterval(30000);
        poolProperties.setTimeBetweenEvictionRunsMillis(30000);
        poolProperties.setMaxActive(100);
        poolProperties.setInitialSize(10);
        poolProperties.setMaxWait(10000);
        poolProperties.setRemoveAbandonedTimeout(60);
        poolProperties.setMinEvictableIdleTimeMillis(30000);
        poolProperties.setMinIdle(10);
        poolProperties.setLogAbandoned(true);
        poolProperties.setRemoveAbandoned(true);
        poolProperties.setAbandonWhenPercentageFull(50);
        poolProperties.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
                "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer;"+
                        "org.apache.tomcat.jdbc.pool.interceptor.ResetAbandonedTimer"
        );
        dataSource = new DataSource();
        dataSource.setPoolProperties(poolProperties);
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RmodelException.SqlException(RmodelException.SQL_EXCEPTION,e);
        }
    }

    @Override
    public void setConnection(com.tektak.iloop.rmodel.connection.Connection connection) {
        this.connection = connection;
    }

    @Override
    public void CloseConnection() throws RmodelException.SqlException {
        if(this.dataSource == null)
            throw new RmodelException.SqlException(RmodelException.SQL_EXCEPTION);
        this.dataSource.close();
    }

    @Override
    public Connection getSqlConnection()  throws RmodelException.SqlException {
        if(this.dataSource == null)
            return null;
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RmodelException.SqlException(RmodelException.SQL_EXCEPTION,e);
        }
    }
}
