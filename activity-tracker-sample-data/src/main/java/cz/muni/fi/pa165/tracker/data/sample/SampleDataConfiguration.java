package cz.muni.fi.pa165.tracker.data.sample; /**
 * @author Martin Styk
 * @version 29.11.2016
 */

import cz.muni.fi.pa165.tracker.configuration.ServiceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;

@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = SampleDataLoadingFacade.class)
public class SampleDataConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleDataConfiguration.class);

    @Inject
    private SampleDataLoadingFacade sampleDataLoadingFacade;

    @PostConstruct
    public void dataLoading() throws IOException {
        LOGGER.debug("dataLoading()");
        sampleDataLoadingFacade.loadData();
    }
}
