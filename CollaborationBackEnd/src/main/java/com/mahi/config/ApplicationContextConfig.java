package com.mahi.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.mahi"},excludeFilters={@ComponentScan.Filter(value=Configuration.class)})
@EnableTransactionManagement
public class ApplicationContextConfig 
{

	@Bean(name = "dataSource")
	public DataSource getH2DataSource()
	{
		System.out.println("i am in datasource");
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("mahitha");
		dataSource.setPassword("password1");
		
		return dataSource;
		
	}
	
	@Autowired
	@Bean
	public LocalSessionFactoryBean getSessionFactory(DataSource dataSource)
	{
		
		System.out.println("i am in sessiofactory");
		LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setHibernateProperties(getHibernateProperties());
		sessionFactory.setPackagesToScan(new String[] {"com.mahi.model"});
	
		return sessionFactory;
	}
	
	@Bean
	public Properties getHibernateProperties()
	{
		System.out.println("i am in properties");
		Properties properties=new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.default_schema", "mahitha");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		
		return properties;
	}
	
	
	@Autowired
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		
		System.out.println("i m in transx");
		HibernateTransactionManager transactionManager=new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		
		return transactionManager;
	}


	
}
