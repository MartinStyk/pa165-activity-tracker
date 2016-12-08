package cz.muni.fi.pa165.tracker.spring.mvc.controllers;

import cz.muni.fi.pa165.tracker.dto.UserDTO;
import cz.muni.fi.pa165.tracker.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * @author Petra Onrejkova
 * @version 8.12.2016
 */
@Controller
@RequestMapping(value = {"/settings"})
public class UserController extends ActivityTrackerController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Inject
    private UserFacade userFacade;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String settings(Model model) {
        log.debug("update userUpdate({})", getLoggedUser());
        return "user/settings";
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String settings(
            @Valid @ModelAttribute("loggedUser") UserDTO formData,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        UserDTO notUpdated = getLoggedUser();
        formData.setId(notUpdated.getId());

        log.debug("update user({})", formData);

        if (bindingResult.hasErrors()) {
            addValidationErrors(bindingResult, model);
            return "user/settings";
        }

        userFacade.updateUser(formData);
        redirectAttributes.addFlashAttribute("alert_success", "User " + formData.getEmail() + " was updated");

        return "redirect:";
    }
}
