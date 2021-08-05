package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.Role;
import com.trainingcentertastic.entity.User;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class NewCoursePageCommand implements Command {
    private static final String PAGE = "WEB-INF/view/newCourse.jsp";
    private final UserService userService;

    public NewCoursePageCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<User> teachers = userService.getAllByRole(Role.TEACHER);
        request.getSession().setAttribute("teachers", teachers);
        return CommandResult.forward(PAGE);
    }
}
