package pl.coderslab.program.user;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.model.Exercise;
import pl.coderslab.model.Solution;

import java.sql.Timestamp;
import java.util.*;

public class UserProgram {
    public static void main(String[] args) {
        final String exit = "quit";
        String programState = "run";

        Scanner scanner = new Scanner(System.in);
        SolutionDao solutionDao = new SolutionDao();

        int userId = 0;

        try {
            if (args.length == 1) {
                userId = Integer.parseInt(args[0]);
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            ex.getMessage();
        }

        userExistsCheck(userId);

        do {
            printMenu();
            programState = scanner.nextLine();
            switch (programState) {
                case "add":
                    addSolution(userId, solutionDao, scanner);
                    break;
                case "view":
                    viewSolution(userId, solutionDao);
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
     * This method is used to add two integers.
     *
     * @return Nothing.
     */
    public static void printMenu() {

        System.out.println("CHOSE OPTION:");
        System.out.println("ADD - add solution");
        System.out.println("VIEW - view solution");
        System.out.println("QUIT = main menu");
    }

    public static void addSolution(int userId, SolutionDao solutionDao, Scanner scanner) {
        ExerciseDao exerciseDao = new ExerciseDao();
        Solution solution = new Solution();
        List<Exercise> exerciseList = new ArrayList<>();
        List<Integer> exerciseIdList = new ArrayList<>();
        int exerciseId = 0;

        exerciseList = exerciseDao.findAllExerciseWhereNoSolution(userId);

        if (exerciseList.isEmpty()) {
            System.out.println("No solution to ADD");
        } else {
            System.out.printf("%4s | %21s | %21s |%n", "ID", "TITLE", "DESCRIPTION");
            for (Exercise exercise : exerciseList) {
                System.out.printf("%4d | %21s | %21s |%n", exercise.getId(), exercise.getTitle(), exercise.getDescription());
                exerciseIdList.add(exercise.getId());
            }

            while (true) {
                try {
                    System.out.println("Point Exercise ID to add Solutions:");
                    exerciseId = scanner.nextInt();
                    if (exerciseIdList.indexOf(exerciseId) != -1) {
                        solution.setExercise_id(exerciseId);
                        scanner.nextLine();
                        System.out.println("Exercise added");
                        break;
                    }
                    System.out.println("Can't add solution");
                } catch (InputMismatchException ex) {
                    ex.getMessage();
                    scanner.nextLine();
                }
            }

            System.out.println("Set Description to Exercise");
            solution.setDescription(scanner.nextLine());

            Calendar calendar = Calendar.getInstance();

            solution.setCreated(new Timestamp(System.currentTimeMillis()));
            solution.setUsers_id(userId);
            System.out.println(solution.toString());

            solutionDao.create(solution);
        }
    }

    public static void viewSolution(int userId, SolutionDao solutionDao) {

        List<Solution> solutions;
        solutions = Arrays.asList(solutionDao.findAllByUserId(userId));
        int counter = 0;
        System.out.printf("%4s | %21s | %21s | %30s | %11s | %7s |%n", "ID", "CREATED", "UPDATED", "DESCRIPTION", "EXERCISE ID", "USER ID");
        for (Solution solution : solutions) {
            System.out.printf("%4d | %21s | %21s | %30s | %11d | %7d |%n", solution.getId(), solution.getCreated(), solution.getUpdated(),
                    solution.getDescription(), solution.getExercise_id(), solution.getUsers_id());
            counter++;
        }

        System.out.printf("\n %4s: %1d %n \n", "SOLUTION COUNTED", counter);
    }

    public static void userExistsCheck(int userId) {
        UserDao userDao = new UserDao();
        if (userDao.read(userId) == null) {
            System.out.println("USER DOES NOT EXIST");
            System.exit(0);
        }
    }
}
