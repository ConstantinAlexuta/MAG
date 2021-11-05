package com.axc.gsm.database;

import com.axc.gsm.model.User;
import com.axc.gsm.repository.DiaryRepository;
import com.axc.gsm.repository.ParameterRepository;
import com.axc.gsm.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class OracleDatabaseHelper {

    public static String VIEW_FOLDER = "WEB-INF/view";
    public static String NOT_FOUND = "notfound.jsp";
    public static String DB_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
    public static String DB_USE_SSL = "useSSL=false";
    public static String DB_SERVER_TIMEZONE = "serverTimezone=UTC";
    public static String DB_CHARSET = "charset=UTF-8";
    public static String DB_USERNAME = "user=SYSTEM";
    //    public static String DB_PASSWORD = "password=DDaeklmg98$%" + "&amp;" + "Djyjkuy5DDD";
    public static String DB_PASSWORD = "password=DDaeklmg98$%" +   "Djyjkuy5DDD";

    //    public static String DB_SOURCE = DB_URL + "?" + DB_USE_SSL + "&" + DB_SERVER_TIMEZONE + "&" + DB_USERNAME + "&" + DB_PASSWORD + "&" + DB_CHARSET;
    public static String DB_SOURCE = "jdbc:oracle:thin:@//localhost:1522/orcl?user=SYSTEM&password=DDaeklmg98$%Djyjkuy5DDD";
    private static IDatabase DATABASE = null;

    public static void view(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String viewFile = getViewFile(request);
        request
                .getRequestDispatcher(OracleDatabaseHelper.VIEW_FOLDER + File.separator + viewFile)
                .forward(request, response);
    }

    private static String getViewFile(HttpServletRequest request) {
        Object viewFileAttribute = request.getAttribute("viewFile");
        return (viewFileAttribute == null)
                ? OracleDatabaseHelper.NOT_FOUND
                : viewFileAttribute.toString();
    }

    private static IDatabase getOracleDatabase() {
        System.out.println("====================================>>>>>>>>>  OracleDatabaseHelper >> getOracleDatabase()");

        if (OracleDatabaseHelper.DATABASE == null) {
            OracleDatabaseHelper.DATABASE = new OracleDatabase(OracleDatabaseHelper.DB_SOURCE);
        }
        return OracleDatabaseHelper.DATABASE;
    }

    public static UserRepository userRepository() {
        return new UserRepository(OracleDatabaseHelper.getOracleDatabase());
    }

    public static DiaryRepository diaryRepository() {
        return new DiaryRepository(OracleDatabaseHelper.getOracleDatabase());
    }

    public static ParameterRepository parameterRepository() {
        System.out.println("====================================>>>>>>>>>  OracleDatabaseHelper >> parameterRepository()");

        return new ParameterRepository(OracleDatabaseHelper.getOracleDatabase());
    }

    public static boolean checkParameters(String[] parameters, Map<String, String[]> parameterMap) {
        for (String parameter : parameters) {
            if (!parameterMap.containsKey(parameter)) {
                return false;
            }
        }
        return true;
    }

    public static String md5(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(text.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return text;
        }
    }

    public static User getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userAttribute = session.getAttribute("user");
        return userAttribute == null ? null : (User) userAttribute;
    }
}
