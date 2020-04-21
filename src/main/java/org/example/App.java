package org.example;

import java.sql.*;

public class App
{
    private static final String connectionString = "jdbc:mysql://localhost:3306/world?&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Berlin";
    private static final String userName = "root";
    private static final String userPassword = "root";

    public static void main( String[] args )
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection(connectionString, userName, userPassword);

            statement = connection.createStatement();

            String getMalmo = "SELECT * FROM city WHERE ID = 3050";

            resultSet = statement.executeQuery(getMalmo);

            while (resultSet.next()) {
                System.out.print("City: " + resultSet.getString("Name"));
                System.out.println(" | District: " + resultSet.getString("District"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
