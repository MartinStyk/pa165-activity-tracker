package cz.muni.fi.pa165.tracker.servicetest;

import cz.muni.fi.pa165.tracker.ServiceConfiguration;
import cz.muni.fi.pa165.tracker.exception.ActivityTrackerDataAccessException;
import cz.muni.fi.pa165.tracker.service.ActivityReportService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.inject.Inject;

/**
 * @author Martin Styk
 * @version 08.11.2016
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class TransalteTest extends AbstractTestNGSpringContextTests {

    @Inject
    ActivityReportService activityReportService;

//    @Test(expectedExceptions = ActivityTrackerDataAccessException.class)
//    public void translate() {
//        activityReportService.create(null);
//    }
}
