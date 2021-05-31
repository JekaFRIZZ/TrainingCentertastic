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

public class TaskCommand implements Command {

    public static final String PAGE = "WEB-INF/view/task.jsp";
    private final TaskService taskService;

    public TaskCommand(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException, DaoException, com.google.protobuf.ServiceException {
        HttpSession session = request.getSession();

        String taskName = request.getParameter("taskName");
        String nameCourse = request.getParameter("nameCourse");

        if(taskName == null || nameCourse == null) {
            taskName = (String) session.getAttribute("taskName");
            nameCourse = (String) session.getAttribute("nameCourse");
        }

        Task task = taskService.getTaskByName(taskName, nameCourse).get();

        session.setAttribute("assignment", task.getAssignment());
        session.setAttribute("taskName", taskName);
        return CommandResult.forward(PAGE);
    }
}
