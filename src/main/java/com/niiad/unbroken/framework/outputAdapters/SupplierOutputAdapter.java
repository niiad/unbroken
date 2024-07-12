package com.niiad.unbroken.framework.outputAdapters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.niiad.unbroken.application.outputPorts.SupplierOutputPort;
import com.niiad.unbroken.domain.entities.local.Supplier;

@Component
public class SupplierOutputAdapter implements SupplierOutputPort {
    private static final String JDBC_URL = "jdbc:sqlite:supplier.db";
    private Connection connection;

    public SupplierOutputAdapter() {
        try {
            connection = DriverManager.getConnection(JDBC_URL);

            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean persistSupplier(Supplier supplier) {
        String insertSql = "INSERT INTO suppliers (name, contact, city, region) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getContact());
            statement.setString(3, supplier.getCity());
            statement.setString(4, supplier.getRegion());

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public List<Supplier> fetchAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();

        String selectSql = "SELECT * FROM suppliers";

        try (
                PreparedStatement statement = connection.prepareStatement(selectSql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Supplier supplier = new Supplier(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("contact"),
                        resultSet.getString("city"),
                        resultSet.getString("region")
                );

                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suppliers;
    }

    private void createTableIfNotExists() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS suppliers (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, contact TEXT, city TEXT, region TEXT);";

        try (PreparedStatement statement = connection.prepareStatement(createTableSql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

