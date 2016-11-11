package cz.muni.fi.pa165.tracker;


import cz.muni.fi.pa165.tracker.dto.UserCreateDTO;
import cz.muni.fi.pa165.tracker.dto.UserDTO;
import cz.muni.fi.pa165.tracker.entity.User;
import cz.muni.fi.pa165.tracker.exception.DataAccessExceptionTranslateAspect;
import cz.muni.fi.pa165.tracker.facade.ActivityReportFacadeImpl;
import cz.muni.fi.pa165.tracker.mapping.BeanMappingService;
import cz.muni.fi.pa165.tracker.mapping.BeanMappingServiceImpl;
import cz.muni.fi.pa165.tracker.service.ActivityReportServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldDefinition;
import org.dozer.loader.api.TypeMappingOptions;
import org.springframework.context.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Styk
 * @version 08.11.2016
 */
@Configuration
@EnableAspectJAutoProxy
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackageClasses = {ActivityReportServiceImpl.class, ActivityReportFacadeImpl.class,
        DataAccessExceptionTranslateAspect.class, BeanMappingServiceImpl.class})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        // this is needed to support Java 8 time api with Dozer
        List<String> mappingFiles = new ArrayList();
        mappingFiles.add("dozerJdk8Converters.xml");

        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.setMappingFiles(mappingFiles);
        return dozer;
    }
}
