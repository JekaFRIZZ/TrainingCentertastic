package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.User;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class FindTeacherCommand implements Command {
    private final UserService userService;

    public FindTeacherCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String nameTeacher = request.getParameter("nameTeacher");

        if(nameTeacher == null) {
            return CommandResult.forward("WEB-INF/view/teachers.jsp");
        }

        Optional<User> teacher = userService.getUserByUsername(nameTeacher);

        if(teacher.isPresent()) {
            String username = teacher.get().getUsername();
            request.setAttribute("foundTeacher", username);
        }
        else {
            request.setAttribute("notExist", "Teacher isn`t exist");
        }
        return CommandResult.forward("WEB-INF/view/teachers.jsp");
    }
}
