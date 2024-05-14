package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {

//        Для Hibernate
        public static void main (String[] args){

            final UserService userService = new UserServiceImpl();

            userService.dropUsersTable();
            userService.createUsersTable();
            userService.saveUser("Semen", "Sovilov", (byte) 55);
            userService.saveUser("Bob", "Akerman", (byte) 98);
            userService.saveUser("Mark", "Belov", (byte) 30);
            userService.saveUser("Ibragim", "Salimatov", (byte) 3);
            userService.getAllUsers();
            userService.cleanUsersTable();
            userService.dropUsersTable();

        }
//        Для JDBC

//        Util.getConnectionJDBC();
//        UserDao userDao = new UserDaoJDBCImpl();
//
//        userDao.createUsersTable();
//
//        userDao.saveUser("Name1", "LastName1", (byte) 20);
//        userDao.saveUser("Name2", "LastName2", (byte) 25);
//        userDao.saveUser("Name3", "LastName3", (byte) 31);
//        userDao.saveUser("Name4", "LastName4", (byte) 38);
//
//        userDao.removeUserById(1);
//        userDao.getAllUsers();
//        userDao.cleanUsersTable();
//        userDao.dropUsersTable();
    }



