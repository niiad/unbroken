package com.niiad.unbroken.framework.inputAdapters;

import animatefx.animation.BounceIn;
import animatefx.animation.FadeOut;
import com.niiad.unbroken.application.useCases.CashAccountUseCase;
import com.niiad.unbroken.domain.entities.local.CashAccount;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.Setter;
import org.springframework.stereotype.Component;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.function.Consumer;

@Component
public class CreateCashAccountController {

    @FXML
    TextField accountNameField;

    @FXML
    TextField accountNumberField;

    @FXML
    TextField accountTypeField;

    @FXML
    TextField bankField;

    @FXML
    TextField balanceField;

    @FXML
    JFXButton cancelButton;

    @FXML
    JFXButton addButton;

    private final CashAccountUseCase cashAccountUseCase;

    @Setter
    private Consumer<CashAccount> onCashAccountAddedConsumer;

    public CreateCashAccountController(CashAccountUseCase cashAccountUseCase) {
        this.cashAccountUseCase = cashAccountUseCase;
    }

    @FXML
    void cancelCreateCashAccount(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == cancelButton) {
            closeStage(mouseEvent);
        }
    }

    @FXML
    void createCashAccount(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == addButton) {
            if (accountNameField.getText().isEmpty() || accountNumberField.getText().isEmpty() ||
                    accountTypeField.getText().isEmpty() || bankField.getText().isEmpty() || balanceField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setContentText("Fields should not be empty!");
                alert.setTitle("Status");
                alert.setHeaderText("Operation unsuccessful");

                new BounceIn(alert.getDialogPane()).play();

                alert.showAndWait();

                return;
            }

            try {
                Double.parseDouble(balanceField.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setContentText("Invalid balance value!");
                alert.setTitle("Status");
                alert.setHeaderText("Operation unsuccessful");

                new BounceIn(alert.getDialogPane()).play();

                alert.showAndWait();

                return;
            }

            CashAccount cashAccount = new CashAccount(0, accountNameField.getText(),
                    accountNumberField.getText(), bankField.getText(), accountTypeField.getText(), Double.valueOf(balanceField.getText()));

            notifyCashAccountAdded(cashAccountUseCase.createCashAccount(cashAccount));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("New product created");
            alert.setTitle("Status");
            alert.setHeaderText("Operation successful");

            new BounceIn(alert.getDialogPane()).play();

            alert.showAndWait();

            accountNameField.setText("");
            accountNumberField.setText("");
            bankField.setText("");
            accountTypeField.setText("");
            balanceField.setText("");
        }
    }

    public void notifyCashAccountAdded(CashAccount cashAccount) {
        if (onCashAccountAddedConsumer != null) {
            onCashAccountAddedConsumer.accept(cashAccount);
        }
    }

    private void closeStage(MouseEvent mouseEvent) {
        Node source = (Node) mouseEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        new FadeOut(stage.getScene().getRoot()).play();

        stage.close();
    }
}
