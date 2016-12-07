package cz.muni.fi.pa165.tracker.spring.mvc.controllers;

import cz.muni.fi.pa165.tracker.exception.NonExistingEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * ControllerAdvice for exception handling
 *
 * @author Martin Styk
 * @version 7.12.2016
 */

@ControllerAdvice
public class ActivityTrackerExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    @ExceptionHandler(NonExistingEntityException.class)
    public ModelAndView handleNonExistingEntityException(NonExistingEntityException ex) {

        ModelAndView model = new ModelAndView("/error");
        model.addObject("errorMessage", ex.getLocalizedMessage());

        return model;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {

        ModelAndView model = new ModelAndView("/error");
        model.addObject("errorMessage", ex.getLocalizedMessage());
        log.error("Unexpected errror", ex);

        return model;
    }
}
