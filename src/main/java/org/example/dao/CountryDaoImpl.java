package org.example.dao;

import org.example.model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl implements CountryDao {

    private static final String connectionString = "jdbc:mysql://localhost:3306/world?&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Berlin";
    private static final String userName = "root";
    private static final String userPassword = "root";

    @Override
    public List<Country> getContinent(String continent) {

        List<Country> countries = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection(connectionString, userName, userPassword);
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM country WHERE continent = ?", ResultSet.TYPE_FORWARD_ONLY);//OCP book page 518
            )
        {
            preparedStatement.setString(1, continent);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                countries.add(
                        new Country(
                                resultSet.getString(1),// Col index starts at 1
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;
    }

    @Override
    public List<Country> getRegion(String region) {
        return null;
    }

    @Override
    public Country getByName(String name) {
        return null;
    }

    @Override
    public Country getByCode(String code) {
        return null;
    }

    @Override
    public Country create(Country country) {

        try (
                Connection connection = DriverManager.getConnection(connectionString, userName, userPassword);
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO country (Code,Name,Region) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
        )
        {
            preparedStatement.setString(1, country.getCode());
            preparedStatement.setString(2, country.getName());
            preparedStatement.setString(3, country.getRegion());

            int rowsChanged = preparedStatement.executeUpdate();
            System.out.println("rows changed: " + rowsChanged);

            ResultSet keys = preparedStatement.getGeneratedKeys();

            if (keys.next()) {
                country.setContinent(keys.getString("Continent"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return country;
    }
}
