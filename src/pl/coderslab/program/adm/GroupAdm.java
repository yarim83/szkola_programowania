package pl.coderslab.program.adm;

import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.model.UserGroup;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GroupAdm {
    public static void main(String[] args) {
        final String exit = "quit";
        Scanner scanner = new Scanner(System.in);
        UserGroup userGroup = new UserGroup();
        UserGroupDao userGroupDao = new UserGroupDao();
        String programState = "run";

        do {
            printMenu(userGroupDao);
            programState = scanner.nextLine();
            switch (programState) {
                case "add":
                    addGroup(scanner, userGroup, userGroupDao);
                    break;
                case "edit":
                    editGroup(scanner, userGroup, userGroupDao);
                    break;
                case "del":
                    deleteGroup(scanner, userGroupDao);
                    break;
                case "quit":
                    break;
                default:
                    System.out.println("Wrong option");
                    break;
            }

        } while (!programState.equalsIgnoreCase(exit));

    }

    /**
     * This method is used to print program menu.
     *
     * @return Nothing.
     */
    public static void printMenu(UserGroupDao userGroupDao) {

        List<UserGroup> userGroups;
        userGroups = userGroupDao.findAll();

        int counter = 0;
        System.out.printf("%4s | %21s |%n", "ID", "GROUP NAME");
        for (UserGroup userGroup : userGroups) {
            System.out.printf("%4d | %21s |%n", userGroup.getId(), userGroup.getName());
            counter++;
        }

        System.out.printf("\n %4s: %1d %n \n", "USER GROUP COUNTED", counter);

        System.out.println("Chose option:");
        System.out.println("add - add group");
        System.out.println("edit - edit group");
        System.out.println("del = delete group");
        System.out.println("quit = main menu");
    }
    /**
     * This method is used to add group do database.
     *
     * @return Nothing.
     */
    public static void addGroup(Scanner scanner, UserGroup userGroup, UserGroupDao userGroupDao) {
        System.out.println("Set title for User Grop");
        userGroup.setName(scanner.nextLine());

        userGroupDao.create(userGroup);
    }
    /**
     * This method is used to edit user group.
     *
     * @return Nothing.
     */
    public static void editGroup(Scanner scanner, UserGroup userGroup, UserGroupDao userGroupDao) {
        while (true) {
            try {
                System.out.println("Point ID to Edit");
                userGroup.setId(scanner.nextInt());
                break;
            } catch (InputMismatchException ex) {
                ex.getMessage();
                scanner.nextLine();
            }
        }

        scanner.nextLine();
        System.out.println("Set new title for User Group");
        userGroup.setName(scanner.nextLine());

        userGroupDao.update(userGroup);
    }
    /**
     * This method is used to delete user group.
     *
     * @return Nothing.
     */
    public static void deleteGroup(Scanner scanner, UserGroupDao userGroupDao) {
        System.out.println("Set Group ID to delete");

        try {
            userGroupDao.delete(scanner.nextInt());
            System.out.println("Group deleted");
        } catch (InputMismatchException ex) {
            ex.getMessage();
        }
    }

}
