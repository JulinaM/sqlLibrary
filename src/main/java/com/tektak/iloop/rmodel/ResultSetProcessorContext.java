package com.tektak.iloop.rmodel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dipak Malla
 * Date: 8/7/14
 */
public class ResultSetProcessorContext {
    public static <E> ArrayList<E> processResult(ResultSet resultSet, ResultSetProcessor resultSetProcessor, E model)
            throws RmodelException.CommonException, SQLException {
        resultSet.last();
        int size = resultSet.getRow();
        resultSet.beforeFirst();
        ArrayList<E> modelList = new ArrayList<>(size);
        while (resultSet.next()) {
            modelList.add(resultSetProcessor.process(resultSet,model));
        }
        return modelList;
    }
}
