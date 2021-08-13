package com.trainingcentertastic.command;

import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.HomeworkService;
import com.trainingcentertastic.service.TaskService;
import com.trainingcentertastic.validator.NameValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateTaskCommand implements Command {
    private static final String PAGE = "WEB-INF/view/subjectTaught.jsp";
    private final TaskService taskService;
    private final HomeworkService homeworkService;

    public CreateTaskCommand(TaskService taskService, HomeworkService homeworkService) {
        this.taskService = taskService;
        this.homeworkService = homeworkService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String courseName = request.getParameter("nameCourse");
        String taskName = request.getParameter("taskName");
        String assignment = request.getParameter("assignment");

        if(courseName == null || taskName == null || assignment == null) {
            return CommandResult.forward("WEB-INF/view/subjectTaught.jsp");
        }

        if(!NameValidator.checkName(taskName)) {
            request.setAttribute("invalidName", "Invalid name");
            return CommandResult.forward(PAGE);
        }

        taskService.createTask(taskName, courseName, assignment);
        request.setAttribute("nameCourse", courseName);
        return CommandResult.forward(PAGE);
    }
}
