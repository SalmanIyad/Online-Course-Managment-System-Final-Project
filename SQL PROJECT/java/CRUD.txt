package connecttodatabase;

import java.sql.*;
import java.util.Scanner;

public class CRUDSystem {

    private static final String DB_URL = "jdbc:oracle:thin:@//localHost:1521/orclpdb";
    private static final String USERNAME = "userName";
    private static final String PASSWORD = "userPassword";

    public static void main(String[] args) {
        Connection connection = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database.");

            while (true) {
                System.out.println("\n--- CRUD System Menu ---");
                System.out.println("1. Add");
                System.out.println("2. Select");
                System.out.println("3. Update");
                System.out.println("4. Delete");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice = new Scanner(System.in).nextInt();

                switch (choice) {
                    case 1:
                        addRecord(connection);
                        break;
                    case 2:
                        readRecord(connection);
                        break;
                    case 3:
                        updateRecord(connection);
                        break;
                    case 4:
                        deleteRecord(connection);
                        break;
                    case 5:
                        System.out.println("Exiting the program.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
                new Scanner(System.in).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void addRecord(Connection connection)  {
    System.out.print("Enter the table name: ");
    String tableName = new Scanner(System.in).nextLine();
    
    try {

        ResultSetMetaData metaData = connection.createStatement().executeQuery("SELECT * FROM " + tableName).getMetaData();
        int columnCount = metaData.getColumnCount();

        StringBuilder columns = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();

        for (int i = 1; i <= columnCount; i++) {
            if (i > 1) {
                columns.append(", ");
                placeholders.append(", ");
            }
            String columnName = metaData.getColumnName(i);
            columns.append(columnName);
            placeholders.append("?");
        }

        String sql = "INSERT INTO " + tableName + " (" + columns.toString() + ") VALUES (" + placeholders.toString() + ")";
        PreparedStatement statement = connection.prepareStatement(sql);

        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            System.out.print("Enter value for " + columnName + ": ");
            String value = new Scanner(System.in).nextLine();
            statement.setString(i, value);
        }

        // Execute the INSERT statement
        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Record added successfully.");
        } else {
            System.out.println("Failed to add record.");
        }

    } catch (SQLException e) {
        System.out.println("Error executing the INSERT operation: " + e.getMessage());
    }
} // DONE

    private static void readRecord(Connection connection) {
    System.out.print("Enter the table name: ");
    String tableName = new Scanner(System.in).next();
        
    try{

        String sql = "SELECT * FROM " + tableName;
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object value = resultSet.getObject(i);
                System.out.println(columnName + ": " + value);
            }
            System.out.println(); // Add a line break between rows
        }
    } catch (SQLException e) {
        System.out.println("Error executing the SELECT operation: " + e.getMessage());
    }
} // DONE

    private static void updateRecord(Connection connection) {
    System.out.print("Enter the table name: ");
    String tableName = new Scanner(System.in).nextLine();
    try {
        ResultSetMetaData metaData = connection.createStatement().executeQuery("SELECT * FROM " + tableName).getMetaData();
        int columnCount = metaData.getColumnCount();

        StringBuilder setClause = new StringBuilder();

        String idColumnName = metaData.getColumnName(1); // Assume the first column is the ID column

        for (int i = 2; i <= columnCount; i++) {
            if (i > 2) {
                setClause.append(", ");
            }
            String columnName = metaData.getColumnName(i);
            setClause.append(columnName).append(" = ?");
        }

        String sql = "UPDATE " + tableName + " SET " + setClause.toString() + " WHERE " + idColumnName + " = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        for (int i = 2; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            System.out.print("Enter new value for " + columnName + ": ");
            String value = new Scanner(System.in).nextLine();
            statement.setString(i - 1, value);
        }

        System.out.print("Enter the ID of the record to update: ");
        int id = new Scanner(System.in).nextInt();
        statement.setInt(columnCount, id);

        // Execute the UPDATE statement
        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Record updated successfully.");
        } else {
            System.out.println("Failed to update record.");
        }
    } catch (SQLException e) {
        System.out.println("Error executing the UPDATE operation: " + e.getMessage());
    }
}  // DONE
    
    private static void deleteRecord(Connection connection) {
        System.out.print("Enter the table name: ");
        String tableName = new Scanner(System.in).nextLine();

        try {
            ResultSetMetaData metaData = connection.createStatement().executeQuery("SELECT * FROM " + tableName).getMetaData();
            String idColumnName = metaData.getColumnName(1);

            System.out.print("Enter the ID of the record to delete: ");
            int id = new Scanner(System.in).nextInt();

            // Prepare the DELETE statement
            String sql = "DELETE FROM " + tableName + " WHERE " + idColumnName + " = " + id;
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute the DELETE statement
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("Failed to delete record.");
            }
        } catch (SQLException e) {
            System.out.println("Error executing the DELETE operation: " + e.getMessage());
        }
    } // DONE

}

