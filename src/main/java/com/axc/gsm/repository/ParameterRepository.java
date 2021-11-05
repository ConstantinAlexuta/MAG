package com.axc.gsm.repository;

import com.axc.gsm.database.IDatabase;
import com.axc.gsm.database.IRepository;
import com.axc.gsm.model.Parameter;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class ParameterRepository implements IRepository<Parameter, Integer> {

    private final IDatabase database;

    public ParameterRepository(IDatabase database) {
        this.database = database;
    }


    @Override
    public Parameter get(Integer index) {
        Parameter parameter = null;
        String query = String .format("SELECT * FROM parameter WHERE id = %d", index);
        try {
            ResultSet resultSet = database.executeQuery(query);
            while (resultSet.next()) {
                parameter = new Parameter();
                Integer id = resultSet.getInt("id");
                System.out.println();
                parameter.setId(id);
                parameter.setName(resultSet.getString("name"));
                parameter.setValue(resultSet.getString("value"));
                parameter.setActions(resultSet.getString("actions"));
                parameter.setLastUpdateDateTime(resultSet.getString("lastUpdateDateTime"));
                parameter.setLastUpdateUser(resultSet.getString("lastUpdateUser"));
                parameter.setHistory(resultSet.getString("history"));
            }
        } catch (Exception e) {
            return parameter;
        }
        return parameter;
    }

    @Override
    public List<Parameter> getAll() {

        List<Parameter> list = new ArrayList<>();
//        list.add(new Parameter(1,"test","test","test","test","test","test"));
        System.out.println("====================================>>>>>>>>>  ParameterRepository >> getAll()");

//        String query = "SELECT * FROM C##_JAVA_JSP.TBL_PARAMETER ORDER BY N_ID ASC";
//        String query = "SELECT * FROM C##_JAVA_JSP.TBL_PARAMETER";
        String query = "SELECT * FROM TBL_PARAMETER";

        System.out.println("query = " + query);

        try {
            ResultSet resultSet = database.executeQuery(query);

            System.out.println();
            System.out.println("resultSet = " + resultSet);
            System.out.println("resultSet.toString() = " + resultSet.toString());
            System.out.println("resultSet.getMetaData().getSchemaName(1) = " + resultSet.getMetaData().getSchemaName(1));
            System.out.println("resultSet.getMetaData().getTableName(1) = " + resultSet.getMetaData().getTableName(1));
            System.out.println();
            System.out.println("resultSet.getMetaData().getColumnName(1) = " + resultSet.getMetaData().getColumnName(1));
            System.out.println("resultSet.getMetaData().getColumnName(2) = " + resultSet.getMetaData().getColumnName(2));
            System.out.println("resultSet.getMetaData().getColumnName(3) = " + resultSet.getMetaData().getColumnName(3));
            System.out.println("resultSet.getMetaData().getColumnName(4) = " + resultSet.getMetaData().getColumnName(4));
            System.out.println("resultSet.getMetaData().getColumnName(5) = " + resultSet.getMetaData().getColumnName(5));
            System.out.println("resultSet.getMetaData().getColumnName(6) = " + resultSet.getMetaData().getColumnName(6));
            System.out.println("resultSet.getMetaData().getColumnName(7) = " + resultSet.getMetaData().getColumnName(7));
            System.out.println();
            System.out.println("resultSet.getMetaData().getColumnType(1) = " + resultSet.getMetaData().getColumnType(1));
            System.out.println("resultSet.getMetaData().getColumnType(2) = " + resultSet.getMetaData().getColumnType(2));
            System.out.println("resultSet.getMetaData().getColumnType(3) = " + resultSet.getMetaData().getColumnType(3));
            System.out.println("resultSet.getMetaData().getColumnType(4) = " + resultSet.getMetaData().getColumnType(4));
            System.out.println("resultSet.getMetaData().getColumnType(5) = " + resultSet.getMetaData().getColumnType(5));
            System.out.println("resultSet.getMetaData().getColumnType(6) = " + resultSet.getMetaData().getColumnType(6));
            System.out.println("resultSet.getMetaData().getColumnType(7) = " + resultSet.getMetaData().getColumnType(7));

            ResultSetMetaData metaData = resultSet.getMetaData();
            System.out.println();
            System.out.println("metaData = " + metaData);
            System.out.println();
            System.out.println("metaData.toString() = " + metaData.toString());

            while (resultSet.next()) {

                Parameter parameter = new Parameter();

                System.out.println();

                int id = resultSet.getInt("N_ID");
                System.out.println("id = " + id);
                parameter.setId(id);

                String name = resultSet.getString("VCH_NAME");
                System.out.println("name = " + name);
                parameter.setName(name);

                String value = resultSet.getString("VCH_VALUE");
                System.out.println("value = " + value);
                parameter.setValue(value);

                String actions = resultSet.getString("VCH_ACTIONS");
                System.out.println("actions = " + actions);
                parameter.setActions(actions);

                String lastUpdateDateTime = resultSet.getString("VCH_LAST_UPDATE_DATE_TIME");
                System.out.println("lastUpdateDateTime = " + lastUpdateDateTime);
                parameter.setLastUpdateDateTime(lastUpdateDateTime);

                String lastUpdateUser = resultSet.getString("VCH_LAST_UPDATE_USER");
                System.out.println("lastUpdateUser = " + lastUpdateUser);
                parameter.setLastUpdateUser(lastUpdateUser);

                String history = resultSet.getString("VCH_HISTORY");
                System.out.println("history = " + history);
                parameter.setHistory(history);

                list.add(parameter);
            }

        } catch (Exception ex) {
            System.out.println("exception in   while (resultSet.next())");
            System.out.println("exception: " + ex);
            return list;
        }
        return list;
    }

    @Override
    public boolean add(Parameter parameter) {
        boolean result = false;
        String query = String.format("INSERT INTO parameter"
                        + " VALUES(NULL, '%s', '%s', '%s', '%s', '%s', '%s')",
                parameter.getName(),
                parameter.getValue(),
                parameter.getActions(),
                parameter.getLastUpdateDateTime(),
                parameter.getLastUpdateUser(),
                parameter.getHistory());
        try {
            database.executeSQL(query);
            result = true;
        } catch (Exception ex) {
            return result;
        }
        return result;
    }

    @Override
    public Parameter update(Integer index, Parameter parameter) {
        Parameter updatedParameter = get(index);
        String query = String.format("UPDATE parameter SET "
                        + "name = '%s', "
                        + "value = '%s', "
                        + "actions = '%s', "
                        + "lastUpdateDateTime = '%s', "
                        + "lastUpdateUser = '%s', "
                        + "history = '%s' "
                        + "WHERE id = %d",
                parameter.getName(),
                parameter.getValue(),
                parameter.getActions(),
                parameter.getLastUpdateDateTime(),
                parameter.getLastUpdateUser(),
                parameter.getHistory(),
                index);

        try {
            boolean result = (boolean) database.executeSQL(query);
            updatedParameter = result ? updatedParameter : parameter;
        } catch (Exception ex) {
            return updatedParameter;
        }
        return updatedParameter;
    }

    @Override
    public Parameter remove(Integer index) {
        String query = String
                .format("DELETE FROM parameter WHERE id = %d", index);
        Parameter deletedParameter = get(index);
        try {
            database.executeSQL(query);
        } catch (Exception ex) {
            return deletedParameter;
        }
        return deletedParameter;
    }

    public Parameter login(String actions, String lastUpdateDateTime) {
        Parameter parameter = null;
        String query = String
                .format("SELECT * FROM parameter "
                        + "WHERE actions = '%s' "
                        + "AND lastUpdateDateTime = md5('%s')", actions, lastUpdateDateTime);
        try {
            ResultSet resultSet = database.executeQuery(query);
            while (resultSet.next()) {
                parameter = new Parameter();
                parameter.setId(resultSet.getInt("id"));
                parameter.setName(resultSet.getString("name"));
                parameter.setValue(resultSet.getString("value"));
                parameter.setActions(resultSet.getString("actions"));
                parameter.setLastUpdateDateTime(resultSet.getString("lastUpdateDateTime"));
                parameter.setLastUpdateUser(resultSet.getString("lastUpdateUser"));
                parameter.setHistory(resultSet.getString("history"));
            }
        } catch (Exception e) {
            return parameter;
        }
        return parameter;
    }

}
