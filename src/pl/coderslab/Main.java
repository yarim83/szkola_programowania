package pl.coderslab;

import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;
import pl.coderslab.model.UserGroup;
import pl.coderslab.program.adm.UserAdm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * <h1>Programers shool</h1>
 * The Programers shool program implements an application that
 * manage users, groups, tasks. Is based on mysql databases.
 *
 * @author  Tomasz Smereczyński
 * @version 1.0
 * @since   2019-11-11
 */

public class Main {

    /**
     * This is the main method which manage program.
     * @param args Unused.
     * @return Nothing.
     */
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
                case "adm":
                    new UserAdm().userMenu();
                    scanner.close();
                    break;
                case "task":
                    scanner.close();
                    break;
                case "group":
                    scanner.close();
                    break;
                case "assign":
                    scanner.close();
                    break;
                case "quit":
                    System.out.println("Wyjście z programu");
                    scanner.close();
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
     * @return Nothind.
     */
    public static void printMenu() {
        System.out.println("Wybierz opcję:");
        System.out.println("test - testowanie");
        System.out.println("adm - zarządzanie użytkownikami");
        System.out.println("task - zarządzanie zadaniami");
        System.out.println("group = zarządzanie grupami");
        System.out.println("assign = przypisywanie grup");
        System.out.println("quit = wyjście z programu");
    }
}
