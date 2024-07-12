package com.niiad.unbroken.framework.outputAdapters;

import com.niiad.unbroken.application.outputPorts.TransactionOutputPort;
import com.niiad.unbroken.domain.entities.local.Transaction;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionOutputAdapter implements TransactionOutputPort {
    private static final String JDBC_URL = "jdbc:sqlite:transactions.db";
    private Connection connection;

    public TransactionOutputAdapter() {
        try {
            connection = DriverManager.getConnection(JDBC_URL);

            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean persistTransaction(Transaction transaction) {
        String insertSql = "INSERT INTO transactions (sender, receiver, value, type, timestamp, description) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
            statement.setString(1, transaction.getSender());
            statement.setString(2, transaction.getReceiver());
            statement.setDouble(3, transaction.getValue());
            statement.setString(4, transaction.getType());
            statement.setString(5, transaction.getTimestamp());
            statement.setString(6, transaction.getDescription());

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

    }

    @Override
    public List<Transaction> fetchAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        String selectSql = "SELECT * FROM transactions";

        try (
                PreparedStatement statement = connection.prepareStatement(selectSql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Transaction transaction = new Transaction(
                        resultSet.getLong("id"),
                        resultSet.getString("sender"),
                        resultSet.getString("receiver"),
                        resultSet.getDouble("value"),
                        resultSet.getString("type"),
                        resultSet.getString("timestamp"),
                        resultSet.getString("description")
                );

                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    private void createTableIfNotExists() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS transactions (id INTEGER PRIMARY KEY AUTOINCREMENT, sender TEXT, receiver TEXT, value DOUBLE, type TEXT, timestamp TEXT, description TEXT);";

        try (PreparedStatement statement = connection.prepareStatement(createTableSql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
