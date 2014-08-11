package com.tektak.iloop.rmodel.driver;

import com.tektak.iloop.rmodel.RmodelException;
import com.tektak.iloop.rmodel.connection.MySqlConnection;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Dipak Malla
 * Date: 7/1/14
 */
public class MySqlTest {
    @Test
    public void TestConnection() throws RmodelException.SqlException {
        MySqlConnection mySqlConnection = new MySqlConnection();
        mySqlConnection.setDatabaseName("ram");
        mySqlConnection.setUsername("root");
        mySqlConnection.setPassword("cdanged");
        mySqlConnection.setUrl("jdbc:mysql://localhost:3306/");
        mySqlConnection.setDriver("com.mysql.jdbc.Driver");
        MySql mySql = new MySql();
        mySql.setConnection(mySqlConnection);
        try {
            mySql.InitConnection();
        } catch (RmodelException.CommonException e) {
            e.printStackTrace();
        } catch (RmodelException.SqlException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(mySql.getSqlConnection());
    }

}
