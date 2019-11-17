package pl.coderslab.program.adm;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.model.Exercise;
import pl.coderslab.model.Solution;

import java.util.*;

public class ExerciseAdm {
    public static void main(String[] args) {
        final String exit = "quit";
        Scanner scanner = new Scanner(System.in);
        String programState = "run";

        do {
            printMenu();
            programState = scanner.nextLine();
            switch (programState) {
                case "add":
                    addExercise(scanner);
                    break;
                case "edit":
                    editExercise(scanner);
                    break;
                case "del":
                    deleteExercise(scanner);
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
     * This method is used to add two integers.
     *
     * @return Nothing.
     */
    public static void printMenu() {
        ExerciseDao exerciseDao = new ExerciseDao();
        List<Exercise> exercises = new ArrayList<>();

        exercises = exerciseDao.findAll();

        int counter = 0;
        System.out.printf("%4s | %21s | %31s |%n", "ID", "EXERCISE TITLE", "DESCRIPTION");
        for (Exercise exercise : exercises) {
            System.out.printf("%4d | %21s | %31s |%n", exercise.getId(), exercise.getTitle(), exercise.getDescription());
            counter++;
        }

        System.out.printf("\n %4s: %1d %n \n", "USER COUNTED", counter);

        System.out.println("Chose option:");
        System.out.println("add - add exercise");
        System.out.println("edit - edit exercise");
        System.out.println("del = delete exercise");
        System.out.println("quit = main menu");
    }

    /**
     * This method is used to add two integers. This is
     * a the simplest form of a class method, just to
     * show the usage of various javadoc Tags.
     *
     * @return Nothing.
     */
    public static void addExercise(Scanner scanner) {
        Exercise exercise = new Exercise();
        ExerciseDao exerciseDao = new ExerciseDao();

        System.out.println("Set title for Exercise");
        exercise.setTitle(scanner.nextLine());
        System.out.println("Set description for Exercise");
        exercise.setDescription(scanner.nextLine());

        exerciseDao.create(exercise);
    }

    /**
     * This method is used to add two integers. This is
     * a the simplest form of a class method, just to
     * show the usage of various javadoc Tags.
     *
     * @return Nothing.
     */
    public static void editExercise(Scanner scanner) {
        Exercise exercise = new Exercise();
        ExerciseDao exerciseDao = new ExerciseDao();

        while (true) {
            try {
                System.out.println("Point ID to Edit");
                exercise.setId(scanner.nextInt());
                break;
            } catch (InputMismatchException ex) {
                ex.getMessage();
                scanner.nextLine();
            }
        }


        scanner.nextLine();
        System.out.println("Set new title for Exercise");
        exercise.setTitle(scanner.nextLine());
        System.out.println("Set new description for Exercise");
        exercise.setDescription(scanner.nextLine());

        exerciseDao.update(exercise);
    }

    /**
     * This method is used to add two integers. This is
     * a the simplest form of a class method, just to
     * show the usage of various javadoc Tags.
     *
     * @return Nothing.
     */
    public static void deleteExercise(Scanner scanner) {
        ExerciseDao exerciseDao = new ExerciseDao();

        System.out.println("Point User ID to delete");
        try {
            int exerciseId = (scanner.nextInt());
            exerciseDao.delete(exerciseId);
        } catch (InputMismatchException ex) {
            ex.getMessage();
        }
    }

}
