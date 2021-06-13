package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.User;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SubjectTaughtCommand implements Command {

    private static final String PAGE = "WEB-INF/view/subjectTaught.jsp";
    private final UserService userService;

    public SubjectTaughtCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();

        String nameCourse = request.getParameter("nameCourse");
        if(nameCourse == null) {
            nameCourse = (String) session.getAttribute("nameCourse");
        }

        List<User> students = userService.getStudentsByCourseName(nameCourse);

        session.setAttribute("students", students);
        session.setAttribute("nameCourse", nameCourse);

        return CommandResult.forward(PAGE);
    }
}
