package com.itmoclouddev.labf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
// import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages="com.itmoclouddev.labf")
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer {
	@Autowired
	private ApplicationContext applicationContext;

	@Bean
    public ViewResolver viewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(springTemplateEngine());
		return resolver;
	}

	@Bean
	public SpringTemplateEngine springTemplateEngine() {
	  SpringTemplateEngine engine = new SpringTemplateEngine();
	  engine.setEnableSpringELCompiler(true);
	  engine.setTemplateResolver(templateResolver());
	  return engine;
	}

	@Bean
    public SpringResourceTemplateResolver templateResolver() {
	  SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
	  resolver.setApplicationContext(this.applicationContext);
	  resolver.setPrefix("/WEB-INF/views/");
	  resolver.setSuffix(".html");
	  resolver.setTemplateMode(TemplateMode.HTML);
	  return resolver;
	}
	
	// @Bean(name = "viewResolver")
    // public InternalResourceViewResolver getViewResolver() {
    //     InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    //     viewResolver.setPrefix("classpath:/WEB-INF/views/");
    //     viewResolver.setSuffix(".html");
    //     return viewResolver;
    // }

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/views/static/");
	}

	@Bean
	public MultipartResolver multipartResolver(){
		return new CommonsMultipartResolver();
	}
}
