package connecttodatabase;

import java.sql.*;

public class ConnectToDatabase {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            String url = "jdbc:oracle:thin:@//loalHost:1521/orclpdb";
            String username = "userName";
            String password = "userPassword";
            connection = DriverManager.getConnection(url, username, password);

            statement = connection.createStatement();

            String sql = "SELECT * FROM Student where student_id = 1";
            select(sql, connection);
            
            select("SELECT * FROM Student where student_id = 2", connection);
            select("SELECT * FROM Student where student_id = 8", connection);
            select("SELECT * FROM Student where student_id = 10", connection);
            
            select("SELECT * FROM instructor where instructor_id = 1", connection);
            select("SELECT * FROM instructor where instructor_id = 2", connection);
            select("SELECT * FROM instructor where instructor_id = 5", connection);
            select("SELECT * FROM instructor where instructor_id = 10", connection);

            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void select(String sql, Connection connection) throws SQLException {
    Statement statement = null;
    ResultSet resultSet = null;
    
    try {
        statement = connection.createStatement(); // Initialize the statement object
        
        resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object value = resultSet.getObject(i);
                System.out.println(columnName + ": " + value);
            }
            System.out.println(); 
        }
    } finally {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
    }
}
}
