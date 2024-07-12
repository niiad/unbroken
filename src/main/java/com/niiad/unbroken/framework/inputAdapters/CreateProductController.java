package com.niiad.unbroken.framework.inputAdapters;

import animatefx.animation.BounceIn;
import animatefx.animation.FadeOut;
import com.jfoenix.controls.JFXButton;
import com.niiad.unbroken.application.useCases.LocalProductUseCase;
import com.niiad.unbroken.domain.entities.local.LocalProduct;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class CreateProductController {
    @FXML
    TextField nameField;

    @FXML
    TextField unitPriceField;

    @FXML
    TextField sellingPriceField;

    @FXML
    TextField categoryField;

    @FXML
    TextField quantityField;

    @FXML
    TextArea descriptionArea;

    @FXML
    JFXButton cancelButton;

    @FXML
    JFXButton addButton;

    @FXML
    Label nameFieldError;

    @FXML
    Label unitPriceError;

    @FXML
    Label costPriceError;

    @FXML
    Label categoryError;

    @FXML
    Label quantityError;

    private final LocalProductUseCase productUseCase;

    @Setter
    private Consumer<LocalProduct> onProductAddedConsumer;

    public CreateProductController(LocalProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @FXML
    void createProduct(MouseEvent event) {
        if (event.getSource() == addButton) {
            if (nameField.getText().isEmpty()) {
                nameFieldError.setText("Product name cannot be empty!");

                return;
            }

            if (unitPriceField.getText().isEmpty()) {
                unitPriceError.setText("Unit price cannot be empty!");

                return;
            }

            if (sellingPriceField.getText().isEmpty()) {
                costPriceError.setText("Cost price cannot be empty!");

                return;
            }

            if (categoryField.getText().isEmpty()) {
                categoryError.setText("Category cannot be empty!");

                return;
            }

            if (quantityField.getText().isEmpty()) {
                quantityError.setText("Specify an initial quantity");

                return;
            }

            try {
                Double.parseDouble(unitPriceField.getText());
            } catch (NumberFormatException e) {
                unitPriceError.setText("Unit price should be a number");

                return;
            }

            try {
                Double.parseDouble(sellingPriceField.getText());
            } catch (NumberFormatException e) {
                costPriceError.setText("Cost price should be a number");

                return;
            }

            try {
                Integer.parseInt(quantityField.getText());
            } catch (NumberFormatException e) {
                quantityError.setText("Quantity should be a number");

                return;
            }

            LocalProduct product = new LocalProduct(0,
                    nameField.getText(), categoryField.getText(), descriptionArea.getText(),
                    unitPriceField.getText(), sellingPriceField.getText(), Integer.valueOf(quantityField.getText()));

            notifyProductAdded(productUseCase.createLocalProduct(product));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("New product created");
            alert.setTitle("Status");
            alert.setHeaderText("Operation successful");

            new BounceIn(alert.getDialogPane()).play();

            alert.showAndWait();

            nameField.setText("");
            unitPriceField.setText("");
            sellingPriceField.setText("");
            categoryField.setText("");
            quantityField.setText("");
            descriptionArea.setText("");
        }
    }

    @FXML
    void cancelCreateProduct(MouseEvent event) {
        if (event.getSource() == cancelButton) {
            closeStage(event);
        }
    }

    private void notifyProductAdded(LocalProduct product) {
        if (onProductAddedConsumer != null) {
            onProductAddedConsumer.accept(product);
        }
    }

    private void closeStage(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        new FadeOut(stage.getScene().getRoot()).play();

        stage.close();
    }
}

