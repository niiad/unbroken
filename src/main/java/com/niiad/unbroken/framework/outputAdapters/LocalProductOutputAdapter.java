package com.niiad.unbroken.framework.outputAdapters;

import com.niiad.unbroken.application.outputPorts.LocalProductOutputPort;
import com.niiad.unbroken.domain.entities.local.LocalProduct;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class LocalProductOutputAdapter implements LocalProductOutputPort {
    private static final String JDBC_URL = "jdbc:sqlite:localproduct.db";
    private Connection connection;

    public LocalProductOutputAdapter() {
        try {
            connection = DriverManager.getConnection(JDBC_URL);

            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LocalProduct fetchLocalProductById(Integer productId) {
        return null;
    }

    @Override
    public boolean persistLocalProduct(LocalProduct product) {
        String insertSql = "INSERT INTO local_products (name, category, description, unit_price, cost_price, quantity_in_stock) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getCategory());
            statement.setString(3, product.getDescription());
            statement.setString(4, product.getUnitPrice());
            statement.setString(5, product.getSellingPrice());
            statement.setInt(6, product.getQuantityInStock());

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public List<LocalProduct> fetchAllLocalProducts() {
        List<LocalProduct> products = new ArrayList<>();

        String selectSql = "SELECT * FROM local_products";

        try (
                PreparedStatement statement = connection.prepareStatement(selectSql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                LocalProduct product = new LocalProduct(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("category"),
                        resultSet.getString("description"),
                        resultSet.getString("unit_price"),
                        resultSet.getString("cost_price"),
                        resultSet.getInt("quantity_in_stock")
                );

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public boolean increaseQuantityInStock(Integer productId, Integer quantity) {
        String updateSql = "UPDATE local_products SET quantity_in_stock = quantity_in_stock + ? WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setInt(1, quantity);
            statement.setInt(2, productId);

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public boolean decreaseQuantityInStock(Integer productId, Integer quantity) {
        String updateSql = "UPDATE local_products SET quantity_in_stock = CASE WHEN (quantity_in_stock - ?) < 0 THEN 0 ELSE (quantity_in_stock - ?) END WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setInt(1, quantity);
            statement.setInt(2, quantity);
            statement.setInt(3, productId);

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    private void createTableIfNotExists() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS local_products (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, category TEXT, description TEXT, unit_price TEXT, cost_price TEXT, quantity_in_stock INTEGER);";

        try (PreparedStatement statement = connection.prepareStatement(createTableSql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

