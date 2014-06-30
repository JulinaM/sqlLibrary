package com.tektak.iloop.rmodel;

/**
 * Created by Dipak Malla
 * Date: 6/30/14
 */
public interface RmodelException {
    public static final String CommonException = "";
    public static final String SQL_EXCEPTION = "Error in sql driver.";
    public static final String CONNECTION_NULL = "Connection is null.";
    public class CommonException extends Exception{
        public CommonException(String message, Throwable throwable){
            super(message, throwable);
        }
        public CommonException(String message){
            super(message);
        }
    }
    public class SqlException extends Exception{
        public SqlException(String message, Throwable throwable){
            super(message, throwable);
        }
        public SqlException(String message){
            super(message);
        }
    }

}
