package cz.muni.fi.pa165.tracker.spring.mvc.config;

import cz.muni.fi.pa165.tracker.data.sample.SampleDataConfiguration;
import cz.muni.fi.pa165.tracker.data.sample.SampleDataLoadingFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.validation.Validator;
import java.io.IOException;

/**
 * Configuration of Spring MVC
 *
 * @author Adam Laurenčík
 */
@Configuration
@EnableWebMvc
@Import({SampleDataConfiguration.class, WebApplicationSecurityConfiguration.class})
@ComponentScan("cz.muni.fi.pa165.tracker.spring.mvc")
public class WebApplicationConfiguration extends WebMvcConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(WebApplicationConfiguration.class);

    @Inject
    private SampleDataLoadingFacade sampleDataLoadingFacade;

    /**
     * Provides mapping from view names to JSP pages in WEB-INF/jsp directory.
     */
    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    /**
     * Handling of resources
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/*");
    }

    /**
     * Enables default Tomcat servlet that serves static files.
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * Provides JSR-303 Validator.
     */
    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    /**
     * Provides localized texts.
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("Texts");
        return messageSource;
    }

    /**
     * We need to load data here, It is node on application startup. It can not be done in other configuration layer
     * because, here we need to load users and determine their types in {@link WebApplicationSecurityConfiguration}
     *
     * @throws IOException
     */
    @PostConstruct
    public void dataLoading() throws IOException {
        sampleDataLoadingFacade.loadData();
    }
}
