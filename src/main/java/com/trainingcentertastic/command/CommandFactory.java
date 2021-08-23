package com.trainingcentertastic.command;

import com.trainingcentertastic.dao.DaoHelperFactory;
import com.trainingcentertastic.parser.RequirementParser;
import com.trainingcentertastic.service.*;
import org.apache.log4j.Logger;

public class CommandFactory {
    public static final String UNKNOWN_TYPE_OF_COMMAND = "Unknown type of command ";

    private static final Logger LOGGER = Logger.getLogger(CommandFactory.class);

    public Command create(String type) {
        LOGGER.debug("command " + type);
        switch (type) {
            case Commands.LOGIN:
                return new LoginCommand(new UserService(new DaoHelperFactory()));
            case Commands.MAIN_PAGE:
                return new ShowPageCommand("WEB-INF/view/main.jsp");
            case Commands.COURSES:
                return new CoursesCommand(new CourseService(new DaoHelperFactory()));
            case Commands.MY_PROFILE:
                return new ShowPageCommand("WEB-INF/view/myProfile.jsp");
            case Commands.LOGOUT:
                return new LogoutCommand();
            case Commands.CHANGE_LANGUAGE:
                return new ChangeLanguageCommand();
            case Commands.STUDENTS:
                return new StudentsCommand(new UserService(new DaoHelperFactory()));
            case Commands.FIND_STUDENT:
                return new FindStudentCommand(new UserService(new DaoHelperFactory()), new CourseService(new DaoHelperFactory()));
            case Commands.COURSE:
                return new CourseCommand(new CourseService(new DaoHelperFactory()), new UserService(new DaoHelperFactory()), new RequirementParser());
            case Commands.MY_PROFILE_STUDENT:
                return new MyProfileStudentCommand(new CourseService(new DaoHelperFactory()));
            case Commands.NEW_REQUIREMENT:
                return new NewRequirementCommand(new CourseService(new DaoHelperFactory()), new UserService(new DaoHelperFactory()), new RequirementParser());
            case Commands.SUBMIT_STUDENT:
                return new SubmitStudentCommand(new CourseUsersService(new DaoHelperFactory()));
            case Commands.STUDY_COURSE:
                return new StudyCourseCommand(new TaskService(new DaoHelperFactory()));
            case Commands.TASK:
                return new TaskCommand(new TaskService(new DaoHelperFactory()), new HomeworkService(new DaoHelperFactory()));
            case Commands.MY_COURSES_TEACHER:
                return new MyCoursesTeacherCommand(new CourseService(new DaoHelperFactory()));
            case Commands.SUBJECT_TAUGHT:
                return new SubjectTaughtCommand(new UserService(new DaoHelperFactory()));
            case Commands.TASK_VIEWER:
                return new HomeworkCommand(new HomeworkService(new DaoHelperFactory()), new TaskService(new DaoHelperFactory()));
            case Commands.CHANGE_LINK:
                return new ChangeLinkCommand(new HomeworkService(new DaoHelperFactory()));
            case Commands.TEACHERS:
                return new TeachersCommand(new UserService(new DaoHelperFactory()));
            case Commands.FIND_TEACHER:
                return new FindTeacherCommand(new UserService(new DaoHelperFactory()), new CourseService(new DaoHelperFactory()));
            case Commands.DELETE_TEACHER:
                return new DeleteTeacherCommand(new UserService(new DaoHelperFactory()));
            case Commands.REGISTRATION_PAGE:
                return new ShowPageCommand("WEB-INF/view/registration.jsp");
            case Commands.REGISTRATION:
                return new RegistrationCommand(new UserService(new DaoHelperFactory()));
            case Commands.NEW_COURSE_PAGE:
                return new NewCoursePageCommand(new UserService(new DaoHelperFactory()));
            case Commands.NEW_COURSE:
                return new NewCourseCommand(new CourseService(new DaoHelperFactory()), new UserService(new DaoHelperFactory()));
            case Commands.CREATE_TASK:
                return new CreateTaskCommand(new TaskService(new DaoHelperFactory()), new HomeworkService(new DaoHelperFactory()));
            case Commands.CREATE_TEACHER:
                return new CreateTeacherCommand(new UserService(new DaoHelperFactory()));
            default:
                LOGGER.debug(UNKNOWN_TYPE_OF_COMMAND + type);
                throw new IllegalArgumentException(UNKNOWN_TYPE_OF_COMMAND + type);
        }
    }
}
