package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.User;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class FindStudentCommand implements Command{

    private static final String PAGE = "WEB-INF/view/students.jsp";
    private final UserService userService;

    public FindStudentCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String nameStudent = request.getParameter("nameStudent");

        if(nameStudent == null) {
            return CommandResult.forward(PAGE);
        }


        Optional<User> student = userService.getUserByUsername(nameStudent);

        if(student.isPresent()) {
            Long id = student.get().getId();
            String username = student.get().getUsername();
            request.setAttribute("id", id);
            request.setAttribute("username", username);
        }
        else {
            request.setAttribute("notExist", "Student isn`t exist");
        }

        return CommandResult.forward(PAGE);
    }
}
