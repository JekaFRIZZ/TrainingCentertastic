package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.User;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class DeleteTeacherCommand implements Command {

    private final UserService userService;

    public DeleteTeacherCommand(UserService userService) {
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
            userService.deleteUserByUsername(teacher.get().getId());
            request.setAttribute("deleteMessage", "The teacher was removed");
        }
        else {
            request.setAttribute("deleteMessage", "There is no teacher with that name");
        }

        return CommandResult.forward("WEB-INF/view/teachers.jsp");
    }
}
