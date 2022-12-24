package com.dbms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author ayushkumar
 * @created on 04/12/22
 */
//public class DBConfigManager {
//    private static final Logger logger = Logger.getLogger(DBConfigManager.class.getName());
//
//    private static Properties getDbProperties() {
//        Properties prop = new Properties();
//        String fileName = "db.properties";
//        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
//            prop.load(fileInputStream);
//        } catch (Throwable throwable) {
//            logger.log(Level.SEVERE, "DB config file not found at path = " + fileName);
//            throw new RuntimeException(throwable.getMessage());
//        }
//        return prop;
//    }
//
//    public static String getDBUrl() {
//        Properties prop = getDbProperties();
//        return prop.getProperty("db.url");
//    }
//
//    public static String getDBUser() {
//        Properties prop = getDbProperties();
//        return prop.getProperty("db.username");
//    }
//
//    public static String getDBPass() {
//        Properties prop = getDbProperties();
//        return prop.getProperty("db.password");
//    }
//
//}

//@Configuration
//@PropertySource("classpath:db.properties")
//public class DBConfigManager {
//
//    @Value("${db.driverClassName}")
//    String driverClassName;
//
//    @Value("${db.url}")
//    String url;
//
//    @Value("${db.username}")
//    String username;
//
//    @Value("${db.password}")
//    String password;
//
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer
//    propertySourcesPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }

//}
