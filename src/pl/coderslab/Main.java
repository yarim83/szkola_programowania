package pl.coderslab;

import pl.coderslab.dao.UserDao;
import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.model.User;
import pl.coderslab.model.UserGroup;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {


    UserGroupDao userGroupDao = new UserGroupDao();
        System.out.println("" + userGroupDao.read(2).getId() + ", " + userGroupDao.read(2).getName());

    }
}
