package pl.coderslab;

import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;
import pl.coderslab.model.UserGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final String exit = "quit";
        Scanner scanner = new Scanner(System.in);
        String programState = "run";

        do {
            printMenu();
            programState = scanner.nextLine();
            switch (programState) {
                case "test":

                    UserDao userDao = new UserDao();

                    SolutionDao solutionDao = new SolutionDao();
                    System.out.println(Arrays.toString(solutionDao.findAllByExerciseId(1)));

                    break;
                case "add":
                    addUser();
                    break;
                case "edit":
                    break;
                case "delete":
                    break;
                case "quit":
                    System.out.println("Wyjście z programu");
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

        System.out.println("Wybierz opcję:");
        System.out.println("test - testowanie");
        System.out.println("add - dodanie użytkownika");
        System.out.println("edit - edycja użytkownika");
        System.out.println("delete = usunięcie użytkownika");
        System.out.println("quit = zakończenie programu");
    }

    public static void addUser(){
        User newUser = new User();
        UserDao newUserDao = new UserDao();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Set User Name: ");
        newUser.setUserName(scanner.nextLine());
        System.out.println("Set Email adress: ");
        newUser.setEmail(scanner.nextLine());
        System.out.println("Set Group ID: ");
        newUser.setGoupId(scanner.nextInt());

        newUserDao.create(newUser);
    }
}
