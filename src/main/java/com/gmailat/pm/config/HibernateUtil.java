package com.gmailat.pm.config;

import com.gmailat.pm.entity.Client;
import com.gmailat.pm.entity.Discount;
import com.gmailat.pm.entity.Product;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@org.springframework.context.annotation.Configuration
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    @Bean
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/web_service?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "4504913");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.FORMAT_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.USE_SQL_COMMENTS, "true");
                settings.put("hibernate.connection.characterEncoding", "utf8");
                // settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Client.class);
                configuration.addAnnotatedClass(Discount.class);
                configuration.addAnnotatedClass(Product.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
