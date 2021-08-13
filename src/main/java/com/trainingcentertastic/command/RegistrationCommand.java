package com.trainingcentertastic.command;

import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.UserService;
import com.trainingcentertastic.validator.NameValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {

    private static final String PAGE = "WEB-INF/view/registration.jsp";
    private final UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String username = request.getParameter("username");
        String firstPassword = request.getParameter("firstPassword");
        String secondPassword = request.getParameter("secondPassword");

        if (username == null || firstPassword == null || secondPassword == null) {
            return CommandResult.forward(PAGE);
        }

        if(!NameValidator.checkName(username)) {
            request.setAttribute("invalidName", "Invalid name");
            return CommandResult.forward(PAGE);
        }

        if (firstPassword.equals(secondPassword)) {
            try {
                userService.addStudent(username, firstPassword);
            } catch (ServiceException e) {
                request.setAttribute("errorMessage", "username is already taken");
                return CommandResult.forward("WEB-INF/view/registration.jsp");
            }

            return CommandResult.forward("index.jsp");
        }
        request.setAttribute("errorMessage", "passwords are not equals");
        return CommandResult.forward(PAGE);
    }
}
