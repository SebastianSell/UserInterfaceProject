package com.example.finalUI.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:taskmanager.db";

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection(URL);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void initializeDatabase() {

        String usersTable = """
        CREATE TABLE IF NOT EXISTS users (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            username TEXT UNIQUE,
            password TEXT
        )
    """;

        String tasksTable = """
        CREATE TABLE IF NOT EXISTS tasks (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            task_name TEXT,
            difficulty TEXT,
            member_assigned TEXT,
            status TEXT,
            due_date TEXT,
            created_date TEXT,
            notes TEXT,
            user_id INTEGER
        )
    """;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(usersTable);
            stmt.execute(tasksTable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
