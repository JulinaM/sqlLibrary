package com.tektak.iloop.rmodel.connection;

/**
 * Created by Dipak Malla
 * Date: 6/30/14
 * Getter and setter methods.
 * @author Dipak Malla
 */
public class MySqlConnection implements Connection {
    private String url;
    private String databaseName;
    private String username;
    private String password;
    private String driver;
    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    @Override
    public String getDatabaseName() {
        return this.databaseName;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public String getDriver() {
        return this.driver;
    }
}
