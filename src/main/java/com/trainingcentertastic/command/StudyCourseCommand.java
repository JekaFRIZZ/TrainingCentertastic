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

    private static final String PAGE = "WEB-INF/view/studyCourse.jsp";
    private final TaskService taskService;

    public StudyCourseCommand(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();

        String nameCourse = request.getParameter("nameCourse");
        String command = request.getParameter("command");

        if(nameCourse == null) {
            nameCourse = (String) session.getAttribute("nameCourse");
        }
        List<Task> tasks = taskService.getTasksByCourseName(nameCourse);

        session.setAttribute("tasks", tasks);
        session.setAttribute("nameCourse", nameCourse);
        request.setAttribute("command", command);

        return CommandResult.forward(PAGE);
    }
}
