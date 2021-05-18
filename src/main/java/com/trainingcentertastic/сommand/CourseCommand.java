package com.trainingcentertastic.сommand;

import com.trainingcentertastic.entity.Course;
import com.trainingcentertastic.entity.Student;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.CourseService;
import com.trainingcentertastic.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CourseCommand implements Command {
    private final String SPLITTER = "\\.";

    private final CourseService courseService;
    private final StudentService studentService;

    public CourseCommand(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException, DaoException, com.google.protobuf.ServiceException {
        HttpSession session = request.getSession();

        String name = request.getParameter("nameCourse");

        Course course = courseService.getCourseByName(name).get();
        List<String> requirements = parseRequirement(course.getRequirement());


        session.setAttribute("nameCourse", course.getName());
        session.setAttribute("requirements", requirements);

        return CommandResult.forward("WEB-INF/view/course.jsp");
    }

    private List<String> parseRequirement(String requirement) {
        String[] template = requirement.split(SPLITTER);

        for(String temp : template) {
           temp.trim();
        }

        List<String> requirements = Arrays.asList(template);
        return requirements;
    }


}
