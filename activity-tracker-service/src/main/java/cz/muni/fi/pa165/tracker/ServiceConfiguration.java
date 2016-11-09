package cz.muni.fi.pa165.tracker;


import cz.muni.fi.pa165.tracker.exception.DataAccessExceptionTranslateAspect;
import cz.muni.fi.pa165.tracker.facade.ActivityReportFacadeImpl;
import cz.muni.fi.pa165.tracker.service.ActivityReportServiceImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * @author Martin Styk
 * @version 08.11.2016
 */
@Configuration
@EnableAspectJAutoProxy
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackageClasses = {ActivityReportServiceImpl.class, ActivityReportFacadeImpl.class,
        DataAccessExceptionTranslateAspect.class})
public class ServiceConfiguration {
}
