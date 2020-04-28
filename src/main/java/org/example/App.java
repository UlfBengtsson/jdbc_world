package org.example;

import org.example.dao.CountryDao;
import org.example.dao.CountryDaoImpl;
import org.example.model.Country;

import java.io.*;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

public class App
{


    public static void main( String[] args )
    {
        saveToPropFile();
        readPropFile();
    }

    private static void saveToPropFile() {
        try (OutputStream output = new FileOutputStream("./config.properties")) {

            Properties prop = new Properties();

            // set the properties value
            prop.setProperty("db.url", "localhost");
            prop.setProperty("db.user", "username");
            prop.setProperty("db.password", "password");

            // save properties to project root folder
            prop.store(output, null);

            System.out.println(prop);

        } catch (IOException io) {
            io.printStackTrace();
        }

    }

    private static Properties readPropFile() {
        Properties prop = null;
        try (InputStream input = new FileInputStream("./config.properties")) {

            prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            }
            else {
                //load a properties file from class path, inside static method
                prop.load(input);

                //get the property value and print it out
                System.out.println(prop.getProperty("db.url"));
                System.out.println(prop.getProperty("db.user"));
                System.out.println(prop.getProperty("db.password"));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }

}
/*
        CountryDao countryDao = new CountryDaoImpl();

        Country country = countryDao.create(new Country("LEC","Lexicon","Coding"));

        System.out.println(country);
 */


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