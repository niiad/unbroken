package com.niiad.unbroken.framework.inputAdapters;

import animatefx.animation.BounceIn;
import animatefx.animation.FadeOut;
import com.jfoenix.controls.JFXButton;
import com.niiad.unbroken.application.useCases.SupplierUseCase;
import com.niiad.unbroken.domain.entities.local.Supplier;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class CreateSupplierController {

    @FXML
    TextField supplierNameField;

    @FXML
    TextField supplierContactField;

    @FXML
    TextField supplierCityField;

    @FXML
    TextField supplierRegionField;

    @FXML
    JFXButton cancelButton;

    @FXML
    JFXButton addButton;

    private final SupplierUseCase supplierUseCase;

    private Consumer<Supplier> onSupplierAddedConsumer;

    public CreateSupplierController(SupplierUseCase supplierUseCase) {
        this.supplierUseCase = supplierUseCase;
    }

    @FXML
    void createSupplier(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == addButton) {
            if (supplierNameField.getText().isEmpty() || supplierContactField.getText().isEmpty() || supplierCityField.getText().isEmpty() || supplierRegionField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Empty field");
                alert.setTitle("Status");
                alert.setHeaderText("Operation unsuccessful");

                new BounceIn(alert.getDialogPane()).play();

                alert.showAndWait();

                return;
            }

            Supplier supplier = new Supplier(0, supplierNameField.getText(), supplierContactField.getText(), supplierCityField.getText(), supplierRegionField.getText());

            notifySupplierAdded(supplierUseCase.createSupplier(supplier));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("New supplier created");
            alert.setTitle("Status");
            alert.setHeaderText("Operation successful");

            new BounceIn(alert.getDialogPane()).play();

            alert.showAndWait();

            supplierNameField.setText("");
            supplierContactField.setText("");
            supplierCityField.setText("");
            supplierRegionField.setText("");
        }
    }

    @FXML
    void cancelCreateSupplier(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == cancelButton) {
            closeStage(mouseEvent);
        }
    }

    public void setOnSupplierAddedConsumer(Consumer<Supplier> consumer) {
        this.onSupplierAddedConsumer = consumer;
    }

    private void notifySupplierAdded(Supplier supplier) {
        if (onSupplierAddedConsumer != null) {
            onSupplierAddedConsumer.accept(supplier);
        }
    }

    private void closeStage(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        new FadeOut(stage.getScene().getRoot()).play();

        stage.close();
    }

}

