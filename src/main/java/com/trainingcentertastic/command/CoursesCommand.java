package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.Course;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CoursesCommand implements Command {

    public static final String PAGE = "WEB-INF/view/courses.jsp";
    private final CourseService service;

    public CoursesCommand(CourseService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        HttpSession session = request.getSession();

        int page = 1;
        int recordsPerPage = 3;
        String requestPage = request.getParameter("page");
        String command = request.getParameter("command");

        if(requestPage != null && !"".equals(requestPage)) {
            page = Integer.parseInt(requestPage);
        }

        List<Course> courses = service.getLimit((page - 1) * recordsPerPage, recordsPerPage);

        int noOfRecords = service.getAll().size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        session.setAttribute("courses", courses);
        session.setAttribute("noOfPages",noOfPages);
        session.setAttribute("currentPage", page);
        session.setAttribute("command", command);

        return CommandResult.forward(PAGE);
    }
}
