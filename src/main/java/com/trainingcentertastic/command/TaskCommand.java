package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.Homework;
import com.trainingcentertastic.entity.Task;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.HomeworkService;
import com.trainingcentertastic.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TaskCommand implements Command {

    private static final String PAGE = "WEB-INF/view/task.jsp";
    private final TaskService taskService;
    private final HomeworkService homeworkService;

    public TaskCommand(TaskService taskService, HomeworkService homeworkService) {
        this.taskService = taskService;
        this.homeworkService = homeworkService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();

        String taskName = request.getParameter("taskName");
        String nameCourse = request.getParameter("nameCourse");
        String command = request.getParameter("command");
        String username = (String) session.getAttribute("username");

        if (taskName == null || nameCourse == null) {
            taskName = (String) session.getAttribute("taskName");
            nameCourse = (String) session.getAttribute("nameCourse");
        }
        try {
            homeworkService.createHomework(taskName, username, nameCourse);
        } catch (ServiceException e) {
        }

        Task task = taskService.getTaskByName(taskName, nameCourse).get();
        Homework homework = homeworkService.getHomeworkStudent(taskName, username, nameCourse).get();

        request.setAttribute("mark", homework.getMark());
        request.setAttribute("link", homework.getLink());
        request.setAttribute("review", homework.getReview());

        session.setAttribute("assignment", task.getAssignment());
        session.setAttribute("taskName", taskName);
        request.setAttribute("command", command);
        return CommandResult.forward(PAGE);
    }
}
