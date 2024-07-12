package com.niiad.unbroken.framework.inputAdapters;

import animatefx.animation.BounceIn;
import animatefx.animation.FadeOut;
import com.jfoenix.controls.JFXButton;
import com.niiad.unbroken.application.outputPorts.CashAccountOutputPort;
import com.niiad.unbroken.application.useCases.TransactionUseCase;
import com.niiad.unbroken.domain.entities.local.CashAccount;
import com.niiad.unbroken.domain.entities.local.CashAccountDisplay;
import com.niiad.unbroken.domain.entities.local.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class DepositIntoCashAccountController implements Initializable {

    @FXML
    JFXButton addButton;

    @FXML
    JFXButton cancelButton;

    @FXML
    Label amountError;

    @FXML
    Label senderError;

    @FXML
    ChoiceBox<CashAccountDisplay> cashAccountChoiceBox;

    @FXML
    TextField amountField;

    @FXML
    TextField senderField;

    private CashAccountOutputPort cashAccountOutputPort;
    private final List<CashAccount> cashAccounts = new ArrayList<>();
    private final TransactionUseCase transactionUseCase;

    private final DateTimeFormatter dateTimeFormatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cashAccounts.clear();
        cashAccounts.addAll(cashAccountOutputPort.fetchAllCashAccounts());

        List<CashAccountDisplay> displayCashAccounts = cashAccounts.stream().map(CashAccount::toDisplay).collect(Collectors.toList());

        cashAccountChoiceBox.getItems().setAll(displayCashAccounts);

        cashAccountChoiceBox.setConverter(new StringConverter<CashAccountDisplay>() {

            @Override
            public String toString(CashAccountDisplay object) {
                return (object != null) ? object.getName() : "";
            }

            @Override
            public CashAccountDisplay fromString(String string) {
                return null;
            }
        });
    }

    public DepositIntoCashAccountController(TransactionUseCase transactionUseCase) {
        this.transactionUseCase = transactionUseCase;
    }

    @FXML
    void makeDeposit(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == addButton) {
            if (cashAccountChoiceBox.getValue() == null) {
                return;
            }

            if (amountField.getText().isEmpty()) {
                amountError.setText("Cannot be empty!");

                return;
            }

            if (senderField.getText().isEmpty()) {
                senderError.setText("Cannot be empty!");

                return;
            }

            try {
                Double.parseDouble(amountField.getText());
            } catch (NumberFormatException e) {
                amountError.setText("Amount should be a number!");

                return;
            }

            CashAccountDisplay selectedCashAccountDisplay = cashAccountChoiceBox.getValue();

            if (selectedCashAccountDisplay != null) {
                CashAccount actualCashAccount = cashAccounts.stream()
                        .filter(cashAccount -> cashAccount.getId().equals(selectedCashAccountDisplay.getId()))
                        .findFirst().orElse(null);

                if (actualCashAccount != null) {
                    LocalDateTime timeOfTransaction = LocalDateTime.now();

                    Transaction transaction = new Transaction(0L, senderField.getText(),
                            actualCashAccount.getAccountName(), Double.valueOf(amountField.getText()), "deposit",
                            timeOfTransaction.format(dateTimeFormatter), "");

                    boolean status = cashAccountOutputPort.depositIntoCashAccount(actualCashAccount, Double.valueOf(amountField.getText()));

                    Transaction performedTransaction = transactionUseCase.performTransaction(transaction);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                    alert.setContentText("New deposit made " + status + ", transaction performed under type " + performedTransaction.getType());
                    alert.setTitle("Status");
                    alert.setHeaderText("Operation successful");

                    new BounceIn(alert.getDialogPane()).play();

                    alert.showAndWait();

                    senderField.setText("");
                    amountField.setText("");
                }
            }
        }
    }

    @FXML
    void cancelMakeDeposit(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == cancelButton) {
            closeStage(mouseEvent);
        }
    }

    @Autowired
    private void initCashAccountOutputPort(CashAccountOutputPort cashAccountOutputPort) {
        this.cashAccountOutputPort = cashAccountOutputPort;
    }

    private void closeStage(MouseEvent mouseEvent) {
        Node source = (Node) mouseEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        new FadeOut(stage.getScene().getRoot()).play();

        stage.close();
    }
}
