package com.imin.example.conf.web;

import com.imin.example.conf.ParamConfig;
import com.imin.infrastructure.common.mapper.JsonMapper;
import com.imin.infrastructure.common.spring.SpringContextHolder;
import com.imin.infrastructure.common.spring.config.CorsConfig;
import com.imin.infrastructure.common.spring.config.RedisConfig;
import com.imin.infrastructure.common.spring.config.ValidatorMessageInterpolatorAdapter;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author code
 * @version V1.0
 * @Title: 基础代码
 * @Description: 基础信息
 **/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.imin.example.controller", useDefaultFilters = false, includeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {
				Controller.class, ControllerAdvice.class})})
@Import({RedisConfig.class, CorsConfig.class, ParamConfig.class})
public class WebAppConfig extends WebMvcConfigurerAdapter {
	@Bean
	public MappingJackson2HttpMessageConverter converter() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.configure(new JsonMapper());
		builder.indentOutput(true);
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter(
				builder.build());
		jsonMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
		return jsonMessageConverter;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// registry.addInterceptor(new WebInterceptor()).addPathPatterns("/**");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		//支持Protobuf
		converters.add(new ProtobufHttpMessageConverter());
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public Validator getValidator() {

		return localValidatorFactoryBean();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}


	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}

	@Bean
	public SpringContextHolder springContextHolder() {
		return new SpringContextHolder();
	}

	@Bean
	public LocalValidatorFactoryBean localValidatorFactoryBean() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setProviderClass(HibernateValidator.class);
		localValidatorFactoryBean.setValidationMessageSource(resourceBundleMessageSource());
		localValidatorFactoryBean.setMessageInterpolator(new ValidatorMessageInterpolatorAdapter(resourceBundleMessageSource()));
		return localValidatorFactoryBean;
	}

	@Bean
	public ResourceBundleMessageSource resourceBundleMessageSource() {
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setBasenames(
				"i18n/validation/ValidationMessages", "i18n/message/messages");

		resourceBundleMessageSource.setCacheSeconds(120);
		resourceBundleMessageSource.setDefaultEncoding("UTF-8");
		return resourceBundleMessageSource;
	}
}
