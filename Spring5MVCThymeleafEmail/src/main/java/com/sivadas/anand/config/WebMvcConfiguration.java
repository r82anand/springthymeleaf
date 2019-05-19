package com.sivadas.anand.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
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

@Configuration
@EnableWebMvc
@ComponentScan("com.sivadas.anand")
public class WebMvcConfiguration implements WebMvcConfigurer, ApplicationContextAware {
	@Autowired
	ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/*
	 * Dispatcher configuration for serving static resources
	 */
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
	}

	/*
	 * Message externalization/internationalization
	 */
	@Bean
	public ResourceBundleMessageSource messageSource() {
		final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("Messages");
		return messageSource;
	}

	/*
	 * Multipart resolver (needed for uploading attachments from web form)
	 */
	@Bean
	public MultipartResolver multipartResolver() {
		final CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10485760); // 10MBytes
		return multipartResolver;
	}

	// 1. Creating SpringResourceTemplateResolver
	@Bean
	public SpringResourceTemplateResolver springTemplateResolver() {

		SpringResourceTemplateResolver springTemplateResolver = new SpringResourceTemplateResolver();
		springTemplateResolver.setApplicationContext(this.applicationContext);
		springTemplateResolver.setPrefix("/WEB-INF/pages/");
		springTemplateResolver.setSuffix(".html");
//		springTemplateResolver.setTemplateMode("HTML5");
		springTemplateResolver.setTemplateMode(TemplateMode.HTML);
		// Template cache is true by default. Set to false if you want
		// templates to be automatically updated when modified.
		springTemplateResolver.setCacheable(true);
		springTemplateResolver.setCharacterEncoding("UTF-8");

		return springTemplateResolver;
	}

	// 2. Creating SpringTemplateEngine
	@Bean
	public SpringTemplateEngine springTemplateEngine() {

		SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
		springTemplateEngine.setTemplateResolver(springTemplateResolver());
		springTemplateEngine.setEnableSpringELCompiler(true);

		return springTemplateEngine;
	}

	// 3. Registering ThymeleafViewResolver
	@Bean
	public ViewResolver viewResolver() {

		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(springTemplateEngine());

		return viewResolver;
	}
}