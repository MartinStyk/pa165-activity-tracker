package cz.muni.fi.pa165.tracker.data.sample;

import cz.muni.fi.pa165.tracker.configuration.ServiceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Martin Styk
 * @version 29.11.2016
 */

@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = SampleDataLoadingFacade.class)
public class SampleDataConfiguration {
}
