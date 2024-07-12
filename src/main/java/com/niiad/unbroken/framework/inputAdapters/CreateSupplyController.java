package com.niiad.unbroken.framework.inputAdapters;

import animatefx.animation.BounceIn;
import animatefx.animation.FadeOut;
import com.jfoenix.controls.JFXButton;
import com.niiad.unbroken.application.outputPorts.LocalProductOutputPort;
import com.niiad.unbroken.application.outputPorts.SupplierOutputPort;
import com.niiad.unbroken.application.useCases.ExpenditureUseCase;
import com.niiad.unbroken.application.useCases.SupplyUseCase;
import com.niiad.unbroken.domain.entities.local.*;
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
import java.util.stream.Collectors;

@Component
public class CreateSupplyController implements Initializable {

    @FXML
    ChoiceBox<LocalProductDisplay> productsChoiceBox;

    @FXML
    TextField unitPriceField;

    @FXML
    TextField quantityField;

    @FXML
    Label unitPriceError;

    @FXML
    Label quantityError;

    @FXML
    ChoiceBox<SupplierDisplay> suppliersChoiceBox;

    @FXML
    Label totalAmountLabel;

    @FXML
    JFXButton cancelButton;

    @FXML
    JFXButton addButton;

    private LocalProductOutputPort localProductOutputPort;
    private final List<LocalProduct> products = new ArrayList<>();

    private SupplierOutputPort supplierOutputPort;
    private final List<Supplier> suppliers = new ArrayList<>();

    private final SupplyUseCase supplyUseCase;

    private final ExpenditureUseCase expenditureUseCase;

    @Setter
    private Consumer<Supply> onSupplyAddedConsumer;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        products.clear();
        products.addAll(localProductOutputPort.fetchAllLocalProducts());

        suppliers.clear();
        suppliers.addAll(supplierOutputPort.fetchAllSuppliers());

        List<LocalProductDisplay> displayProducts = products.stream().map(LocalProduct::toDisplay).collect(Collectors.toList());
        List<SupplierDisplay> displaySuppliers = suppliers.stream().map(Supplier::toDisplay).collect(Collectors.toList());

        productsChoiceBox.getItems().setAll(displayProducts);
        suppliersChoiceBox.getItems().setAll(displaySuppliers);

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

        suppliersChoiceBox.setConverter(new StringConverter<SupplierDisplay>() {

            @Override
            public String toString(SupplierDisplay object) {
                return (object != null) ? object.getName() : "";
            }

            @Override
            public SupplierDisplay fromString(String string) {
                return null;
            }
        });

    }

    public CreateSupplyController(SupplyUseCase supplyUseCase, ExpenditureUseCase expenditureUseCase) {
        this.supplyUseCase = supplyUseCase;
        this.expenditureUseCase = expenditureUseCase;
    }

    @FXML
    void createSupply(MouseEvent event) {
        if (event.getSource() == addButton) {
            if (productsChoiceBox.getValue() == null) {
                return;
            }

            if (unitPriceField.getText().isEmpty()) {
                unitPriceError.setText("Cannot be empty!");

                return;
            }

            if (quantityField.getText().isEmpty()) {
                quantityError.setText("Cannot be empty!");

                return;
            }

            try {
                Double.parseDouble(unitPriceField.getText());
            } catch (NumberFormatException e) {
                unitPriceError.setText("Unit price should be a number!");

                return;
            }

            try {
                Integer.parseInt(quantityField.getText());
            } catch (NumberFormatException e) {
                quantityError.setText("Quantity should be a number!");

                return;
            }

            if (Double.parseDouble(unitPriceField.getText()) <= 0) {
                unitPriceError.setText("Should be greater than 0");

                return;
            }

            if (Integer.parseInt(quantityField.getText()) <= 0) {
                quantityError.setText("Should be greater than 0");

                return;
            }

            if (suppliersChoiceBox.getValue() == null) {
                return;
            }

            LocalProductDisplay selectedProductDisplay = productsChoiceBox.getValue();
            SupplierDisplay selectedSupplierDisplay = suppliersChoiceBox.getValue();

            if (selectedProductDisplay != null && selectedSupplierDisplay != null) {
                LocalProduct actualProduct = products.stream()
                        .filter(product -> product.getId().equals(selectedProductDisplay.getId())).findFirst().orElse(null);
                Supplier actualSupplier = suppliers.stream()
                        .filter(supplier -> supplier.getId().equals(selectedSupplierDisplay.getId())).findFirst().orElse(null);

                if (actualProduct != null && actualSupplier != null) {
                    LocalDateTime timeOfSupply = LocalDateTime.now();

                    Supply supply = new Supply(0, actualSupplier.getId(), actualSupplier.getName(), actualProduct.getId(),
                            actualProduct.getName(), unitPriceField.getText(), Integer.valueOf(quantityField.getText()),
                            timeOfSupply.format(dateFormatter));

                    Expenditure expenditure = new Expenditure(0L, "supply", "",
                            timeOfSupply.format(dateFormatter), unitPriceField.getText());

                    boolean status = localProductOutputPort.increaseQuantityInStock(actualProduct.getId(), Integer.valueOf(quantityField.getText()));

                    Expenditure expenditureMade = expenditureUseCase.makeAnExpenditure(expenditure);

                    BigDecimal totalAmount = new BigDecimal(unitPriceField.getText()).multiply(new BigDecimal(quantityField.getText()));

                    totalAmountLabel.setText(totalAmount.toString());

                    notifySupplyAdded(supplyUseCase.createSupply(supply));

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("New supply created " + status + ", expenditure generated under category " + expenditureMade.getCategory());
                    alert.setTitle("Status");
                    alert.setHeaderText("Operation successful");

                    new BounceIn(alert.getDialogPane()).play();

                    alert.showAndWait();

                    unitPriceField.setText("");
                    quantityField.setText("");
                    totalAmountLabel.setText("");
                }
            }
        }
    }

    @FXML
    void cancelCreateSupply(MouseEvent event) {
        if (event.getSource() == cancelButton) {
            closeStage(event);
        }
    }

    @Autowired
    private void initLocalProductOutputPort(LocalProductOutputPort localProductOutputPort) {
        this.localProductOutputPort = localProductOutputPort;
    }

    @Autowired
    private void initSupplierOutputPort(SupplierOutputPort supplierOutputPort) {
        this.supplierOutputPort = supplierOutputPort;
    }

    private void notifySupplyAdded(Supply supply) {
        if (onSupplyAddedConsumer != null) {
            onSupplyAddedConsumer.accept(supply);
        }
    }

    private void closeStage(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        new FadeOut(stage.getScene().getRoot()).play();

        stage.close();
    }
}

