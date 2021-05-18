package com.trainingcentertastic.сommand;

import com.trainingcentertastic.connetion.ConnectionException;
import com.trainingcentertastic.connetion.ConnectionPool;
import com.trainingcentertastic.dao.DaoHelper;
import com.trainingcentertastic.dao.UserDao;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.service.CourseService;
import com.trainingcentertastic.service.StudentService;
import com.trainingcentertastic.service.UserService;

public class CommandFactory {

    public static final String LOGIN = "login";
    public static final String MAIN_PAGE = "mainPage";
    public static final String COURSES = "courses";
    public static final String MY_PROFILE = "myProfile";
    public static final String LOGOUT = "logout";
    public static final String CHANGE_LANGUAGE = "changeLanguage";
    public static final String STUDENTS = "students";
    public static final String FIND_STUDENT = "findStudent";
    public static final String COURSE = "course";
    public static final String UNKNOWN_TYPE_OF_COMMAND = "Unknown type of command ";

    private ConnectionPool pool;
    private DaoHelper helper;

    public CommandFactory() throws ConnectionException, DaoException {
        pool = ConnectionPool.getInstance();
        try {
            helper = new DaoHelper(pool.getConnection());
        } catch (ConnectionException exception) {
            throw new DaoException(exception);
        }
    }


    public Command create(String type) {
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
                    return new StudentsCommand(new StudentService(helper.createStudentDao()));
                case FIND_STUDENT:
                    return new FindStudentCommand(new StudentService(helper.createStudentDao()));
                case COURSE:
                    return new CourseCommand(new CourseService(helper.createCourseDao()), new StudentService(helper.createStudentDao()));
                default:
                    throw new IllegalArgumentException(UNKNOWN_TYPE_OF_COMMAND + type);
        }
    }
}
