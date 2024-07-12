package com.niiad.unbroken.framework.inputAdapters;

import animatefx.animation.BounceIn;
import animatefx.animation.FadeOut;
import com.jfoenix.controls.JFXButton;
import com.niiad.unbroken.application.useCases.TaxRecordUseCase;
import com.niiad.unbroken.domain.entities.local.TaxRecord;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class CreateTaxRecordController {
    private final TaxRecordUseCase taxRecordUseCase;

    @FXML
    TextField taxCodeField;

    @FXML
    TextField taxTypeField;

    @FXML
    TextField amountPaidField;

    @FXML
    DatePicker dateOfPaymentPicker;

    @FXML
    JFXButton addButton;

    @FXML
    JFXButton cancelButton;

    @Setter
    private Consumer<TaxRecord> onTaxRecordAddedConsumer;

    public CreateTaxRecordController(TaxRecordUseCase taxRecordUseCase) {
        this.taxRecordUseCase = taxRecordUseCase;
    }

    private void notifyOnTaxRecordAdded(TaxRecord taxRecord) {
        if (onTaxRecordAddedConsumer != null) {
            onTaxRecordAddedConsumer.accept(taxRecord);
        }
    }

    public void createTaxRecord(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == addButton) {
            if (taxCodeField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setContentText("Tax code cannot be empty!");
                alert.setTitle("Error");
                alert.setHeaderText("Error processing tax code");

                new BounceIn(alert.getDialogPane()).play();

                alert.showAndWait();
            }

            if (taxTypeField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setContentText("Tax type cannot be empty!");
                alert.setTitle("Error");
                alert.setHeaderText("Error processing tax type");

                new BounceIn(alert.getDialogPane()).play();

                alert.showAndWait();
            }

            if (amountPaidField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setContentText("Tax amount cannot be empty!");
                alert.setTitle("Error");
                alert.setHeaderText("Error processing tax amount");

                new BounceIn(alert.getDialogPane()).play();

                alert.showAndWait();
            }

            try {
                Double.parseDouble(amountPaidField.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setContentText("Tax amount value not valid!");
                alert.setTitle("Error");
                alert.setHeaderText("Error processing tax amount");

                new BounceIn(alert.getDialogPane()).play();

                alert.showAndWait();
            }

            String dateOfPayment = dateOfPaymentPicker.getValue().toString();

            TaxRecord taxRecord = new TaxRecord(
                    0,
                    taxCodeField.getText(),
                    taxTypeField.getText(),
                    Double.valueOf(amountPaidField.getText()),
                    dateOfPayment
            );

            notifyOnTaxRecordAdded(taxRecordUseCase.fileTaxRecord(taxRecord));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Tax record filed");
            alert.setTitle("Status");
            alert.setHeaderText("Operation successful");

            new BounceIn(alert.getDialogPane()).play();

            alert.showAndWait();

            taxCodeField.setText("");
            taxTypeField.setText("");
            amountPaidField.setText("");
        }
    }

    public void cancelCreateTaxRecord(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == cancelButton) {
            closeStage(mouseEvent);
        }
    }

    private void closeStage(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        new FadeOut(stage.getScene().getRoot()).play();

        stage.close();
    }
}

