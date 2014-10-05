package com.tektak.iloop.rmodel.driver;

import com.tektak.iloop.rmodel.RmodelException;
import com.tektak.iloop.rmodel.connection.MySqlConnection;
import org.junit.Assert;
import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

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
        mySqlConnection.setPassword("tektak123@np");
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
    @Test
    public void T(){
        MySqlConnection mySqlConnection = new MySqlConnection();
        mySqlConnection.setDatabaseName("iLoopV1_0");
        mySqlConnection.setUsername("sa");
        mySqlConnection.setPassword("tektak123@np");
        mySqlConnection.setUrl("jdbc:sqlserver://10.0.0.6:1433");
        mySqlConnection.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        MsSql mySql = new MsSql();
        mySql.setConnection(mySqlConnection);
        try {
            mySql.InitConnection();
            CallableStatement callableStatement = mySql.getSqlConnection().prepareCall("EXEC userLoginTest ?,?,?,? ");
            callableStatement.setString(1, "indira");
            callableStatement.setString(2, "c49ddb9f837b6dc68325b23bb56bcfa9fc810dcf2730d101c16f7beea6f2ac25246d8086618837445cbf7b7e7eaaef70b656a286d8393a0d159300613d341209");
            callableStatement.setString(3, "passwordSalt");
            callableStatement.registerOutParameter(4, Types.INTEGER);

            ResultSet rs = callableStatement.executeQuery();
            System.out.println(callableStatement.getInt(4));

        } catch (RmodelException.CommonException e) {
            e.printStackTrace();
        } catch (RmodelException.SqlException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
