package cz.muni.fi.pa165.tracker.spring.mvc.controllers;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;
import cz.muni.fi.pa165.tracker.dto.UserDTO;
import cz.muni.fi.pa165.tracker.exception.NonExistingEntityException;
import cz.muni.fi.pa165.tracker.facade.UserFacade;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author petra
 */
@Controller
@RequestMapping(value = {"/settings"})
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Inject
    private UserFacade userFacade;

    @Inject
    private MessageSource messageSource;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String settings(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        UserDTO found = userFacade.findUserByEmail(name);
        model.addAttribute("userUpdate", found);
        log.debug("update userUpdate({})", found);
        return "user/settings";
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String settings(
            @Valid @ModelAttribute("userUpdate") UserDTO formData,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        UserDTO notUpdated = userFacade.findUserByEmail(name);
        formData.setId(notUpdated.getId());

        log.debug("update user({})", formData);

        if (bindingResult.hasErrors()) {
            addValidationErrors(bindingResult, model);
            return "user/settings";
        }

        try {
            userFacade.updateUser(formData);
            redirectAttributes.addFlashAttribute("alert_success", "User " + formData.getEmail() + " was updated");
        } catch (NonExistingEntityException | IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("alert_danger", " User can not be updated, "
                    + "because it doesn't exist");
            log.error("User not updated", e);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alert_danger", "User can not be updated");
            log.error("User not updated", e);
        }

        return "redirect:";
    }

    private void addValidationErrors(BindingResult bindingResult, Model model) {
        for (ObjectError ge : bindingResult.getGlobalErrors()) {
            log.error("ObjectError: {}", ge);
        }
        for (FieldError fe : bindingResult.getFieldErrors()) {
            model.addAttribute(fe.getField() + "_error", true);
            log.error("FieldError: {}", fe);
        }
    }

}
