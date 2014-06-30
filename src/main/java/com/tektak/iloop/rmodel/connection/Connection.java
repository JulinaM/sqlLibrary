package com.tektak.iloop.rmodel.connection;

/**
 * Created by Dipak Malla
 * Date: 6/30/14
 */
public interface Connection {
    public void setUrl(String url);
    public String getUrl();
    public void setDatabaseName(String databaseName);
    public String getDatabaseName();
    public void setUsername(String username);
    public String getUsername();
    public void setPassword(String password);
    public String getPassword();
    public void setDriver(String driver);
    public String getDriver();
}
