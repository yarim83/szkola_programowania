package pl.coderslab.program.adm;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.model.Solution;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class SolutionAssign {
    public static void main(String[] args) {

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

            scanner.close();

    }
        /**
         * This method is used to print option menu.
         *
         * @return Nothing.
         */
        private static void printMenu () {

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
        private static void addSolution () {
            Scanner scanner = new Scanner(System.in);
            UserDao userDao = new UserDao();
            ExerciseDao exerciseDao = new ExerciseDao();
            System.out.println(Arrays.toString(userDao.findAll()));

            int userId = 0, exerciseId = 0;
            SolutionDao solutionDao = new SolutionDao();

            System.out.println("Point User ID to add Solutions:");

            userId = scanner.nextInt();
            userDao.read(userId);
            System.out.println(Arrays.toString(exerciseDao.findAll()));

            System.out.println("Point Exercise ID to add to User:");
            exerciseId = scanner.nextInt();

            try {
                Solution solution = new Solution();
                solution.setUsers_id(userId);
                solution.setExercise_id(exerciseId);
                solution.setDescription("");
                solution.setUpdated(null);
                Calendar calendar = Calendar.getInstance();
                Timestamp currentTimestamp = new Timestamp(calendar.getTime().getTime());

                System.out.println(currentTimestamp);
                solution.setCreated(currentTimestamp);
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
        private static void viewSolution () {
            Scanner scanner = new Scanner(System.in);
            SolutionDao solutionDao = new SolutionDao();
            List<Solution> solutionList = Arrays.asList(solutionDao.findAll());

            System.out.printf("%4s | %21s | %21s | %30s | %11s | %7s |%n", "ID", "CREATED", "UPDATED", "DESCRIPTION", "EXERCISE ID", "USER ID");
            for (Solution solution : solutionList) {
                System.out.printf("%4d | %21s | %21s | %30s | %11d | %7d |%n", solution.getId(), solution.getCreated(), solution.getUpdated(),
                        solution.getDescription(), solution.getExercise_id(), solution.getUsers_id());
            }

            System.out.println("Point User ID to see Solutions:");
            System.out.println(Arrays.toString(solutionDao.findAllByUserId(scanner.nextInt())));
        }

}
