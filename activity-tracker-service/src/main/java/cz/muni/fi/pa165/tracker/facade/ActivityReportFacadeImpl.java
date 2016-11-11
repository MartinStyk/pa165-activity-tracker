package cz.muni.fi.pa165.tracker.facade;

import cz.muni.fi.pa165.tracker.dto.ActivityReportCreateDTO;
import cz.muni.fi.pa165.tracker.dto.ActivityReportDTO;
import cz.muni.fi.pa165.tracker.dto.ActivityReportUpdateDTO;
import cz.muni.fi.pa165.tracker.entity.ActivityReport;
import cz.muni.fi.pa165.tracker.entity.User;
import cz.muni.fi.pa165.tracker.mapping.BeanMappingService;
import cz.muni.fi.pa165.tracker.service.ActivityReportService;
import cz.muni.fi.pa165.tracker.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of {@link ActivityReportFacade}.
 *
 * @author Martin Styk
 * @version 07.11.2016
 */
@Service
@Transactional
public class ActivityReportFacadeImpl implements ActivityReportFacade {

    @Inject
    private ActivityReportService activityReportService;

    @Inject
    private BeanMappingService beanMappingService;

    @Inject
    private UserService userService;

//    @Inject
//    private SportService sportService;

    @Override
    public Long createActivityReport(ActivityReportCreateDTO activityReportDTO) {
        if (activityReportDTO == null) {
            throw new IllegalArgumentException("activity report cannot be null");
        }
        ActivityReport activityReport = beanMappingService.mapTo(activityReportDTO, ActivityReport.class);
//        activityReport.setSportActivity(sportService.findById(activityReportDTO.getSportActivityId()));
        activityReport.setUser(userService.findById(activityReportDTO.getUserId()));
        activityReportService.create(activityReport);
        return activityReport.getId();
    }

    @Override
    public void updateActivityReport(ActivityReportUpdateDTO activityReportDTO) {
        if (activityReportDTO == null) {
            throw new IllegalArgumentException("activity report cannot be null");
        }
        ActivityReport activityReport = beanMappingService.mapTo(activityReportDTO, ActivityReport.class);
        activityReport.setUser(userService.findById(activityReportDTO.getId()));
//        activityReport.setSportActivity(sportService.findById(activityReportDTO.getSportActivity()));
        activityReportService.update(activityReport);
    }

    @Override
    public List<ActivityReportDTO> getAllActivityReports() {
        return beanMappingService.mapTo(activityReportService.findAll(), ActivityReportDTO.class);
    }

    @Override
    public ActivityReportDTO getActivityReportById(Long activityReportId) {
        return beanMappingService.mapTo(activityReportService.findById(activityReportId), ActivityReportDTO.class);
    }

    @Override
    public List<ActivityReportDTO> getActivityReportsByUser(Long userId) {
        User user = userService.findById(userId);
        return beanMappingService.mapTo(activityReportService.findByUser(user), ActivityReportDTO.class);
    }

    @Override
    public List<ActivityReportDTO> getActivityReportsBySport(Long sportId) {
//        SportActivity sportActivity = sportService.findById(sportId);
//        return beanMappingService.mapTo(activityReportService.findBySport(sportActivity), ActivityReportDTO.class);
        return null;
    }

    @Override
    public void removeActivityReport(Long activityReportId) {
        activityReportService.remove(new ActivityReport(activityReportId));
    }

    @Override
    public void removeActivityReportsOfUser(Long userId) {
        activityReportService.removeActivityReportsOfUser(new User(userId));
    }
}
