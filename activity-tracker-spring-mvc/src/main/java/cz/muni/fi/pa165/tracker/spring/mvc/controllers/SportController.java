package cz.muni.fi.pa165.tracker.spring.mvc.controllers;

import cz.muni.fi.pa165.tracker.dto.SportActivityCreateDTO;
import cz.muni.fi.pa165.tracker.dto.SportActivityDTO;
import cz.muni.fi.pa165.tracker.dto.SportActivityUpdateDTO;
import cz.muni.fi.pa165.tracker.exception.NonExistingEntityException;
import cz.muni.fi.pa165.tracker.facade.SportActivityFacade;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Styk
 * @version 12.4.2016
 */
@Controller
@RequestMapping(value = {"/sports"})
public class SportController {

    private static final Logger log = LoggerFactory.getLogger(SportController.class);

    @Inject
    private SportActivityFacade sportFacade;

    @Inject
    private MessageSource messageSource;

    /**
     * List sports in database.
     * <p>
     * If name is not specified, all sports are returned.
     * If name is specified, only sport with given name is returned
     *
     * @param model
     * @param sportName if specified, only sport with given name is returned
     * @return jsp template sports/index
     */
    @RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
    public String index(Model model,
                        @RequestParam(value = "sportName", required = false) String sportName) {

        List<SportActivityDTO> sports = null;

        if (sportName == null || sportName.isEmpty()) {
            sports = sportFacade.getAllSportActivities();
        } else {
            sports = new ArrayList<>();
            try {
                sports.add(sportFacade.getSportActivityByName(sportName));
            } catch (NonExistingEntityException | IllegalArgumentException e) {
                log.debug("Sport " + sportName + " not found", e);
            }
        }

        model.addAttribute("sports", sports);

        return "sports/index";
    }

    /**
     * Initialize form for creation of new sport
     *
     * @param model
     * @return jsp template sports/create
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("sportCreate", new SportActivityCreateDTO());
        return "sports/create";
    }

    /**
     * Handles the creation of new sport
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(
            @Valid @ModelAttribute("sportCreate") SportActivityCreateDTO formData,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriComponentsBuilder) {

        log.debug("create sport({})", formData);

        //if there are any validation errors forward back to the the form
        if (bindingResult.hasErrors()) {
            addValidationErrors(bindingResult, model);
            return "/sports/create";
        }

        Long id = null;
        try {
            id = sportFacade.createSportActivity(formData);
            redirectAttributes.addFlashAttribute("alert_success", "Sport with id " + id + " created");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alert_danger", "This sport can not be created");
            log.error("Sport not created", e);
        }
        return "redirect:" + uriComponentsBuilder.path("/sports").buildAndExpand(id).encode().toUriString();
    }

    /**
     * Initialize form for update of new sport with given id
     *
     * @param model
     * @return jsp template sports/update
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        SportActivityDTO found = sportFacade.getSportActivityById(id);

        SportActivityUpdateDTO updateDTO = new SportActivityUpdateDTO();
        updateDTO.setId(found.getId());
        updateDTO.setName(found.getName());
        updateDTO.setCaloriesFactor(found.getCaloriesFactor());

        model.addAttribute("sportUpdate", found);

        return "sports/update";
    }

    /**
     * Handles the update of sport
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(
            @Valid @ModelAttribute("sportUpdate") SportActivityUpdateDTO formData,
            BindingResult bindingResult,
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder) {

        log.debug("update sport({})", formData);

        if (bindingResult.hasErrors()) {
            addValidationErrors(bindingResult, model);
            return "/sports/update";
        }

        try {
            sportFacade.updateSportActivity(formData);
            redirectAttributes.addFlashAttribute("alert_success", "Sport " + formData.getName() + " updated");
        } catch (NonExistingEntityException | IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("alert_danger", "Sport can not be updated, " +
                    "because it doesn't exist");
            log.error("Sport not updated", e);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alert_danger", "Sport can not be updated");
            log.error("Sport not updated", e);
        }

        return "redirect:" + uriBuilder.path("/sports").build().encode().toUriString();
    }

    /**
     * Removes sport with given id
     *
     * @param model
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
    public String remove(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder,
                         RedirectAttributes redirectAttributes) {

        try {
            sportFacade.removeSportActivity(id);
            redirectAttributes.addFlashAttribute("alert_success", "Sport with id " + id + " deleted");
        } catch (NonExistingEntityException | IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("alert_danger", "Sport can not be deleted, " +
                    "since it doesn't exist");
            log.error("Sport not deleted", e);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alert_danger", "Sport can not be deleted, " +
                    "there are sport activities referencing it.");
            log.error("Sport not deleted", e);
        }

        return "redirect:" + uriBuilder.path("/sports").toUriString();
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
