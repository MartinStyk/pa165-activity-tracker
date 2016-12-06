package cz.muni.fi.pa165.tracker.spring.mvc.controllers;

import cz.muni.fi.pa165.tracker.dto.UserDTO;
import cz.muni.fi.pa165.tracker.facade.UserFacade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Parent controller for all applicaiton controllers
 */
public class ActivityTrackerController {

    @Inject
    private UserFacade userFacade;

    @ModelAttribute("loggedUser")
    public UserDTO getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        String name = authentication.getName();

        if (name == null || name.equals("anonymousUser")) {
            return null;
        }

        UserDTO user = userFacade.findUserByEmail(name);
        return user;
    }

    @ModelAttribute("isAdmin")
    public boolean isUserAdmin(HttpServletRequest request) {
        return request.isUserInRole("ROLE_ADMIN");
    }

}

