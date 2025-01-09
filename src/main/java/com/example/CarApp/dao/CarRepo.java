package com.example.CarApp.dao;

import com.example.CarApp.controller.CarRowMapper;
import com.example.CarApp.dto.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class CarRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public String addCar(Car car) {
        String sql = "INSERT INTO Cars (Name, ManufacturingDate, ExpiryDate, Model, Email, PhoneNo, Address)VALUES (?,?,?,?,?,?,?)";
        int rows = jdbcTemplate.update(sql, car.getName(), car.getManufacturingDate(), car.getExpiryDate(), car.getModel(), car.getEmail(), car.getPhoneNo(), car.getAddress());
        if (rows > 0) {
            return "Car added successfully";
        }
        return "Failed To Add Car";
    }

    public Car fetchCarById(int id) {
        String sql = "SELECT * FROM Cars WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new CarRowMapper());
    }

    public String removeCar(int id) {
        String sql = "DELETE FROM Cars WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql,id);
        if (rowsAffected > 0) {
            return "Successfully Deleted Car";
        }
        return "Failed to Delete Car";
    }

    public String updateCar(Car car, int id) {
        String sql = "UPDATE Cars SET Name = ?, ManufacturingDate = ?, ExpiryDate = ? , Model =? , Email = ?, PhoneNo = ?, Address = ? WHERE ID = ?";

        int rowsAffected = jdbcTemplate.update(sql, car.getName(), Date.valueOf(car.getManufacturingDate()), Date.valueOf(car.getExpiryDate()),
                Date.valueOf(car.getManufacturingDate()), car.getModel(), car.getEmail(), car.getPhoneNo(), car.getAddress(), id);

        if (rowsAffected > 0) {
            return "Car Updated Successfully";

        }
        return "Failed To Update Car";
    }
}