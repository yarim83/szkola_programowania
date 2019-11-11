package pl.coderslab.program.adm;

import pl.coderslab.Main;
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
                    new UserAdm().addUser();
                    break;
                case "edit":
                    break;
                case "delete":
                    break;
                case "quit":
                    break;
                default:
                    System.out.println("Wybrałeś błędną opcję");
                    break;
            }

        } while (!programState.equalsIgnoreCase(exit));


    }

    public static void printMenu() {
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

        System.out.println("Chose option:");
        System.out.println("tests - tests");
        System.out.println("add - add user");
        System.out.println("edit - edit user");
        System.out.println("delete = delete user");
        System.out.println("quit = main menu");
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
        newUser.setUserName(scanner.nextLine());
        System.out.println("Set Email adress: ");
        newUser.setEmail(scanner.nextLine());
        System.out.println("Set Group ID: ");
        newUser.setGoupId(scanner.nextInt());
        System.out.println("Set Password");
        newUser.setPassword(scanner.nextLine());
        newUserDao.create(newUser);
    }


}
