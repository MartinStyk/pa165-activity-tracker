package cz.muni.fi.pa165.tracker.spring.mvc.config;

import cz.muni.fi.pa165.tracker.data.sample.SampleDataConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.validation.Validator;

/**
 * Configuration of Spring MVC
 *
 * @author Adam Laurenčík
 */
@Configuration
@EnableWebMvc
@Import({SampleDataConfiguration.class})
@ComponentScan("cz.muni.fi.pa165.tracker.spring.mvc")
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

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
}
