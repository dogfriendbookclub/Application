package edu.metrostate.models;

import edu.metrostate.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Person extends SqlModel {
    private String name;

    private final List<Task> taskList = new ArrayList<>();

    public static List<Person> loadAll(Connection connection) {
        List<Person> personList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM person");
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Person person = new Person(id, name);
                List<Task> tasks = Task.loadAll(person, connection);
                tasks.forEach(task -> person.addTask(task));
                personList.add(person);
            }
            Util.closeQuietly(resultSet);
            Util.closeQuietly(statement);
        } catch (SQLException ex) {

        }
        return personList;
    }

    public Person(String name) {
        super(null);
        this.name = name;
    }

    public Person(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTask(Task task) {
        if (task == null || taskList.contains(task)) {
            return;
        }
        task.setPersonId(getId());
        taskList.add(task);
    }

    public List<Task> getTaskList() {
        return this.taskList
                .stream()
                .peek(task -> task.setPersonId(Person.this.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public PreparedStatement insertStatement(Connection connection) throws SQLException {
        String sql = "INSERT INTO person (name) VALUES (?) RETURNING id";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, this.name);
        return statement;
    }

    @Override
    public PreparedStatement updateStatement(Connection connection) throws SQLException {
        String sql = "UPDATE person SET name = ? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, this.name);
        preparedStatement.setInt(2, this.getId());
        return preparedStatement;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Name: ");
        builder.append(this.name);
        if (this.getId() != null) {
            builder.append("\n");
            builder.append("ID: ");
            builder.append(this.getId());
        }
        if (!taskList.isEmpty()) {
            builder.append("\n");
            builder.append("Tasks:\n");
            taskList.forEach(task -> {
                builder.append("- ");
                builder.append(task);
                builder.append("\n");
            });
        }
        return builder.toString();
    }
}
