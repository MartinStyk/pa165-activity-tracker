package cz.muni.fi.pa165.tracker.spring.mvc.controllers;

import cz.muni.fi.pa165.tracker.dto.ActivityReportCreateDTO;
import cz.muni.fi.pa165.tracker.dto.ActivityReportDTO;
import cz.muni.fi.pa165.tracker.dto.ActivityReportUpdateDTO;
import cz.muni.fi.pa165.tracker.exception.NonExistingEntityException;
import cz.muni.fi.pa165.tracker.facade.ActivityReportFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * @author Adam Laurencik
 * @version 4.12.2016
 */
@Controller
@RequestMapping(value = {"/reports"})
public class ReportController {

    private static final Logger log = LoggerFactory.getLogger(SportController.class);

    @Inject
    private ActivityReportFacade activityReportFacade;

    @Inject
    private MessageSource messageSource;

    /**
     * Default view for reports section
     *
     * @param model
     * @return jsp template reports/index
     */
    @RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        return "reports/index";
    }

    /**
     * Initialize form for creation of new report
     *
     * @param model
     * @return jsp template reports/create
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("reportCreate", new ActivityReportCreateDTO());
        return "reports/create";
    }

    /**
     * Handles the creation of new report
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(
            @Valid @ModelAttribute("reportCreate") ActivityReportCreateDTO formData,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriComponentsBuilder) {

        log.debug("create report({})", formData);

        //if there are any validation errors forward back to the the form
        if (bindingResult.hasErrors()) {
            addValidationErrors(bindingResult, model);
            return "/reports/create";
        }

        Long id = null;
        try {
            id = activityReportFacade.createActivityReport(formData);
            redirectAttributes.addFlashAttribute("alert_success", "Activity report with id " + id + " created");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alert_danger", "This activity can not be created");
            log.error("Activity not created", e);
        }
        return "redirect:" + uriComponentsBuilder.path("/reports").buildAndExpand(id).encode().toUriString();
    }

    /**
     * Initialize form for update of new report with given id
     *
     * @param model
     * @return jsp template reports/update
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        ActivityReportDTO found = activityReportFacade.getActivityReportById(id);

        ActivityReportUpdateDTO updateDTO = new ActivityReportUpdateDTO();
        updateDTO.setId(found.getId());
        model.addAttribute("reportUpdate", found);

        return "reports/update";
    }

    /**
     * Handles the update of report
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(
            @Valid @ModelAttribute("reportUpdate") ActivityReportUpdateDTO formData,
            BindingResult bindingResult,
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder) {

        log.debug("update activityReport({})", formData);

        if (bindingResult.hasErrors()) {
            addValidationErrors(bindingResult, model);
            return "/reports/update";
        }

        try {
            activityReportFacade.updateActivityReport(formData);
            redirectAttributes.addFlashAttribute("alert_success", "Report " + formData.getId() + " updated");
        } catch (NonExistingEntityException | IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("alert_danger", "Report can not be updated, "
                    + "because it doesn't exist");
            log.error("Report not updated", e);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alert_danger", "Report can not be updated");
            log.error("Report not updated", e);
        }

        return "redirect:" + uriBuilder.path("/reports").build().encode().toUriString();
    }

    /**
     * Removes report with given id
     *
     * @param model
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
    public String remove(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder,
            RedirectAttributes redirectAttributes) {

        try {
            activityReportFacade.removeActivityReport(id);
            redirectAttributes.addFlashAttribute("alert_success", "Report with id " + id + " deleted");
        } catch (NonExistingEntityException | IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("alert_danger", "Report can not be deleted, "
                    + "since it doesn't exist");
            log.error("Report not deleted", e);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alert_danger", "Report can not be deleted, "
                    + "there are Report activities referencing it.");
            log.error("Report not deleted", e);
        }

        return "redirect:" + uriBuilder.path("/reports").toUriString();
    }

    protected void addValidationErrors(BindingResult bindingResult, Model model) {
        for (ObjectError ge : bindingResult.getGlobalErrors()) {
            log.error("ObjectError: {}", ge);
        }
        for (FieldError fe : bindingResult.getFieldErrors()) {
            model.addAttribute(fe.getField() + "_error", true);
            log.error("FieldError: {}", fe);
        }
    }
}