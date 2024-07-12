package com.niiad.unbroken.framework.outputAdapters;

import com.niiad.unbroken.application.outputPorts.TaxRecordOutputPort;
import com.niiad.unbroken.domain.entities.local.TaxRecord;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaxRecordOutputAdapter implements TaxRecordOutputPort {
    private static final String JDBC_URL = "jdbc:sqlite:taxrecord.db";
    private Connection connection;

    public TaxRecordOutputAdapter() {
        try {
            connection = DriverManager.getConnection(JDBC_URL);

            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TaxRecord fetchTaxRecordById(Integer taxRecordId) {
        return null;
    }

    @Override
    public boolean persistTaxRecord(TaxRecord taxRecord) {
        String insertSql = "INSERT INTO tax_records (code, type, amount_paid, date_of_payment) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
            statement.setString(1, taxRecord.getCode());
            statement.setString(2, taxRecord.getType());
            statement.setDouble(3, taxRecord.getAmountPaid());
            statement.setString(4, taxRecord.getDateOfPayment());

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public List<TaxRecord> fetchAllTaxRecords() {
        List<TaxRecord> taxRecords = new ArrayList<>();

        String selectSql = "SELECT * FROM tax_records";

        try (
                PreparedStatement statement = connection.prepareStatement(selectSql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                TaxRecord taxRecord = new TaxRecord(
                        resultSet.getInt("id"),
                        resultSet.getString("code"),
                        resultSet.getString("type"),
                        resultSet.getDouble("amount_paid"),
                        resultSet.getString("date_of_payment")
                );

                taxRecords.add(taxRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return taxRecords;
    }

    private void createTableIfNotExists() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS tax_records (id INTEGER PRIMARY KEY AUTOINCREMENT, code TEXT, type TEXT, amount_paid TEXT, date_of_payment TEXT);";

        try (PreparedStatement statement = connection.prepareStatement(createTableSql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

