package com.tektak.iloop.rmodel;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dipak Malla
 * Date: 8/7/14
 */
public interface ResultSetProcessor {
    public <E> E process(ResultSet resultSet, E model) throws RmodelException.CommonException;
}
