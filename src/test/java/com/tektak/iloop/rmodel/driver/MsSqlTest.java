package com.tektak.iloop.rmodel.driver;

import com.tektak.iloop.rmodel.RmodelException;
import com.tektak.iloop.rmodel.connection.MySqlConnection;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Dipak Malla
 * Date: 7/30/14
 */
public class MsSqlTest {
    @Test
    public void TestConnection() throws RmodelException.SqlException {
        MySqlConnection mySqlConnection = new MySqlConnection();
        mySqlConnection.setDatabaseName("iLoopV1_0");
        mySqlConnection.setUsername("sa");
        mySqlConnection.setPassword("sandman");
        mySqlConnection.setUrl("jdbc:sqlserver://10.0.0.6:1433");
        mySqlConnection.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        MsSql mySql = new MsSql();
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
