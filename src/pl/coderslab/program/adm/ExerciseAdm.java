package pl.coderslab.program.adm;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.model.Exercise;

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
                case "testy":

                    UserDao userDao = new UserDao();

                    SolutionDao solutionDao = new SolutionDao();
                    System.out.println(Arrays.toString(solutionDao.findAllByExerciseId(1)));

                    break;
                case "add":
                    addExercise();
                    break;
                case "edit":
                    editExercise();
                    break;
                case "del":
                    deleteExercise();
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
     * @return Nothing.
     */
    public static void printMenu() {
        ExerciseDao exerciseDao = new ExerciseDao();

        List<Exercise> exercises;
        exercises = Arrays.asList(exerciseDao.findAll());
        int exerciseCount = 1;
        for (Exercise exercise : exercises) {
            System.out.println("User: " + exerciseCount +
                    "\n  User ID: " + exercise.getId() +
                    "\n  User Name: " + exercise.getTitle() +
                    "\n  Email: " + exercise.getDescription() +
                    "\n"
            );
            exerciseCount++;
        }

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
     * @return Nothing.
     */
    public static void addExercise() {
        Exercise exercise = new Exercise();
        ExerciseDao exerciseDao = new ExerciseDao();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Set title for Exercise");
        exercise.setTitle(scanner.next());
        System.out.println("Set description for Exercise");
        exercise.setDescription(scanner.next());

        exerciseDao.create(exercise);
    }

    /**
     * This method is used to add two integers. This is
     * a the simplest form of a class method, just to
     * show the usage of various javadoc Tags.
     * @return Nothing.
     */
    public static void editExercise(){
        Exercise exercise = new Exercise();
        ExerciseDao exerciseDao = new ExerciseDao();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Point ID to Edit");
        exercise.setId(scanner.nextInt());
        System.out.println("Set new title for Exercise");
        exercise.setTitle(scanner.next());
        System.out.println("Set new description for Exercise");
        exercise.setDescription(scanner.next());

        try {
            exerciseDao.update(exercise);
        } catch (NoSuchElementException elemEx) {
            elemEx.getMessage();
        }
    }

    /**
     * This method is used to add two integers. This is
     * a the simplest form of a class method, just to
     * show the usage of various javadoc Tags.
     * @return Nothing.
     */
    public static void deleteExercise(){
        ExerciseDao exerciseDao = new ExerciseDao();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Point User ID to delete");
        try {
            int exerciseId = (scanner.nextInt());
            exerciseDao.delete(exerciseId);
        } catch (InputMismatchException ex){
            ex.getMessage();
        }
    }

}
