package pl.coderslab.program.adm;

import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.model.User;
import pl.coderslab.model.UserGroup;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GroupAdm {

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
                        System.out.println(Arrays.toString(solutionDao.findAllByExerciseId(1)));

                        break;
                    case "add":
                        addGroup();
                        break;
                    case "edit":
                        editGroup();
                        break;
                    case "del":
                        deleteGroup();
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
            UserGroupDao userGroupDao = new UserGroupDao();

            List<UserGroup> userGroups;
            userGroups = Arrays.asList(userGroupDao.findAll());
            int groupCount = 1;
            for (UserGroup userGroup : userGroups) {
                System.out.println("Groups: " + groupCount +
                        "\n  Group ID: " + userGroup.getId() +
                        "\n  User Name: " + userGroup.getName() +
                        "\n"
                );
                groupCount++;
            }

            System.out.println("Chose option:");
            System.out.println("tests - tests");
            System.out.println("add - add group");
            System.out.println("edit - edit group");
            System.out.println("del = delete group");
            System.out.println("quit = main menu");
        }

        public void addGroup(){

        }

        public void editGroup(){

        }

        public void deleteGroup(){
            UserGroupDao userGroupDao = new UserGroupDao();
            Scanner scanner = new Scanner(System.in);

            System.out.println("Set Group ID to delete");

            try{
                userGroupDao.delete(scanner.nextInt());
            } catch (InputMismatchException ex) {
                ex.getMessage();
            }
        }

}
