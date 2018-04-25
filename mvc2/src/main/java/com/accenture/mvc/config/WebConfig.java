package com.accenture.mvc.config;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.accenture.mvc.converters.LocaleToCountryCode;
import com.accenture.mvc.exception.CustomerNotFoundException;
import com.accenture.mvc.formatter.AddressFormatter;
import com.accenture.mvc.formatter.DateFormatter;
import com.accenture.mvc.interceptor.IPLoggerInterceptor;
import com.accenture.mvc.interceptor.PerfomanceInterceptor;
import com.accenture.mvc.model.Customer;
import com.accenture.mvc.views.CustomerExcelView;
import com.accenture.mvc.views.CustomerXmlViewResolver;
import com.accenture.mvc.views.ExcelViewResolver;
import com.accenture.mvc.views.Jaxb2MarshallingXmlViewResolver;
import com.accenture.mvc.views.JsonViewResolver;
import com.accenture.mvc.views.MyJsonViewResolver;
import com.accenture.mvc.views.PdfViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.accenture.mvc.*")
@PropertySource(value = { "classpath:hibernate.properties" })

public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	Environment environment;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(themeChangeInterceptor());
		registry.addInterceptor(perfomanceInterceptor());
		registry.addInterceptor(ipLoggerInterceptor());
	}

	/***************************************************************/
	/* Hibernate-Config */
	/***************************************************************/

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.accenture.mvc.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

 
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		return properties;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
 		txManager.setSessionFactory(s);
		return txManager;
	}
	 
	/***************************************************************/
	/* End of Hibernate-Config */
	/***************************************************************/

	/***************************************************************/
	/* Config for H2-Console */
	/***************************************************************/
	@Bean(initMethod = "start", destroyMethod = "stop")
	public org.h2.tools.Server h2WebConsonleServer() throws SQLException {
		return org.h2.tools.Server.createWebServer("-web", "-webAllowOthers", "-webDaemon", "-webPort", "8082");
	}

	/***************************************************************/
	/* View Resolvers */
	/***************************************************************/
	@Bean
	public DataSource dataSource() {
		/*DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("asdf");
		return dataSource;*/
		return new EmbeddedDatabaseBuilder().generateUniqueName(false).
				setName("testdb")
				.setType(EmbeddedDatabaseType.H2).addDefaultScripts()
				.setScriptEncoding("UTF-8").ignoreFailedDrops(true)
				.build();
	}
	
	@Bean
	public DataSource oracledb() {
		/*DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("asdf");
		return dataSource;*/
		return new EmbeddedDatabaseBuilder().generateUniqueName(false).
				setName("oracledb")
				.setType(EmbeddedDatabaseType.H2).addDefaultScripts()
				.setScriptEncoding("UTF-8").ignoreFailedDrops(true)
				.build();
	}
/*	@Bean
	public DataSource dataSource1() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("asdf");
		return dataSource;
		return new EmbeddedDatabaseBuilder().generateUniqueName(false).
				setName("testdb")
				.setType(EmbeddedDatabaseType.H2).addDefaultScripts()
				.setScriptEncoding("UTF-8").ignoreFailedDrops(true)
				.build();
	}*/

	/***************************************************************/
	/* View Resolvers */
	/***************************************************************/

	// @Bean
	public BeanNameViewResolver beanNameViewResolver() {
		BeanNameViewResolver viewResolver = new BeanNameViewResolver();
		viewResolver.setOrder(1);
		return viewResolver;
	}

	@Bean
	TilesViewResolver tilesViewResolver() {
		TilesViewResolver tiles = new TilesViewResolver();
		tiles.setOrder(2);
		return tiles;
	}

	@Bean
	TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions("/WEB-INF/views/**/tiles.xml");
		// add more tiles definition files if present
		return tilesConfigurer;
	}

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setSuffix(".jsp");
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setOrder(3);
		return viewResolver;
	}

	/***************************************************************/
	/* View Resolvers */
	/***************************************************************/

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addRedirectViewController("/", "login");
		registry.addViewController("/main").setViewName("main");
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/forgot-password").setViewName("forgot-password");
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/uploadPhoto").setViewName("uploadPhoto");

	}

	@Bean(name = "customer/list.xls")
	public CustomerExcelView customerExcelView() {
		return new CustomerExcelView();
	}

	// @Bean
	public Filter[] getServletFilters() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		return new Filter[] { filter };
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages/message");
		return messageSource;
	}

	/***************************************************************/
	/* ContentNegotiation */
	/***************************************************************/
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.TEXT_HTML);
		configurer.favorPathExtension(true);
		configurer.favorParameter(false);
		configurer.ignoreAcceptHeader(true);
	}

	// configure ContentNegotiationViewResolver
	@Bean
	public ViewResolver contentNegotiationViewResolver(ContentNegotiationManager contentNegotiationManager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(contentNegotiationManager);
		List<ViewResolver> resolvers = new ArrayList<>();
	//	resolvers.add(jsonViewResolver1());
	//	resolver.setViewResolvers(resolvers);
	//	resolver.setOrder(0);
		
		
		
		
		
/////////////////////////////////////////////////////////////////////////
		
		
		resolvers.add(jsonViewResolver());
		resolvers.add(excelViewResolver());
		// resolvers.add(beanNameViewResolver());
		// resolvers.add(xmlViewResolver());
		resolvers.add(xmlViewResolver1());
		resolvers.add(pdfViewResolver());
		
		
		/*
		 * List<View> defaultViews = new ArrayList<>(); defaultViews.add(jsonView());
		 * 
		 * resolver.setDefaultViews(defaultViews);
		 */
		resolver.setOrder(0);
		resolver.setViewResolvers(resolvers);
		return resolver;
	}

	public CustomerXmlViewResolver xmlViewResolver1() {
		return new CustomerXmlViewResolver();
	}

	public MyJsonViewResolver jsonViewResolver1() {
		return new MyJsonViewResolver();
	}
	public PdfViewResolver pdfViewResolver() {
		return new PdfViewResolver();
	}

	// @Bean
	public Jaxb2MarshallingXmlViewResolver xmlViewResolver() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setCheckForXmlRootElement(true);
		marshaller.setClassesToBeBound(Customer.class);
		Jaxb2MarshallingXmlViewResolver xmlViewResolver = new Jaxb2MarshallingXmlViewResolver(marshaller);
		return xmlViewResolver;
	}

	@Bean
	public ExcelViewResolver excelViewResolver() {
		return new ExcelViewResolver();
	}

	@Bean
	public JsonViewResolver jsonViewResolver() {
		return new JsonViewResolver();
	}

	public MappingJackson2JsonView jsonView() {
		return new MappingJackson2JsonView();
	}

	/***************************************************************/
	/* Formatter and Converters */
	/***************************************************************/
	@Bean(name = "localeToCountryCode")
	public LocaleToCountryCode localeToCountryCode() {
		return new LocaleToCountryCode();
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		AddressFormatter addressFormatter = new AddressFormatter();
		addressFormatter.setStyle(AddressFormatter.Style.REGION);

		DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd");
		registry.addFormatter(addressFormatter);
		registry.addFormatter(dateFormatter);

		registry.addConverter(localeToCountryCode());

	}

	/***************************************************************/
	/* Commons MultiPartResolver */
	/***************************************************************/
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(-1);// - 1 indicates no limit;
		return resolver;
	}

	/***************************************************************/
	/* User Defined Interceptor */
	/***************************************************************/
	@Bean
	public PerfomanceInterceptor perfomanceInterceptor() {
		return new PerfomanceInterceptor();
	}

	@Bean
	public IPLoggerInterceptor ipLoggerInterceptor() {
		return new IPLoggerInterceptor();
	}

	/***************************************************************/
	/* Configuring Locales */
	/***************************************************************/

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("language");
		return interceptor;
	}

	/*
	 * @Bean(name = "localeResolver") public SessionLocaleResolver localeResolver()
	 * { return new SessionLocaleResolver(); }
	 */

	@Bean(name = "localeResolver")
	public CookieLocaleResolver localeResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setCookieName("locale");
		resolver.setCookieMaxAge(-1);
		return resolver;
	}

	/************ End of Configuring Locales ***************************/

	/***************************************************************/
	/* Configure Themes */
	/***************************************************************/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

	}

	@Bean
	public ResourceBundleThemeSource themeSource() {
		ResourceBundleThemeSource themeSource = new ResourceBundleThemeSource();
		themeSource.setBasenamePrefix("themes.");
		return themeSource;
	}

	@Bean
	public CookieThemeResolver themeResolver() {
		CookieThemeResolver resolver = new CookieThemeResolver();
		// resolver.setDefaultThemeName("bright");
		resolver.setCookieName("my-theme-cookie");
		return resolver;
	}

	@Bean
	public ThemeChangeInterceptor themeChangeInterceptor() {
		ThemeChangeInterceptor interceptor = new ThemeChangeInterceptor();
		interceptor.setParamName("theme");
		return interceptor;
	}

	/***************************************************************/
	/* End of Configuring Themes */
	/***************************************************************/

	/***************************************************************/
	/* Configure Exception Resolvers */
	/***************************************************************/
	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.setProperty("DatabaseException", "error/databaseError");
		mappings.setProperty(CustomerNotFoundException.class.getName(), "error/defaultError");

		resolver.setExceptionMappings(mappings);
		// resolver.setOrder(1);
		// set default error view
		resolver.setDefaultErrorView("error/defaultError");
		resolver.setDefaultStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return resolver;
	}
	/***************************************************************/
	/* End of Configure Exception Resolvers */
	/***************************************************************/

}
