package book.hibernate.chapter03;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

@Configuration
public class HibernateConfiguration {

	@Value("#{dataSource}")
	private DataSource dataSource;
	
	@Value("#{hibernate['hibernate.connection.driver_class']}")
	private String driverClass;
	
	@Value("#{hibernate['hibernate.connection.url']}")
	private String url;
	
	@Value("#{hibernate['hibernate.connection.username']}")
	private String username;
	
	@Value("#{hibernate['hibernate.connection.password']}")
	private String password;
	
	@Value("#{hibernate['hibernate.dialect']}")
	private String dialect;
	
	@Value("#{hibernate['hibernate.hbm2ddl.auto']}")
	private String hbm2ddlAuto;
	
	@Value("#{hibernate['hibernate.format_sql']}")
	private String formatSql;
	
	@Value("#{hibernate['hibernate.show_sql']}")
	private String showSql;
	
	@Value("#{hibernate['hibernate.use_sql_comments']}")
	private String useSqlComments;
	
	@Bean
	public AnnotationSessionFactoryBean sessionFactoryBean() {
		Properties props = new Properties();
		props.put("hibernate.dialect", dialect);
		props.put("hibernate.connection.driver_class", driverClass);
		props.put("hibernate.connection.url", url);
		props.put("hibernate.connection.username", username);
		props.put("hibernate.connection.password", password);
		props.put("hibernate.hbm2ddl.auto", "create-drop");
		props.put("hibernate.format_sql", "true");
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.use_sql_comments", "true");

		AnnotationSessionFactoryBean bean = new AnnotationSessionFactoryBean();
		bean.setAnnotatedClasses(new Class[]{Item.class, Order.class});		
		bean.setHibernateProperties(props);
		bean.setDataSource(this.dataSource);
		bean.setSchemaUpdate(true);
		return bean;
	}
	
	/*
	@Bean
	public LocalSessionFactoryBean sessionFactoryBean() {
		Properties props = new Properties();
		props.put("hibernate.dialect", H2Dialect.class.getName());
		props.put("hibernate.format_sql", "true");
		
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setConfigLocations(new Resource[] {  });
		bean.setMappingResources(new String[] {});
		bean.setHibernateProperties(props);
		bean.setDataSource(dataSource);
		bean.setSchemaUpdate(true);
		return bean;
	}
	*/

	@Bean
	public HibernateTransactionManager transactionManager() {
		return new HibernateTransactionManager( sessionFactoryBean().getObject() );
	}

}
