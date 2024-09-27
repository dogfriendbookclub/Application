package edu.metrostate;

import edu.metrostate.migrations.Migrations;
import edu.metrostate.models.Person;
import edu.metrostate.models.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        Boolean populate = true;
        Boolean markTasksAsDone = true;

        Connection connection = null;

        Migrations migrations = new Migrations();

        try {
            connection = DriverManager.getConnection(Database.connectionString);

            migrations.runMigrations(connection);

            if (populate) {
                populate(connection);
            }

            List<Person> people = load(connection);
            people.forEach(person -> System.out.println(person));

            if (markTasksAsDone) {
                markTasksAsDone(people, connection);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Util.closeQuietly(connection);
        }
    }

    private static void populate(Connection connection) {
        List<Person> people = new ArrayList<>();
        Person philip = new Person("Philip Marlowe");
        Person vivian = new Person("Vivian Rutledge");
        people.add(philip);
        people.add(vivian);

        for (Person person : people) {
            person.insert(connection);
        }

        philip.addTask(new Task("Go to Geiger's shop"));
        philip.addTask(new Task("Go to Brody's apartment"));
        philip.addTask(new Task("Expose Mars' blackmail"));

        for (Task task : philip.getTaskList()) {
            task.insert(connection);
        }

        vivian.addTask(new Task("Pay off gambling debts."));
        vivian.addTask(new Task("Help Marlowe get free."));

        for (Task task : vivian.getTaskList()) {
            task.insert(connection);
        }
    }

    private static List<Person> load(Connection connection) {
        List<Person> people = Person.loadAll(connection);
        return people;
    }

    private static void markTasksAsDone(List<Person> people, Connection connection) {
        people.forEach(person -> {
            person.getTaskList().forEach(Task::completeTask);
        });

        for (Person person : people) {
            for (Task task : person.getTaskList()) {
                task.update(connection);
            }
        }
    }
}