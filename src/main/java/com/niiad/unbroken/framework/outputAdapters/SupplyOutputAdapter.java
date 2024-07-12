package com.niiad.unbroken.framework.outputAdapters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.niiad.unbroken.application.outputPorts.SupplyOutputPort;
import com.niiad.unbroken.domain.entities.local.Supply;

@Component
public class SupplyOutputAdapter implements SupplyOutputPort {
    private static final String JDBC_URL = "jdbc:sqlite:supply.db";
    private Connection connection;

    public SupplyOutputAdapter() {
        try {
            connection = DriverManager.getConnection(JDBC_URL);

            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean persistSupply(Supply supply) {
        String insertSql = "INSERT INTO supplies (supplier, supplier_name, product, product_name, unit_price, quantity, timestamp) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
            statement.setInt(1, supply.getSupplier());
            statement.setString(2, supply.getSupplierName());
            statement.setInt(3, supply.getProduct());
            statement.setString(4, supply.getProductName());
            statement.setString(5, supply.getUnitPrice());
            statement.setInt(6, supply.getQuantity());
            statement.setString(7, supply.getTimestamp());

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public List<Supply> fetchAllSupplies() {
        List<Supply> supplies = new ArrayList<>();

        String selectSql = "SELECT * FROM supplies";

        try (
                PreparedStatement statement = connection.prepareStatement(selectSql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Supply supply = new Supply(
                        resultSet.getInt("id"),
                        resultSet.getInt("supplier"),
                        resultSet.getString("supplier_name"),
                        resultSet.getInt("product"),
                        resultSet.getString("product_name"),
                        resultSet.getString("unit_price"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("timestamp")
                );

                supplies.add(supply);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return supplies;
    }

    private void createTableIfNotExists() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS supplies (id INTEGER PRIMARY KEY AUTOINCREMENT, supplier INTEGER, supplier_name TEXT, product INTEGER, product_name TEXT, unit_price TEXT, quantity INTEGER, timestamp TEXT);";

        try (PreparedStatement statement = connection.prepareStatement(createTableSql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
