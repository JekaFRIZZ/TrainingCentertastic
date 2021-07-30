package com.trainingcentertastic.command;

import com.trainingcentertastic.connetion.ConnectionPool;
import com.trainingcentertastic.dao.DaoHelper;
import com.trainingcentertastic.parser.RequirementParser;
import com.trainingcentertastic.service.*;
import org.apache.log4j.Logger;

public class CommandFactory {
    private static final String DELETE_TEACHER = "deleteTeacher";
    private static final Logger LOGGER = Logger.getLogger(CommandFactory.class);
    private static final String LOGIN = "login";
    private static final String MAIN_PAGE = "mainPage";
    private static final String COURSES = "courses";
    private static final String MY_PROFILE = "myProfile";
    private static final String LOGOUT = "logout";
    private static final String CHANGE_LANGUAGE = "changeLanguage";
    private static final String STUDENTS = "students";
    private static final String FIND_STUDENT = "findStudent";
    private static final String COURSE = "course";
    private static final String UNKNOWN_TYPE_OF_COMMAND = "Unknown type of command ";
    private static final String MY_PROFILE_STUDENT = "myProfileStudent";
    private static final String NEW_REQUIREMENT = "newRequirement";
    private static final String SUBMIT_STUDENT = "submitStudent";
    private static final String STUDY_COURSE = "studyCourse";
    private static final String TASK = "task";
    private static final String MY_COURSES_TEACHER = "myCoursesTeacher";
    private static final String SUBJECT_TAUGHT = "subjectTaught";
    private static final String TASK_VIEWER = "taskViewer";
    private static final String CHANGE_LINK = "changeLink";
    private static final String TEACHERS = "teachers";
    private static final String FIND_TEACHER = "findTeacher";

    private ConnectionPool pool;
    private DaoHelper helper;

    public CommandFactory() {
        pool = ConnectionPool.getInstance();
        helper = new DaoHelper(pool.getConnection());
    }

    public Command create(String type) {
        LOGGER.debug("command " + type);
        switch (type) {
            case LOGIN:
                return new LoginCommand(new UserService(helper.createUserDao()));
            case MAIN_PAGE:
                return new ShowPageCommand("WEB-INF/view/main.jsp");
            case COURSES:
                return new CoursesCommand(new CourseService(helper.createCourseDao()));
            case MY_PROFILE:
                return new ShowPageCommand("WEB-INF/view/myProfile.jsp");
            case LOGOUT:
                return new LogoutCommand();
            case CHANGE_LANGUAGE:
                return new ChangeLanguageCommand();
            case STUDENTS:
                return new StudentsCommand(new UserService(helper.createUserDao()));
            case FIND_STUDENT:
                return new FindStudentCommand(new UserService(helper.createUserDao()));
            case COURSE:
                return new CourseCommand(new CourseService(helper.createCourseDao()), new UserService(helper.createUserDao()), new RequirementParser());
            case MY_PROFILE_STUDENT:
                return new MyProfileStudentCommand(new CourseService(helper.createCourseDao()));
            case NEW_REQUIREMENT:
                return new NewRequirementCommand(new CourseService(helper.createCourseDao()), new UserService(helper.createUserDao()), new RequirementParser());
            case SUBMIT_STUDENT:
                return new SubmitStudentCommand(new CourseUsersService(helper.createCourseUsersDao()));
            case STUDY_COURSE:
                return new StudyCourseCommand(new TaskService(helper.createTaskDao()));
            case TASK:
                return new TaskCommand(new TaskService(helper.createTaskDao()), new HomeworkService(helper.createHomeworkDao()));
            case MY_COURSES_TEACHER:
                return new MyCoursesTeacherCommand(new CourseService(helper.createCourseDao()));
            case SUBJECT_TAUGHT:
                return new SubjectTaughtCommand(new UserService(helper.createUserDao()));
            case TASK_VIEWER:
                return new HomeworkCommand(new HomeworkService(helper.createHomeworkDao()), new TaskService(helper.createTaskDao()));
            case CHANGE_LINK:
                return new ChangeLinkCommand(new HomeworkService(helper.createHomeworkDao()));
            case TEACHERS:
                return new TeachersCommand(new UserService(helper.createUserDao()));
            case FIND_TEACHER:
                return new FindTeacherCommand(new UserService(helper.createUserDao()));
            case DELETE_TEACHER:
                return new DeleteTeacherCommand(new UserService(helper.createUserDao()));
            case "registrationPage":
                return new ShowPageCommand("WEB-INF/view/registration.jsp");
            case "registration":
                return new RegistrationCommand(new UserService(helper.createUserDao()));
            default:
                LOGGER.debug(UNKNOWN_TYPE_OF_COMMAND + type);
                throw new IllegalArgumentException(UNKNOWN_TYPE_OF_COMMAND + type);
        }
    }
}
