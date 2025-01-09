package com.example.CarApp.controller;

import com.example.CarApp.dto.Car;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRowMapper implements RowMapper<Car> {
    @Override
    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
        Car car = new Car();

        car.setId(rs.getInt("ID"));
        car.setName(rs.getString("Name"));
        car.setManufacturingDate(rs.getDate("ManufacturingDate").toLocalDate());
        car.setExpiryDate(rs.getDate("ExpiryDate").toLocalDate());
        car.setModel(rs.getString("Model"));
        car.setEmail(rs.getString("Email"));
        car.setPhoneNo(rs.getString("PhoneNo"));
        car.setAddress(rs.getString("Address"));

        return car;
    }
}
