package com.trainingcentertastic.command;

import com.trainingcentertastic.connetion.ConnectionException;
import com.trainingcentertastic.connetion.ConnectionPool;
import com.trainingcentertastic.dao.DaoHelper;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.service.*;

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
    public static final String MY_PROFILE_STUDENT = "myProfileStudent";
    public static final String NEW_REQUIREMENT = "newRequirement";
    public static final String SUBMIT_STUDENT = "submitStudent";
    public static final String STUDY_COURSE = "studyCourse";
    public static final String TASK = "task";
    public static final String MY_COURSES_TEACHER = "myCoursesTeacher";
    public static final String SUBJECT_TAUGHT = "subjectTaught";
    public static final String TASK_VIEWER = "taskViewer";
    public static final String CHANGE_LINK = "changeLink";
    public static final String TEACHERS = "teachers";

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
            case MY_PROFILE_STUDENT:
                return new MyProfileStudentCommand(new CourseService(helper.createCourseDao()), new StudentService(helper.createStudentDao()));
            case NEW_REQUIREMENT:
                return new NewRequirementCommand(new CourseService(helper.createCourseDao()));
            case SUBMIT_STUDENT:
                return new SubmitStudentCommand(new CourseUsersService(helper.createCourseUsersDao()));
            case STUDY_COURSE:
                return new StudyCourseCommand(new TaskService(helper.createTaskDao()));
            case TASK:
                return new TaskCommand(new TaskService(helper.createTaskDao()));
            case MY_COURSES_TEACHER:
                return new MyCoursesTeacherCommand(new CourseService(helper.createCourseDao()));
            case SUBJECT_TAUGHT:
                return new SubjectTaughtCommand(new StudentService(helper.createStudentDao()));
            case TASK_VIEWER:
                return new HomeworkCommand(new HomeworkService(helper.createHomeworkDao()), new TaskService(helper.createTaskDao()));
            case CHANGE_LINK:
                return new ChangeLinkCommand(new HomeworkService(helper.createHomeworkDao()));
            case TEACHERS:
                return new TeachersCommand(new CourseUsersService(helper.createCourseUsersDao()));
            default:
                throw new IllegalArgumentException(UNKNOWN_TYPE_OF_COMMAND + type);
        }
    }
}
