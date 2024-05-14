package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection connection = Util.getConnectionJDBC();
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS Users (id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(10),lastName VARCHAR(10),age INT )");
            System.out.println("Таблица успешно создана (либо уже существует)");
        } catch (SQLException e) {
            System.err.println("Произошла ошибка при создании таблицы Users: " + e.getMessage());

        }
    }

    public void dropUsersTable() {

        try (Connection connection = Util.getConnectionJDBC();
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS Users ");
            System.out.println("Таблица Users успешно удалена (либо отсутствовала)");
        } catch (SQLException e) {
            System.err.println("Произошла ошибка при удалении таблицы Users: " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnectionJDBC();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Users (name, lastname, age) VALUES (?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь с именем " + name + " успешно добавлен");
        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении пользователя " + e.getMessage());
        }

    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnectionJDBC();
             Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM Users WHERE id = " + id);
            System.out.println("Пользователь c id = " + id + " успешно удален из таблицы user");
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении пользователя " + e.getMessage());
        }


    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        try (Connection connection = Util.getConnectionJDBC();
             Statement statement = connection.createStatement()) {
            boolean resultSetWasReturned = statement.execute("SELECT * FROM Users");
            if (resultSetWasReturned) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));
                    usersList.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println("Произошла ошибка во время вывода списка пользователей " + e.getMessage());
        }
        return usersList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnectionJDBC();
             Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM Users");
            System.out.println("Таблица Users успешно очищена");
        } catch (SQLException e) {
            System.out.println("Произошла ошибка во время очищения таблицы Users " + e.getMessage());
        }


    }
}
