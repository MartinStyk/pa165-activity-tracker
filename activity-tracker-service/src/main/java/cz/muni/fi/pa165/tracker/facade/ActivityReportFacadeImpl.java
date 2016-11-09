package cz.muni.fi.pa165.tracker.facade;

import cz.muni.fi.pa165.tracker.dto.ActivityReportCreateDTO;
import cz.muni.fi.pa165.tracker.dto.ActivityReportDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Martin Styk
 * @version 07.11.2016
 */
@Service
@Transactional
public class ActivityReportFacadeImpl implements ActivityReportFacade {
    @Override
    public Long createActivityReport(ActivityReportCreateDTO activityReport) {
        return null;
    }

    @Override
    public void updateActivityReport(ActivityReportDTO activityReport) {

    }

    @Override
    public List<ActivityReportDTO> getAllActivityReports() {
        return null;
    }

    @Override
    public ActivityReportDTO getActivityReportById(Long activityReportId) {
        return null;
    }

    @Override
    public List<ActivityReportDTO> getActivityReportsByUser(Long userId) {
        return null;
    }

    @Override
    public List<ActivityReportDTO> getActivityReportsBySport(Long sportId) {
        return null;
    }

    @Override
    public void removeActivityReport(Long activityReportId) {

    }

    @Override
    public void removeActivityReportsOfUser(Long userId) {

    }
}
