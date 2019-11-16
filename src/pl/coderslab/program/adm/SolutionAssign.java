package pl.coderslab.program.adm;

import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;

import java.util.Arrays;
import java.util.Scanner;

public class SolutionAssign {

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
                    System.out.println(Arrays.toString(solutionDao.findAllByUserId(1)));

                    break;
                case "add":
                    addSolution();
                    break;
                case "view":
                    viewSolution();
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
     *
     * @return Nothing.
     */
    private static void printMenu() {

        System.out.println("Chose option:");
        System.out.println("add - add exercise");
        System.out.println("edit - edit exercise");
        System.out.println("quit = main menu");
    }

    /**
     * This method is used to add two integers. This is
     * a the simplest form of a class method, just to
     * show the usage of various javadoc Tags.
     * @return Nothing.
     */
    private static void addSolution(){
        new UserDao().findAll();
    }

    /**
     * This method is used to add two integers. This is
     * a the simplest form of a class method, just to
     * show the usage of various javadoc Tags.
     *
     * @return Nothing.
     */
    private static void viewSolution() {
        SolutionDao solutionDao = new SolutionDao();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Point User ID to see Solutions:");
        solutionDao.findAllByUserId(scanner.nextInt());
    }

}
