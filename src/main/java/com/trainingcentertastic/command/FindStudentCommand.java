package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.Course;
import com.trainingcentertastic.entity.User;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.CourseService;
import com.trainingcentertastic.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class FindStudentCommand implements Command{

    private static final String PAGE = "WEB-INF/view/students.jsp";
    private final UserService userService;
    private final CourseService courseService;

    public FindStudentCommand(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String nameStudent = request.getParameter("nameStudent");

        if(nameStudent == null) {
            return CommandResult.forward(PAGE);
        }


        Optional<User> student = userService.getUserByUsername(nameStudent);

        if(student.isPresent()) {
            String username = student.get().getUsername();
            List<Course> courses = courseService.getCoursesByUsername(username);
            request.setAttribute("name", username);
            request.setAttribute("courses", courses);
        }
        else {
            request.setAttribute("notExist", "Student isn`t exist");
        }

        return CommandResult.forward(PAGE);
    }
}
