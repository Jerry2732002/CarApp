package com.example.CarApp.dao;

import com.example.CarApp.dto.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class CarRepo {
    Connection connection;

    public CarRepo(@Value("${database.url}") String URL, @Value("${database.username}") String USERNAME, @Value("${database.password}") String PASSWORD) throws SQLException {
        this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public String addCar(Car car) {
        String sql = "INSERT INTO Cars (Name, ManufacturingDate, ExpiryDate, Model, Email, PhoneNo, Address)VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, car.getName());
            statement.setDate(2, Date.valueOf(car.getManufacturingDate()));
            statement.setDate(3, Date.valueOf(car.getExpiryDate()));
            statement.setString(4, car.getModel());
            statement.setString(5, car.getEmail());
            statement.setString(6, car.getPhoneNo());
            statement.setString(7, car.getAddress());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                return "Car Added Successfully";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Failed To Add Car";
    }

    public Car fetchCarById(int id) {
        String sql = "SELECT * FROM Cars WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Car car = new Car();
                    car.setId(resultSet.getInt("ID"));
                    car.setName(resultSet.getString("Name"));
                    car.setManufacturingDate(resultSet.getDate("ManufacturingDate").toLocalDate());
                    car.setExpiryDate(resultSet.getDate("ExpiryDate").toLocalDate());
                    car.setModel(resultSet.getString("Model"));
                    car.setEmail(resultSet.getString("Email"));
                    car.setPhoneNo(resultSet.getString("PhoneNo"));
                    car.setAddress(resultSet.getString("Address"));
                    return car;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String removeCar(int id) {
        String sql = "DELETE FROM Cars WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return "Successfully Deleted Car";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Failed to Delete Car";
    }

    public String updateCar(Car car, int id) {
        String sql = "UPDATE Cars SET Name = ?, ManufacturingDate = ?, ExpiryDate = ? , Model =? , Email = ?, PhoneNo = ?, Address = ? WHERE ID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, car.getName());
            statement.setDate(2, Date.valueOf(car.getManufacturingDate()));
            statement.setDate(3, Date.valueOf(car.getExpiryDate()));
            statement.setString(4, car.getModel());
            statement.setString(5, car.getEmail());
            statement.setString(6, car.getPhoneNo());
            statement.setString(7, car.getAddress());
            statement.setInt(8,id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                return "Car Updated Successfully";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Failed To Update Car";
    }
}