package com.niiad.unbroken.framework.outputAdapters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.niiad.unbroken.application.outputPorts.SalesOutputPort;
import com.niiad.unbroken.domain.entities.local.Sales;

@Component
public class SalesOutputAdapter implements SalesOutputPort {
    private static final String JDBC_URL = "jdbc:sqlite:sales.db";
    private Connection connection;

    public SalesOutputAdapter() {
        try {
            connection = DriverManager.getConnection(JDBC_URL);

            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Sales fetchSalesById(Integer salesId) {
        return null;
    }

    @Override
    public boolean persistSales(Sales sales) {
        String insertSql = "INSERT INTO sales (product, product_name, timestamp, unit_price, selling_price, quantity_purchased, customer_name, customer_contact) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(insertSql)) {
            statement.setInt(1, sales.getProductId());
            statement.setString(2, sales.getProductName());
            statement.setString(3, sales.getTimestamp());
            statement.setString(4, sales.getUnitPrice());
            statement.setString(5, sales.getSellingPrice());
            statement.setInt(6, sales.getQuantityPurchased());
            statement.setString(7, sales.getCustomerName());
            statement.setString(8, sales.getCustomerContact());

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public List<Sales> fetchAllSales() {
        List<Sales> sales = new ArrayList<>();

        String selectSql = "SELECT * FROM sales";

        try (
                PreparedStatement statement = connection.prepareStatement(selectSql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Sales sale = new Sales(
                        resultSet.getLong("id"),
                        resultSet.getInt("product"),
                        resultSet.getString("product_name"),
                        resultSet.getString("timestamp"),
                        resultSet.getString("unit_price"),
                        resultSet.getString("selling_price"),
                        resultSet.getInt("quantity_purchased"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("customer_contact")
                );

                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sales;
    }

    private void createTableIfNotExists() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS sales (id INTEGER PRIMARY KEY AUTOINCREMENT, product INTEGER, product_name TEXT, timestamp TEXT, unit_price TEXT, selling_price TEXT, quantity_purchased INTEGER, customer_name TEXT, customer_contact TEXT);";

        try (PreparedStatement statement = connection.prepareStatement(createTableSql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
