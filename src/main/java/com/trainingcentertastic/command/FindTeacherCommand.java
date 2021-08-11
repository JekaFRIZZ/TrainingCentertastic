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

public class FindTeacherCommand implements Command {
    private final UserService userService;
    private final CourseService courseService;

    public FindTeacherCommand(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
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
            List<Course> courses = courseService.getTaughtTeacherCoursesByUsername(username);
            request.setAttribute("foundTeacher", username);
            request.setAttribute("taughtCourses", courses);
        }
        else {
            request.setAttribute("notExist", "Teacher isn`t exist");
        }
        return CommandResult.forward("WEB-INF/view/teachers.jsp");
    }
}
