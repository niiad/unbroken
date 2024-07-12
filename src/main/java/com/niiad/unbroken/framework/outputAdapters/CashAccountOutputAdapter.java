package com.niiad.unbroken.framework.outputAdapters;

import com.niiad.unbroken.application.outputPorts.CashAccountOutputPort;
import com.niiad.unbroken.domain.entities.local.CashAccount;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CashAccountOutputAdapter implements CashAccountOutputPort {
    private static final String JDBC_URL = "jdbc:sqlite:cash_accounts.db";
    private Connection connection;

    public CashAccountOutputAdapter() {
        try {
            connection = DriverManager.getConnection(JDBC_URL);

            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean persistCashAccount(CashAccount cashAccount) {
        String insertSql = "INSERT INTO accounts (account_name, account_number, bank, account_type, balance) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
            statement.setString(1, cashAccount.getAccountName());
            statement.setString(2, cashAccount.getAccountNumber());
            statement.setString(3, cashAccount.getBank());
            statement.setString(4, cashAccount.getAccountType());
            statement.setDouble(5, cashAccount.getBalance());

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public List<CashAccount> fetchAllCashAccounts() {
        List<CashAccount> accounts = new ArrayList<>();

        String selectSql = "SELECT * FROM accounts";

        try (
                PreparedStatement statement = connection.prepareStatement(selectSql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                CashAccount account = new CashAccount(
                        resultSet.getInt("id"),
                        resultSet.getString("account_name"),
                        resultSet.getString("account_number"),
                        resultSet.getString("bank"),
                        resultSet.getString("account_type"),
                        resultSet.getDouble("balance")
                );

                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public boolean depositIntoCashAccount(CashAccount cashAccount, Double amount) {
        String updateSql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setDouble(1, amount);
            statement.setInt(2, cashAccount.getId());

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public boolean withdrawFromCashAccount(CashAccount cashAccount, Double amount) {
        String updateSql = "UPDATE accounts SET balance = CASE WHEN (balance - ?) < 0 THEN 0 ELSE (balance - ?) END WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setDouble(1, amount);
            statement.setDouble(2, amount);
            statement.setInt(3, cashAccount.getId());

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    private void createTableIfNotExists() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS accounts (id INTEGER PRIMARY KEY AUTOINCREMENT, account_name TEXT, account_number TEXT, bank TEXT, account_type TEXT, balance DOUBLE);";

        try (PreparedStatement statement = connection.prepareStatement(createTableSql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

