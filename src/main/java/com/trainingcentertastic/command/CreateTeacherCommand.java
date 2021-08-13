package com.trainingcentertastic.command;

import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.UserService;
import com.trainingcentertastic.validator.NameValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateTeacherCommand implements Command {
    private static final String PAGE = "WEB-INF/view/teachers.jsp";
    private final UserService userService;

    public CreateTeacherCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String username = request.getParameter("nameTeacher");
        String password = request.getParameter("passwordTeacher");

        if (username == null || password == null) {
            return CommandResult.forward(PAGE);
        }

        if(!NameValidator.checkName(username)) {
            request.setAttribute("invalidName", "Invalid name");
            return CommandResult.forward(PAGE);
        }

        try {
            userService.addTeacher(username, password);
            request.setAttribute("successCreateTeacher", "The teacher was created!");
        } catch (ServiceException e) {
            request.setAttribute("unSuccessCreateTeacher", "The teacher was not created!");
        }

        return CommandResult.forward(PAGE);
    }
}
