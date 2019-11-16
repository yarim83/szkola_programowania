package pl.coderslab.program.adm;

import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.model.Solution;

import java.util.Arrays;
import java.util.Scanner;

public class SolutionAssign {

    /**
     * This method is used to print SolutionAssign menu.
     *
     * @return Nothing.
     */
    public void userMenu() {

        final String exit = "quit";
        Scanner scanner = new Scanner(System.in);
        String programState = "run";

        do {
            printMenu();
            programState = scanner.nextLine();
            switch (programState) {
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
     * This method is used to print option menu.
     *
     * @return Nothing.
     */
    private static void printMenu() {

        System.out.println("Chose option:");
        System.out.println("add - add solution");
        System.out.println("view - view solution");
        System.out.println("quit = main menu");
    }

    /**
     * This method is used to add specify solution to user.
     *
     * @return Nothing.
     */
    private static void addSolution() {
        UserDao userDao = new UserDao();
        System.out.println(Arrays.toString(userDao.findAll()));

        int userId = 0, solutionId = 0;
        SolutionDao solutionDao = new SolutionDao();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Point User ID to add Solutions:");

        userId = scanner.nextInt();
        userDao.read(userId);
        System.out.println(Arrays.toString(solutionDao.findAll()));

        System.out.println("Point Solution ID to add to User:");
        solutionId = scanner.nextInt();

        Solution solution = new Solution();
        try {
            solution = solutionDao.read(solutionId);
            solution.setUsers_id(userId);
            solutionDao.update(solution);
        } catch (NullPointerException ex) {
            System.out.println("Wrong solution ID");
        }
    }

    /**
     * This method is used to view solution assign to specify User ID.
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
