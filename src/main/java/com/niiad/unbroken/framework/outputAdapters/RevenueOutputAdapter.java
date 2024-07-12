package com.niiad.unbroken.framework.outputAdapters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.niiad.unbroken.application.outputPorts.RevenueOutputPort;
import com.niiad.unbroken.domain.entities.local.Revenue;

@Component
public class RevenueOutputAdapter implements RevenueOutputPort {
    private static final String JDBC_URL = "jdbc:sqlite:revenues.db";
    private Connection connection;

    public RevenueOutputAdapter() {
        try {
            connection = DriverManager.getConnection(JDBC_URL);

            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Revenue fetchRevenueById(Long revenueId) {
        return null;
    }

    @Override
    public boolean persistRevenue(Revenue revenue) {
        String insertSql = "INSERT INTO revenues (amount_received, category, description, timestamp) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
            statement.setString(1, revenue.getAmountReceived());
            statement.setString(2, revenue.getCategory());
            statement.setString(3, revenue.getDescription());
            statement.setString(4, revenue.getTimestamp());

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public List<Revenue> fetchAllRevenues() {
        List<Revenue> revenues = new ArrayList<>();

        String selectSql = "SELECT * FROM revenues";

        try (
                PreparedStatement statement = connection.prepareStatement(selectSql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Revenue revenue = new Revenue(
                        resultSet.getLong("id"),
                        resultSet.getString("amount_received"),
                        resultSet.getString("category"),
                        resultSet.getString("description"),
                        resultSet.getString("timestamp")
                );

                revenues.add(revenue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return revenues;
    }

    private void createTableIfNotExists() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS revenues (id INTEGER PRIMARY KEY AUTOINCREMENT, amount_received TEXT, category TEXT, description TEXT, timestamp TEXT);";

        try (PreparedStatement statement = connection.prepareStatement(createTableSql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
