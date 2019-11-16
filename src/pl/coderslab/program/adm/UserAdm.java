package pl.coderslab.program.adm;

import pl.coderslab.Main;
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.model.User;

import java.sql.SQLSyntaxErrorException;
import java.util.*;

public class UserAdm {

    public void userMenu() {

        final String exit = "quit";
        Scanner scanner = new Scanner(System.in);
        String programState = "run";

        do {
            printMenu();
            programState = scanner.nextLine();
            switch (programState) {
                case "testy":

                    UserDao userDao = new UserDao();

                    SolutionDao solutionDao = new SolutionDao();
                    System.out.println(Arrays.toString(solutionDao.findAllByExerciseId(1)));

                    break;
                case "add":
                    addUser();
                    break;
                case "edit":
                    editUser();
                    break;
                case "del":
                    deleteUser();
                    break;
                case "quit":
                    break;
                default:
                    System.out.println("Wybrałeś błędną opcję");
                    break;
            }

        } while (!programState.equalsIgnoreCase(exit));


    }

    /**
     * This method is used to add two integers. This is
     * a the simplest form of a class method, just to
     * show the usage of various javadoc Tags.
     * @return Nothing.
     */
    public static void printMenu() {
        allUsers();

        System.out.println("Chose option:");
        System.out.println("tests - tests");
        System.out.println("add - add user");
        System.out.println("edit - edit user");
        System.out.println("del = delete user");
        System.out.println("quit = main menu");
    }

    public static void allUsers(){
        UserDao userDao = new UserDao();

        List<User> users;
        users = Arrays.asList(userDao.findAll());
        int userCount = 1;
        for (User user : users) {
            System.out.println("User: " + userCount +
                    "\n  User ID: " + user.getId() +
                    "\n  User Name: " + user.getUserName() +
                    "\n  Email: " + user.getEmail() +
                    "\n  Group ID: " + user.getGoupId() +
                    "\n"
            );
            userCount++;
        }
    }

    /**
     * This method is used to add two integers. This is
     * a the simplest form of a class method, just to
     * show the usage of various javadoc Tags.
     * @return Nothing.
     */
    public void addUser() {
        User newUser = new User();
        UserDao newUserDao = new UserDao();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Set User Name: ");
        newUser.setUserName(scanner.next());
        System.out.println("Set Email adress: ");
        newUser.setEmail(scanner.next());
        System.out.println("Set Group ID: ");
        newUser.setGoupId(scanner.nextInt());
        System.out.println("Set Password");
        newUser.setPassword(scanner.next());
        newUserDao.create(newUser);
    }

    /**
     * This method is used to add two integers. This is
     * a the simplest form of a class method, just to
     * show the usage of various javadoc Tags.
     * @return Nothing.
     */
    public void editUser(){
        User editedUser = new User();
        UserDao editedUserDao = new UserDao();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Point User ID to edit");
        editedUser.setId(scanner.nextInt());
        System.out.println("Set new User Name: ");
        editedUser.setUserName(scanner.next());
        System.out.println("Set new Email adress: ");
        editedUser.setEmail(scanner.next());
        System.out.println("Set new Group ID: ");
        editedUser.setGoupId(scanner.nextInt());
        System.out.println("Set new Password");
        editedUser.setPassword(scanner.next());

        try {
            editedUserDao.update(editedUser);
        } catch (NoSuchElementException elemEx) {
            elemEx.getMessage();
        }
    }

    /**
     * This method is used to add two integers. This is
     * a the simplest form of a class method, just to
     * show the usage of various javadoc Tags.
     * @return Nothing.
     */
    public void deleteUser(){
        UserDao deleteUserDao = new UserDao();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Set User ID to delete");
        try {
            int userId = (scanner.nextInt());
            deleteUserDao.delete(userId);
        } catch (InputMismatchException ex){
            ex.getMessage();
        }
    }

}
