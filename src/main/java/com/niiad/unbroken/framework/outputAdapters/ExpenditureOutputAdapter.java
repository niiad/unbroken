package com.niiad.unbroken.framework.outputAdapters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.niiad.unbroken.application.outputPorts.ExpenditureOutputPort;
import com.niiad.unbroken.domain.entities.local.Expenditure;


@Component
public class ExpenditureOutputAdapter implements ExpenditureOutputPort {
    private static final String JDBC_URL = "jdbc:sqlite:expenditures.db";
    private Connection connection;

    public ExpenditureOutputAdapter() {
        try {
            connection = DriverManager.getConnection(JDBC_URL);

            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Expenditure fetchExpenditureById(Long expenditureId) {
        return null;
    }

    @Override
    public boolean persistExpenditure(Expenditure expenditure) {
        String insertSql = "INSERT INTO expenditures (category, description, timestamp, amount_spent) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
            statement.setString(1, expenditure.getCategory());
            statement.setString(2, expenditure.getDescription());
            statement.setString(3, expenditure.getTimestamp());
            statement.setString(4, expenditure.getAmountSpent());

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public List<Expenditure> fetchAllExpenditures() {
        List<Expenditure> expenditures = new ArrayList<>();

        String selectSql = "SELECT * FROM expenditures";

        try (
                PreparedStatement statement = connection.prepareStatement(selectSql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Expenditure expenditure = new Expenditure(
                        resultSet.getLong("id"),
                        resultSet.getString("category"),
                        resultSet.getString("description"),
                        resultSet.getString("timestamp"),
                        resultSet.getString("amount_spent")
                );

                expenditures.add(expenditure);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expenditures;
    }

    private void createTableIfNotExists() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS expenditures (id INTEGER PRIMARY KEY AUTOINCREMENT, category TEXT, description TEXT, timestamp TEXT, amount_spent TEXT);";

        try (PreparedStatement statement = connection.prepareStatement(createTableSql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
