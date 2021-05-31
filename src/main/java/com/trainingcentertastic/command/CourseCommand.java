package com.trainingcentertastic.command;

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
import java.util.Optional;

public class CourseCommand implements Command {
    public static final String PAGE = "WEB-INF/view/course.jsp";
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
        if(name == null) {
            name = (String) session.getAttribute("nameCourse");
        }

        Optional<Course> courseOptional = courseService.getCourseByName(name);

        Course course = courseOptional.get();
        String oneStringRequirements = course.getRequirement();

        List<String> requirements = parseRequirement(oneStringRequirements);
        List<Student> students = studentService.getStudentsByCourseName(name);

        session.setAttribute("nameCourse", course.getName());
        session.setAttribute("requirements", requirements);
        session.setAttribute("students", students);

        return CommandResult.forward(PAGE);
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
