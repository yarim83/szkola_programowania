package pl.coderslab.program.adm;

import pl.coderslab.dao.UserDao;
import pl.coderslab.model.User;

import java.util.*;

public class UserAdm {
    public static void main(String[] args) {
        final String exit = "quit";
        String programState = "run";
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        UserDao userDao = new UserDao();


        do {
            printMenu(userDao);
            programState = scanner.nextLine();
            switch (programState) {
                case "add":
                    addUser(scanner, user, userDao);
                    break;
                case "edit":
                    editUser(scanner, user, userDao);
                    break;
                case "del":
                    deleteUser(scanner, userDao);
                    break;
                case "quit":
                    break;
                default:
                    System.out.println("Wrong option");
                    break;
            }

        } while (!programState.equalsIgnoreCase(exit));

    }


    /**
     * This method is used to print Menu.
     *
     * @return Nothing.
     */
    public static void printMenu(UserDao userDao) {
        allUsers(userDao);

        System.out.println("Chose option:");
        System.out.println("add - add user");
        System.out.println("edit - edit user");
        System.out.println("del = delete user");
        System.out.println("quit = main menu");
    }

    public static void allUsers(UserDao userDao) {
        List<User> users = userDao.findAll();

        int counter = 0;
        System.out.printf("%4s | %21s | %31s | %10s |%n", "ID", "USER NAME", "EMAIL", "GROUP ID");
        for (User user : users) {
            System.out.printf("%4d | %21s | %31s |%10d |%n", user.getId(), user.getUserName(), user.getEmail(), user.getGoupId());
            counter++;
        }

        System.out.printf("\n %4s: %1d %n \n", "USER COUNTED", counter);

    }

    /**
     * This method is used to add User to DB
     *
     * @return Nothing.
     */
    public static void addUser(Scanner scanner, User user, UserDao userDao) {
        System.out.println("Set User Name: ");
        user.setUserName(scanner.next());
        System.out.println("Set Email adress: ");
        user.setEmail(scanner.next());
        System.out.println("Set Group ID: ");
        user.setGoupId(scanner.nextInt());
        System.out.println("Set Password");
        user.setPassword(scanner.next());
        userDao.create(user);
    }

    /**
     * This method is used to Edit User in DB.
     * @param scanner
     * @param user
     * @param userDao
     * @return Nothing.
     */
    public static void editUser(Scanner scanner, User user, UserDao userDao) {

        System.out.println("Point User ID to edit");
        user.setId(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Set new User Name: ");
        user.setUserName(scanner.nextLine());
        System.out.println("Set new Email adress: ");
        user.setEmail(scanner.nextLine());
        System.out.println("Set new Group ID: ");
        user.setGoupId(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Set new Password");
        user.setPassword(scanner.nextLine());

        try {
            userDao.update(user);
        } catch (NoSuchElementException ex) {
            ex.getMessage();
        }
    }

    /**
     * This method is used to Delete User from DB
     *
     * @return Nothing.
     */
    public static void deleteUser(Scanner scanner, UserDao userDao) {
        System.out.println("Set User ID to delete");
        try {
            int userId = scanner.nextInt();
            userDao.delete(userId);
        } catch (InputMismatchException ex) {
            ex.getMessage();
        }
    }

}
