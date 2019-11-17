package pl.coderslab.program.adm;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SolutionAssign {
    public static void main(String[] args) {

        final String exit = "quit";
        Scanner scanner = new Scanner(System.in);
        UserDao userDao = new UserDao();
        ExerciseDao exerciseDao = new ExerciseDao();
        SolutionDao solutionDao = new SolutionDao();

        String programState = "run";

        do {
            printMenu();
            programState = scanner.nextLine();
            switch (programState) {
                case "add":
                    addSolution(scanner, userDao, exerciseDao, solutionDao);
                    break;
                case "view":
                    viewSolution(scanner, solutionDao);
                    break;
                case "quit":
                    break;
                default:
                    System.out.println("Wrong option");
                    break;
            }
        } while (!programState.equalsIgnoreCase(exit));

        scanner.close();

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
    private static void addSolution(Scanner scanner, UserDao userDao, ExerciseDao exerciseDao, SolutionDao solutionDao) {
        int userId = 0, exerciseId = 0;

        List<User> users = userDao.findAll();

        System.out.printf("%4s | %21s | %30s | %10s |%n", "ID", "USER NAME", "EMAIL", "GROUP ID");
        for (User user : users) {
            System.out.printf("%4d | %21s | %30s | %10d |%n", user.getId(), user.getUserName(), user.getEmail(),
                    user.getGoupId());
        }

        System.out.println("Point User ID to add Solutions:");

        userId = scanner.nextInt();
        userDao.read(userId);
        System.out.println(exerciseDao.findAll());

        System.out.println("Point Exercise ID to add to User:");
        exerciseId = scanner.nextInt();

        try {
            Solution solution = new Solution();
            solution.setUsers_id(userId);
            solution.setExercise_id(exerciseId);
            solution.setDescription("");
            solution.setUpdated(null);
            solution.setCreated(new Timestamp(System.currentTimeMillis()));
            solutionDao.create(solution);
        } catch (NullPointerException ex) {
            System.out.println("Wrong solution ID");
        }
    }

    /**
     * This method is used to view solution assign to specify User ID.
     *
     * @return Nothing.
     */
    private static void viewSolution(Scanner scanner, SolutionDao solutionDao) {
        List<Solution> solutionList = solutionDao.findAll();

        printSolution(solutionList);

        System.out.println("Point User ID to see Solutions:");

        solutionList = solutionDao.findAllByUserId(scanner.nextInt());
        scanner.nextLine();
        printSolution(solutionList);
    }

    private static void printSolution(List<Solution> solutionList) {
        System.out.printf("%4s | %21s | %21s | %30s | %11s | %7s |%n", "ID", "CREATED", "UPDATED", "DESCRIPTION", "EXERCISE ID", "USER ID");
        for (Solution solution : solutionList) {
            System.out.printf("%4d | %21s | %21s | %30s | %11d | %7d |%n", solution.getId(), solution.getCreated(), solution.getUpdated(),
                    solution.getDescription(), solution.getExercise_id(), solution.getUsers_id());
        }
    }
}
