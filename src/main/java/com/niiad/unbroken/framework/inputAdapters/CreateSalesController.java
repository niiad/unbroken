package com.niiad.unbroken.framework.inputAdapters;

import animatefx.animation.BounceIn;
import animatefx.animation.FadeOut;
import com.jfoenix.controls.JFXButton;
import com.niiad.unbroken.application.outputPorts.LocalProductOutputPort;
import com.niiad.unbroken.application.useCases.RevenueUseCase;
import com.niiad.unbroken.application.useCases.SalesUseCase;
import com.niiad.unbroken.domain.entities.local.LocalProduct;
import com.niiad.unbroken.domain.entities.local.LocalProductDisplay;
import com.niiad.unbroken.domain.entities.local.Revenue;
import com.niiad.unbroken.domain.entities.local.Sales;
import javafx.event.ActionEvent;
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
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Component
public class CreateSalesController implements Initializable {

    @FXML
    ChoiceBox<LocalProductDisplay> productsChoiceBox;

    @FXML
    TextField unitPriceField;

    @FXML
    TextField sellingPriceField;

    @FXML
    TextField quantityField;

    @FXML
    TextField customerNameField;

    @FXML
    TextField customerContactField;

    @FXML
    Label sellingPriceError;

    @FXML
    Label quantityError;

    @FXML
    Label totalAmountLabel;

    @FXML
    JFXButton addButton;

    @FXML
    JFXButton cancelButton;

    private LocalProductOutputPort productOutputPort;
    private final List<LocalProduct> products = new ArrayList<>();

    private final SalesUseCase salesUseCase;
    private final RevenueUseCase revenueUseCase;

    @Setter
    private Consumer<Sales> onSalesAddedConsumer;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        products.clear();
        products.addAll(productOutputPort.fetchAllLocalProducts());

        List<LocalProductDisplay> displayProducts = products.stream().map(LocalProduct::toDisplay).collect(Collectors.toList());

        productsChoiceBox.getItems().setAll(displayProducts);

        productsChoiceBox.setConverter(new StringConverter<LocalProductDisplay>() {

            @Override
            public String toString(LocalProductDisplay object) {
                return (object != null) ? object.getName() : "";
            }

            @Override
            public LocalProductDisplay fromString(String string) {
                return null;
            }

        });

        productsChoiceBox.setOnAction(this::handleProductSelected);
    }

    public CreateSalesController(SalesUseCase salesUseCase, RevenueUseCase revenueUseCase) {
        this.salesUseCase = salesUseCase;
        this.revenueUseCase = revenueUseCase;
    }

    @FXML
    void createSales(MouseEvent event) {
        if (event.getSource() == addButton) {
            if (productsChoiceBox.getValue() == null) {
                return;
            }

            if (sellingPriceField.getText().isEmpty()) {
                sellingPriceError.setText("Cannot be empty!");

                return;
            }

            if (unitPriceField.getText().isEmpty()) {
                return;
            }

            if (quantityField.getText().isEmpty()) {
                quantityError.setText("Cannot be empty!");

                return;
            }

            try {
                Integer.parseInt(quantityField.getText());
            } catch (NumberFormatException e) {
                quantityError.setText("Quantity should be a number");

                return;
            }

            try {
                Double.parseDouble(sellingPriceField.getText());
            } catch (NumberFormatException e) {
                sellingPriceError.setText("Selling price should be a number");

                return;
            }

            if (Double.parseDouble(sellingPriceField.getText()) <= 0) {
                sellingPriceError.setText("Should be greater than 0");

                return;
            }

            if (Integer.parseInt(quantityField.getText()) <= 0) {
                quantityError.setText("Should be greater than 0");

                return;
            }

            LocalProductDisplay selectedProductDisplay = productsChoiceBox.getValue();

            if (selectedProductDisplay != null) {
                LocalProduct actualProduct = products.stream().filter(product -> product.getId().equals(selectedProductDisplay.getId())).findFirst().orElse(null);

                if (actualProduct != null) {
                    LocalDateTime timeOfSales = LocalDateTime.now();
                    Sales sales = new Sales(0L, actualProduct.getId(), actualProduct.getName(), timeOfSales.format(dateFormatter), unitPriceField.getText(), sellingPriceField.getText(), Integer.valueOf(quantityField.getText()), customerNameField.getText(), customerContactField.getText());

                    Predicate<LocalProduct> availability = LocalProduct.availabilityStatus();

                    if (availability.test(actualProduct)) {
                        boolean status = productOutputPort.decreaseQuantityInStock(actualProduct.getId(), Integer.valueOf(quantityField.getText()));

                        BigDecimal totalAmount = new BigDecimal(sellingPriceField.getText()).multiply(new BigDecimal(quantityField.getText()));

                        totalAmountLabel.setText(totalAmount.toString());

                        String revenueDescription = "sold " + quantityField.getText() + " " + productsChoiceBox.getValue().getName() + " at a price of " + totalAmount.toString();

                        Revenue revenue = new Revenue(0L, totalAmount.toString(), "sales", revenueDescription, timeOfSales.format(dateFormatter));

                        notifySalesAdded(salesUseCase.makeSales(sales));

                        Revenue generatedRevenue = revenueUseCase.generateRevenue(revenue);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("New sales created is " + status + ", revenue generated under category " + generatedRevenue.getCategory());
                        alert.setTitle("Status");
                        alert.setHeaderText("Operation successful");

                        new BounceIn(alert.getDialogPane()).play();

                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Product low on stock");
                        alert.setTitle("Status");
                        alert.setHeaderText("Operation unsuccessful");

                        new BounceIn(alert.getDialogPane()).play();

                        alert.showAndWait();
                    }

                    unitPriceField.setText("");
                    sellingPriceField.setText("");
                    quantityField.setText("");
                    totalAmountLabel.setText("");
                }
            }

        }
    }

    @FXML
    void cancelCreateSales(MouseEvent event) {
        if (event.getSource() == cancelButton) {
            closeStage(event);
        }
    }

    private void handleProductSelected(ActionEvent event) {
        LocalProductDisplay selectedProductDisplay = productsChoiceBox.getValue();

        if (selectedProductDisplay != null) {
            LocalProduct actualProduct = products.stream().filter(product -> product.getId().equals(selectedProductDisplay.getId())).findFirst().orElse(null);

            unitPriceField.setText(actualProduct.getUnitPrice());
            sellingPriceField.setText(actualProduct.getSellingPrice());
        } else {
            unitPriceField.clear();
            sellingPriceField.clear();
        }
    }

    @Autowired
    private void initLocalProductOutputPort(LocalProductOutputPort localProductOutputPort) {
        this.productOutputPort = localProductOutputPort;
    }

    private void notifySalesAdded(Sales sales) {
        if (onSalesAddedConsumer != null) {
            onSalesAddedConsumer.accept(sales);
        }
    }

    private void closeStage(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        new FadeOut(stage.getScene().getRoot()).play();

        stage.close();
    }
}
