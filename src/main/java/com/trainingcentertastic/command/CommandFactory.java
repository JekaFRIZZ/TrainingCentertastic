package com.trainingcentertastic.command;

import com.trainingcentertastic.dao.DaoHelperFactory;
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
    private static final String REGISTRATION_PAGE = "registrationPage";
    private static final String REGISTRATION = "registration";
    private static final String NEW_COURSE_PAGE = "newCoursePage";
    private static final String NEW_COURSE = "newCourse";
    private static final String CREATE_TASK = "createTask";
    private static final String CREATE_TEACHER = "createTeacher";

    public Command create(String type) {
        LOGGER.debug("command " + type);
        switch (type) {
            case LOGIN:
                return new LoginCommand(new UserService(new DaoHelperFactory()));
            case MAIN_PAGE:
                return new ShowPageCommand("WEB-INF/view/main.jsp");
            case COURSES:
                return new CoursesCommand(new CourseService(new DaoHelperFactory()));
            case MY_PROFILE:
                return new ShowPageCommand("WEB-INF/view/myProfile.jsp");
            case LOGOUT:
                return new LogoutCommand();
            case CHANGE_LANGUAGE:
                return new ChangeLanguageCommand();
            case STUDENTS:
                return new StudentsCommand(new UserService(new DaoHelperFactory()));
            case FIND_STUDENT:
                return new FindStudentCommand(new UserService(new DaoHelperFactory()), new CourseService(new DaoHelperFactory()));
            case COURSE:
                return new CourseCommand(new CourseService(new DaoHelperFactory()), new UserService(new DaoHelperFactory()), new RequirementParser());
            case MY_PROFILE_STUDENT:
                return new MyProfileStudentCommand(new CourseService(new DaoHelperFactory()));
            case NEW_REQUIREMENT:
                return new NewRequirementCommand(new CourseService(new DaoHelperFactory()), new UserService(new DaoHelperFactory()), new RequirementParser());
            case SUBMIT_STUDENT:
                return new SubmitStudentCommand(new CourseUsersService(new DaoHelperFactory()));
            case STUDY_COURSE:
                return new StudyCourseCommand(new TaskService(new DaoHelperFactory()));
            case TASK:
                return new TaskCommand(new TaskService(new DaoHelperFactory()), new HomeworkService(new DaoHelperFactory()));
            case MY_COURSES_TEACHER:
                return new MyCoursesTeacherCommand(new CourseService(new DaoHelperFactory()));
            case SUBJECT_TAUGHT:
                return new SubjectTaughtCommand(new UserService(new DaoHelperFactory()));
            case TASK_VIEWER:
                return new HomeworkCommand(new HomeworkService(new DaoHelperFactory()), new TaskService(new DaoHelperFactory()));
            case CHANGE_LINK:
                return new ChangeLinkCommand(new HomeworkService(new DaoHelperFactory()));
            case TEACHERS:
                return new TeachersCommand(new UserService(new DaoHelperFactory()));
            case FIND_TEACHER:
                return new FindTeacherCommand(new UserService(new DaoHelperFactory()), new CourseService(new DaoHelperFactory()));
            case DELETE_TEACHER:
                return new DeleteTeacherCommand(new UserService(new DaoHelperFactory()));
            case REGISTRATION_PAGE:
                return new ShowPageCommand("WEB-INF/view/registration.jsp");
            case REGISTRATION:
                return new RegistrationCommand(new UserService(new DaoHelperFactory()));
            case NEW_COURSE_PAGE:
                return new NewCoursePageCommand(new UserService(new DaoHelperFactory()));
            case NEW_COURSE:
                return new NewCourseCommand(new CourseService(new DaoHelperFactory()), new UserService(new DaoHelperFactory()));
            case CREATE_TASK:
                return new CreateTaskCommand(new TaskService(new DaoHelperFactory()), new HomeworkService(new DaoHelperFactory()));
            case CREATE_TEACHER:
                return new CreateTeacherCommand(new UserService(new DaoHelperFactory()));
            default:
                LOGGER.debug(UNKNOWN_TYPE_OF_COMMAND + type);
                throw new IllegalArgumentException(UNKNOWN_TYPE_OF_COMMAND + type);
        }
    }
}
