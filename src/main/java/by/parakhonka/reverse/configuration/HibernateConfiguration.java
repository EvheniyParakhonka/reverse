package by.parakhonka.reverse.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan({"by.parakhonka.reverse.configuration"})
@PropertySource(value = {"classpath:application.properties"})
public class HibernateConfiguration {

    private final Environment mEnvironment;

    @Autowired
    public HibernateConfiguration(Environment environment) {
        this.mEnvironment = environment;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"by.parakhonka.reverse.entity"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(mEnvironment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(mEnvironment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(mEnvironment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(mEnvironment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", mEnvironment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", mEnvironment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", mEnvironment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", mEnvironment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }
}

