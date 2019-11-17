package pl.coderslab;

import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.program.adm.ExerciseAdm;
import pl.coderslab.program.adm.GroupAdm;
import pl.coderslab.program.adm.SolutionAssign;
import pl.coderslab.program.adm.UserAdm;

import java.util.Arrays;
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
            programState = scanner.next();
            switch (programState) {
                case "test":

                    UserDao userDao = new UserDao();

                    SolutionDao solutionDao = new SolutionDao();
                    System.out.println(Arrays.toString(solutionDao.findAllByExerciseId(1)));

                    break;
                case "adm":
                    new UserAdm().userMenu();
                    break;
                case "exercise":
                    new ExerciseAdm().userMenu();
                    break;
                case "group":
                    new GroupAdm().userMenu();
                    break;
                case "assign":
                    new SolutionAssign().userMenu();
                    break;
                case "quit":
                    System.out.println("Exiting program. Bey!");
                    scanner.close();
                    break;
                default:
                    System.out.println("Wrong option");
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
        System.out.println("Chose option:");
        System.out.println("adm - user manager");
        System.out.println("solution - solution manager");
        System.out.println("group - group manager");
        System.out.println("assign - assign group");
        System.out.println("quit - exit program");
    }
}
