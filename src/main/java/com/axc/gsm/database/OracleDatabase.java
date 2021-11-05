package com.axc.gsm.database;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class OracleDatabase implements IDatabase {

    private final String source;

    public OracleDatabase(String source) {
        this.source = source;
    }

    @Override
    public Connection getConnection() {
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Class.forName("oracle.jdbc.OracleDriver");

        System.out.println("OracleDatabase >> getConnection()");

//        "jdbc:oracle:thin:@//localhost:1522/orcl?user=SYSTEM&password=DDaeklmg98$%Djyjkuy5DDD"

        OracleDataSource oracleDataSource = null;

        try {
            oracleDataSource = new OracleDataSource();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assert oracleDataSource != null;
        oracleDataSource.setDriverType("thin");
//        oracleDataSource.setDriverType("oci");
        oracleDataSource.setServerName("localhost");
//        oracleDataSource.setPortNumber(1522);
        oracleDataSource.setPortNumber(1521);
//        oracleDataSource.setDatabaseName("orcl");
        oracleDataSource.setDatabaseName("xe");
        oracleDataSource.setUser("SYSTEM");
//        oracleDataSource.setUser("C##_JAVA_JSP");
//        oracleDataSource.setPassword("DDaeklmg98$%Djyjkuy5DDD");
//        oracleDataSource.setPassword("passSFS3434_JAVA_JSP");
//        oracleDataSource.setPassword("DDaek4w44DDD"); // 11g XE
        oracleDataSource.setPassword("DDaeklmg98$%Djyjkuy5DDD"); // 18c XE

        Connection connection = null;

        System.out.println("oracleDataSource.getDataSourceName() = " + oracleDataSource.getDataSourceName());
        System.out.println("oracleDataSource.getDatabaseName() = " + oracleDataSource.getDatabaseName());
        System.out.println("oracleDataSource.getDescription() = " + oracleDataSource.getDescription());
        System.out.println("oracleDataSource.getDriverType() = " + oracleDataSource.getDriverType());
//        System.out.println("oracleDataSource.getRoleName() = " + oracleDataSource.getRoleName());
        System.out.println("oracleDataSource.getServerName() = " + oracleDataSource.getServerName());
        System.out.println("oracleDataSource.getServiceName() = " + oracleDataSource.getServiceName());
        System.out.println("oracleDataSource.getTNSEntryName() = " + oracleDataSource.getTNSEntryName());
        try {
            System.out.println("oracleDataSource.getURL() = " + oracleDataSource.getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("oracleDataSource.getUser() = " + oracleDataSource.getUser());
        System.out.println("oracleDataSource.getPortNumber() = " + oracleDataSource.getPortNumber());

        try {

//            connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=minty&password=greatsqldb");
//            connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1522/orcl?user=SYSTEM&password=DDaeklmg98$%Djyjkuy5DDD");

            connection = oracleDataSource.getConnection();

            System.out.println();
            System.out.println("connection.getClientInfo() = " + connection.getClientInfo());
            System.out.println("connection.getCatalog() = " + connection.getCatalog());
//            System.out.println("connection.getSchema() = " + connection.getSchema());
            System.out.println("connection.getHoldability() = " + connection.getHoldability());
            System.out.println("connection.getAutoCommit() = " + connection.getAutoCommit());
//            System.out.println("connection.getNetworkTimeout() = " + connection.getNetworkTimeout());
            System.out.println("connection.getTransactionIsolation() = " + connection.getTransactionIsolation());
            System.out.println("connection.toString() = " + connection.toString());
            System.out.println("connection.isClosed() = " + connection.isClosed());
            System.out.println("connection.isValid(20) = " + connection.isValid(20));
            System.out.println("connection.isReadOnly() = " + connection.isReadOnly());

            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println();
            System.out.println("message:" + ex.getMessage());
            System.out.println();
            System.out.println("cause:" + ex.getCause());
            System.out.println();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println();
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println();
            System.out.println("VendorError: " + ex.getErrorCode());
            System.out.println();
        }

        //        return DriverManager.getConnection(source);
        return connection;
    }

    @Override
    public ResultSet executeQuery(String query) {

        System.out.println();
        System.out.println("OracleDatabase >> executeQuery(" + query + ")");


        System.out.println();
        System.out.println("--------------- = " + executeSQL(query));
        System.out.println();

        ResultSet resultSet = null;

        try {
//            resultSet = getConnection().createStatement().executeQuery(query);
            resultSet = getConnection()
                    .prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
                    .executeQuery();

            System.out.println("resultSet = " + resultSet.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    @Override
    public Object executeSQL(String sql) {

        System.out.println("OracleDatabase >> executeSQL(" + sql + ")");

        Object object = null;

        try {
            object = getConnection().createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

}