package com.niiad.unbroken.framework.inputAdapters;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@Component
public class ProductCardController {

    @FXML
    Label productNameLabel;

    @FXML
    Label supplierNameLabel;

    @FXML
    Label productCategoryLabel;

    @FXML
    Label productUnitPriceLabel;

    @FXML
    Label supplierRegionLabel;


}