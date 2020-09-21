package com.zx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan("com.zx")
public class LoginConfig {

    private ServletContext servletContext;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.2.139:3306/bookstore?useUnicode=true&amp;characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

//    @Bean
//    public ViewResolver viewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/views/");
//        resolver.setSuffix(".jsp");
//        resolver.setExposeContextBeansAsAttributes(true);
//        resolver.setViewClass(JstlView.class);
//        resolver.setExposePathVariables(true);
//
//        return resolver;
//    }
//
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }
//
//    @Override
//    public void setServletContext(ServletContext servletContext) {
//        this.servletContext = servletContext;
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/");
//        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/statics/images/");
//        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/statics/css/");
//        super.addResourceHandlers(registry);
//    }
}
