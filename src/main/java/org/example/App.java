package org.example;

import org.example.dao.CountryDao;
import org.example.dao.CountryDaoImpl;
import org.example.model.Country;

import java.sql.*;

public class App
{


    public static void main( String[] args )
    {
        CountryDao countryDao = new CountryDaoImpl();

        Country country = countryDao.create(new Country("LEC","Lexicon","Coding"));

        System.out.println(country);
    }
}
/*
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
 */