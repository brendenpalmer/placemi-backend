package com.placemi.commons;

import org.apache.commons.dbcp2.*;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Created by brendenpalmer on 2016-02-06.
 */
public final class DatabaseHelper {
    private static final BasicDataSource source = new BasicDataSource();
    private static final String resourcePath = "/database.properties";
    private static Properties properties = null;

    static {
        try {
            source.setDriverClassName(getProperties().getProperty("driver"));
            source.setUrl(getProperties().getProperty("url"));
            source.setUsername(getProperties().getProperty("username"));
            source.setPassword(getProperties().getProperty("password"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DatabaseHelper() {
        // do nothing, private member, no need for external access
    }

    /**
     * Gets the database properties file
     *
     * @return The database properties file
     * @throws IOException
     */
    private static synchronized Properties getProperties() throws IOException {
        if (properties != null) {
            return properties;
        } else {
            InputStream inputStream = new ClassPathResource(resourcePath).getInputStream();
            properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            return properties;
        }
    }

    /**
     * Gets the database connection
     *
     * @return The database connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return source.getConnection();
    }
}
