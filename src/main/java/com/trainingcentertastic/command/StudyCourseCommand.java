package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.Task;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class StudyCourseCommand implements Command {

    public static final String PAGE = "WEB-INF/view/studyCourse.jsp";
    private final TaskService taskService;

    public StudyCourseCommand(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException, DaoException, com.google.protobuf.ServiceException {
        HttpSession session = request.getSession();

        String courseName = request.getParameter("nameCourse");
        if(courseName == null) {
            courseName = (String) session.getAttribute("nameCourse");
        }

        List<Task> tasks = taskService.getTasksByCourseName(courseName);

        session.setAttribute("tasks", tasks);

        return CommandResult.forward(PAGE);
    }
}
