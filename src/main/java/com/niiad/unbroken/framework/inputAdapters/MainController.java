package com.niiad.unbroken.framework.inputAdapters;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.niiad.unbroken.application.outputPorts.*;
import com.niiad.unbroken.domain.entities.local.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


@Component
public class MainController implements Initializable {

    @FXML
    TabPane mainTabPane;

    @FXML
    Tab dashboardTab;

    @FXML
    Tab salesTab;

    @FXML
    Tab suppliersTab;

    @FXML
    Tab inventoryTab;

    @FXML
    Tab accountingTab;

    @FXML
    Tab taxTab;

    @FXML
    Tab settingsTab;

    @FXML
    Tab marketTab;

    @FXML
    Label dashboardSalesValue;

    @FXML
    Label dashboardSalesCount;

    @FXML
    Label dashboardProductCount;

    @FXML
    Label dashboardProductValue;

    @FXML
    Label dashboardSupplierCount;

    @FXML
    Label dashboardSuppliesCount;

    @FXML
    Label dashboardTotalTaxValue;

    @FXML
    Label dashboardTaxPerMonthValue;

    @FXML
    Label salesCount;

    @FXML
    Label salesQuantity;

    @FXML
    Label salesValue;

    @FXML
    Label supplierCount;

    @FXML
    Label suppliesCount;

    @FXML
    Label suppliesValue;

    @FXML
    Label inventoryProductCount;

    @FXML
    Label inventoryProductValue;

    @FXML
    Label inventoryLowStockProductCount;

    @FXML
    Label inventoryInStockProductCount;

    @FXML
    Label inventoryPercentageInStock;

    @FXML
    Label inventoryDepletedProductCount;

    @FXML
    Label totalRevenueValue;

    @FXML
    Label totalExpenditureValue;

    @FXML
    Label netIncomeValue;

    @FXML
    Label totalBalanceValue;

    @FXML
    Label totalTaxPaidValue;

    @FXML
    Label averageTaxPerMonthValue;

    @FXML
    Label valueAddedTaxValue;

    @FXML
    JFXButton addNewProductButton;

    @FXML
    JFXButton createNewSalesButton;

    @FXML
    JFXButton addNewSupplierButton;

    @FXML
    JFXButton addNewSupplyButton;

    @FXML
    JFXButton addNewCashAccountButton;

    @FXML
    JFXButton depositIntoCashAccountButton;

    @FXML
    JFXButton withdrawFromCashAccountButton;

    @FXML
    JFXButton addTaxRecordButton;

    @FXML
    GridPane marketProductGrid;

    @FXML
    TableView<LocalProduct> productTableView;

    @FXML
    TableView<Sales> salesTableView;

    @FXML
    TableView<Supply> supplyTableView;

    @FXML
    TableView<CashAccount> cashAccountTableView;

    @FXML
    TableView<TaxRecord> taxRecordsTableView;

    @FXML
    TableColumn<LocalProduct, String> productNameColumn;

    @FXML
    TableColumn<LocalProduct, String> productCategoryColumn;

    @FXML
    TableColumn<LocalProduct, String> productUnitPriceColumn;

    @FXML
    TableColumn<LocalProduct, String> productSellingPriceColumn;

    @FXML
    TableColumn<LocalProduct, Integer> productQuantityInStockColumn;

    @FXML
    TableColumn<Sales, String> salesProductNameColumn;

    @FXML
    TableColumn<Sales, String> salesTimestampColumn;

    @FXML
    TableColumn<Sales, String> salesUnitPriceColumn;

    @FXML
    TableColumn<Sales, String> salesSellingPriceColumn;

    @FXML
    TableColumn<Sales, Integer> salesQuantityPurchasedColumn;

    @FXML
    TableColumn<Supply, String> supplySupplyNameColumn;

    @FXML
    TableColumn<Supply, String> supplyProductNameColumn;

    @FXML
    TableColumn<Supply, String> supplyUnitPriceColumn;

    @FXML
    TableColumn<Supply, Integer> supplyQuantityColumn;

    @FXML
    TableColumn<Supply, String> supplyTimestampColumn;

    @FXML
    TableColumn<CashAccount, String> cashAccountNameColumn;

    @FXML
    TableColumn<CashAccount, String> cashAccountNumberColumn;

    @FXML
    TableColumn<CashAccount, String> cashAccountBankColumn;

    @FXML
    TableColumn<CashAccount, String> cashAccountTypeColumn;

    @FXML
    TableColumn<CashAccount, String> cashAccountBalanceColumn;

    @FXML
    TableColumn<TaxRecord, String> taxRecordCodeColumn;

    @FXML
    TableColumn<TaxRecord, String> taxRecordTypeColumn;

    @FXML
    TableColumn<TaxRecord, String> taxRecordAmountPaidColumn;

    @FXML
    TableColumn<TaxRecord, String> taxRecordDateOfPaymentColumn;

    @FXML
    Pagination productPagination;

    @FXML
    Pagination salesPagination;

    @FXML
    Pagination supplyPagination;

    @FXML
    Pagination cashAccountsPagination;

    @FXML
    Pagination taxRecordsPagination;

    @Value("classpath:/createProduct.fxml")
    private Resource createProductResource;

    @Value("classpath:/createSales.fxml")
    private Resource createSalesResource;

    @Value("classpath:/createSupplier.fxml")
    private Resource createSupplierResource;

    @Value("classpath:/createSupply.fxml")
    private Resource createSupplyResource;

    @Value("classpath:/createTaxRecord.fxml")
    private Resource createTaxRecordResource;

    @Value("classpath:/createCashAccount.fxml")
    private Resource createCashAccountResource;

    @Value("classpath:/depositIntoCashAccount.fxml")
    private Resource depositIntoCashAccountResource;

    @Value("classpath:/withdrawFromCashAccount.fxml")
    private Resource withdrawFromCashAccountResource;

    @Value("classpath:/productCard.fxml")
    private Resource productCardResource;

    private ApplicationContext applicationContext;

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat countFormat = NumberFormat.getCompactNumberInstance();
    private static final NumberFormat percentageFormat = NumberFormat.getPercentInstance();

    private CreateProductController createProductController;
    private CreateSalesController createSalesController;
    private CreateSupplierController createSupplierController;
    private CreateSupplyController createSupplyController;
    private CreateCashAccountController createCashAccountController;
    private CreateTaxRecordController createTaxRecordController;
    private ProductCardController productCardController;
    private LocalProductOutputPort productOutputPort;
    private final List<LocalProduct> products = new ArrayList<>();

    private SalesOutputPort salesOutputPort;
    private final List<Sales> sales = new ArrayList<>();

    private SupplierOutputPort supplierOutputPort;
    private final List<Supplier> suppliers = new ArrayList<>();

    private SupplyOutputPort supplyOutputPort;
    private final List<Supply> supplies = new ArrayList<>();

    private RevenueOutputPort revenueOutputPort;
    private final List<Revenue> revenues = new ArrayList<>();

    private ExpenditureOutputPort expenditureOutputPort;
    private final List<Expenditure> expenditures = new ArrayList<>();

    private CashAccountOutputPort cashAccountOutputPort;
    private final List<CashAccount> cashAccounts = new ArrayList<>();

    private TaxRecordOutputPort taxRecordOutputPort;
    private final List<TaxRecord> taxRecords = new ArrayList<>();

    private static final int PRODUCT_ITEM_PER_PAGE = 15;
    private static final int SALES_ITEM_PER_PAGE = 15;
    private static final int SUPPLY_ITEM_PER_PAGE = 15;

    private static final int CASH_ACCOUNT_ITEM_PER_PAGE = 15;

    private static final int TAX_RECORD_ITEM_PER_PAGE = 15;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sales.addAll(salesOutputPort.fetchAllSales());
        dashboardSalesCount.setText(countFormat.format(sales.stream().mapToInt(Sales::getQuantityPurchased).sum()));
        dashboardSalesValue.setText(
                currencyFormat.format(sales.stream()
                        .map(sale -> new BigDecimal(sale.getSellingPrice()).multiply(new BigDecimal(sale.getQuantityPurchased())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
        );

        suppliers.addAll(supplierOutputPort.fetchAllSuppliers());
        dashboardSupplierCount.setText(countFormat.format(Integer.valueOf(suppliers.size())));
        supplierCount.setText(countFormat.format(Integer.valueOf(suppliers.size())));

        products.addAll(productOutputPort.fetchAllLocalProducts());
        dashboardProductCount.setText(countFormat.format(Integer.valueOf(products.size())));
        dashboardProductValue.setText(
                currencyFormat.format(products.stream()
                        .map(product -> new BigDecimal(product.getSellingPrice()).multiply(new BigDecimal(product.getQuantityInStock())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
        );

        supplies.addAll(supplyOutputPort.fetchAllSupplies());
        dashboardSuppliesCount.setText(countFormat.format(Integer.valueOf(supplies.size())));
        suppliesCount.setText(countFormat.format(Integer.valueOf(supplies.size())));
        suppliesValue.setText(
                currencyFormat.format(supplies.stream()
                        .map(supply -> new BigDecimal(supply.getUnitPrice()).multiply(new BigDecimal(supply.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
        );

        revenues.addAll(revenueOutputPort.fetchAllRevenues());
        totalRevenueValue.setText(
                currencyFormat.format(revenues.stream()
                        .map(revenue -> new BigDecimal(revenue.getAmountReceived()))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
        );

        expenditures.addAll(expenditureOutputPort.fetchAllExpenditures());
        totalExpenditureValue.setText(
                currencyFormat.format(expenditures.stream()
                        .map(expenditure -> new BigDecimal(expenditure.getAmountSpent()))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
        );

        netIncomeValue.setText(
                currencyFormat.format(
                        (Double.valueOf(String.valueOf(revenues.stream()
                                .map(revenue -> new BigDecimal(revenue.getAmountReceived()))
                                .reduce(BigDecimal.ZERO, BigDecimal::add))) -

                                Double.valueOf(String.valueOf(expenditures.stream()
                                        .map(expenditure -> new BigDecimal(expenditure.getAmountSpent()))
                                        .reduce(BigDecimal.ZERO, BigDecimal::add)))
                        )
                )
        );

        cashAccounts.addAll(cashAccountOutputPort.fetchAllCashAccounts());
        totalBalanceValue.setText(currencyFormat.format(cashAccounts.stream().mapToDouble(CashAccount::getBalance).sum()));

        salesCount.setText(countFormat.format(Integer.valueOf(sales.size())));
        salesQuantity.setText(countFormat.format(sales.stream().mapToInt(Sales::getQuantityPurchased).sum()));
        salesValue.setText(
                currencyFormat.format(sales.stream()
                        .map(sale -> new BigDecimal(sale.getSellingPrice()).multiply(new BigDecimal(sale.getQuantityPurchased())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
        );

        inventoryProductCount.setText(countFormat.format(Integer.valueOf(products.size())));
        inventoryProductValue.setText(
                currencyFormat.format(products.stream()
                        .map(product -> new BigDecimal(product.getSellingPrice()).multiply(new BigDecimal(product.getQuantityInStock())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
        );
        inventoryLowStockProductCount.setText(
                countFormat.format(Long.valueOf(products.stream().filter(product -> product.getQuantityInStock() <= 5).count()))
        );
        inventoryInStockProductCount.setText(
                countFormat.format(Long.valueOf(products.stream().filter(product -> product.getQuantityInStock() > 5).count()))
        );
        inventoryPercentageInStock.setText(
                percentageFormat.format(
                        Double.valueOf(Double.valueOf(inventoryInStockProductCount.getText()) / Double.valueOf(inventoryProductCount.getText()))
                )
        );
        inventoryDepletedProductCount.setText(
                countFormat.format(Long.valueOf(products.stream().filter(product -> product.getQuantityInStock() == 0).count()))
        );

        productNameColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getName())
        );
        productCategoryColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getCategory())
        );
        productUnitPriceColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(currencyFormat.format(Double.valueOf(cellData.getValue().getUnitPrice())))
        );
        productSellingPriceColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(currencyFormat.format(Double.valueOf(cellData.getValue().getSellingPrice())))
        );
        productQuantityInStockColumn.setCellValueFactory(
                cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantityInStock()).asObject()
        );

        salesProductNameColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getProductName())
        );
        salesTimestampColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getTimestamp())
        );
        salesUnitPriceColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getUnitPrice())
        );
        salesSellingPriceColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getSellingPrice())
        );
        salesQuantityPurchasedColumn.setCellValueFactory(
                cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantityPurchased()).asObject()
        );

        supplySupplyNameColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getSupplierName())
        );
        supplyProductNameColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getProductName())
        );
        supplyUnitPriceColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(currencyFormat.format(Double.valueOf(cellData.getValue().getUnitPrice())))
        );
        supplyQuantityColumn.setCellValueFactory(
                cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject()
        );
        supplyTimestampColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getTimestamp())
        );

        cashAccountNameColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getAccountName())
        );
        cashAccountNumberColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getAccountNumber())
        );
        cashAccountBankColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getBank())
        );
        cashAccountTypeColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getAccountType())
        );
        cashAccountBalanceColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(currencyFormat.format(cellData.getValue().getBalance()))
        );

        taxRecordCodeColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getCode())
        );
        taxRecordTypeColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getType())
        );
        taxRecordAmountPaidColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(currencyFormat.format(cellData.getValue().getAmountPaid()))
        );
        taxRecordDateOfPaymentColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getDateOfPayment())
        );

        taxRecords.addAll(taxRecordOutputPort.fetchAllTaxRecords());
        totalTaxPaidValue.setText(currencyFormat.format(taxRecords.stream().mapToDouble(TaxRecord::getAmountPaid).sum()));
        dashboardTotalTaxValue.setText(currencyFormat.format(taxRecords.stream().mapToDouble(TaxRecord::getAmountPaid).sum()));


        productPagination.setPageCount((int) Math.ceil((double) products.size() / PRODUCT_ITEM_PER_PAGE));
        productPagination.setPageFactory(this::createProductPage);

        salesPagination.setPageCount((int) Math.ceil((double) sales.size() / SALES_ITEM_PER_PAGE));
        salesPagination.setPageFactory(this::createSalesPage);

        supplyPagination.setPageCount((int) Math.ceil((double) supplies.size() / SUPPLY_ITEM_PER_PAGE));
        supplyPagination.setPageFactory(this::createSupplyPage);

        cashAccountsPagination.setPageCount((int) Math.ceil((double) cashAccounts.size() / CASH_ACCOUNT_ITEM_PER_PAGE));
        cashAccountsPagination.setPageFactory(this::createCashAccountPage);

        taxRecordsPagination.setPageCount((int) Math.ceil((double) taxRecords.size() / TAX_RECORD_ITEM_PER_PAGE));
        taxRecordsPagination.setPageFactory(this::createTaxRecordPage);

        createProductController.setOnProductAddedConsumer(this::handleProductAdded);
        createSalesController.setOnSalesAddedConsumer(this::handleSalesAdded);
        createSupplierController.setOnSupplierAddedConsumer(this::handleSupplierAdded);
        createSupplyController.setOnSupplyAddedConsumer(this::handleSupplyAdded);
        createCashAccountController.setOnCashAccountAddedConsumer(this::handleCashAccountAdded);
        createTaxRecordController.setOnTaxRecordAddedConsumer(this::handleTaxRecordAdded);

        int column = 0;
        int row = 0;

        for (int i = 0; i < 20; i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(productCardResource.getURL());
                fxmlLoader.setControllerFactory(applicationContext::getBean);

                marketProductGrid.add(fxmlLoader.load(), column, row);

                column++;
                if (column == 4) {
                    column = 0;
                    row++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void addNewProduct(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == addNewProductButton) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(createProductResource.getURL());
                fxmlLoader.setControllerFactory(applicationContext::getBean);

                Scene scene = new Scene(fxmlLoader.load());

                Stage stage = new Stage();
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);

                new FadeIn(stage.getScene().getRoot()).play();

                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void createNewSales(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == createNewSalesButton) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(createSalesResource.getURL());
                fxmlLoader.setControllerFactory(applicationContext::getBean);

                Scene scene = new Scene(fxmlLoader.load());

                Stage stage = new Stage();
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);

                new FadeIn(stage.getScene().getRoot()).play();

                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void createNewSupplier(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == addNewSupplierButton) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(createSupplierResource.getURL());
                fxmlLoader.setControllerFactory(applicationContext::getBean);

                Scene scene = new Scene(fxmlLoader.load());

                Stage stage = new Stage();
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);

                new FadeIn(stage.getScene().getRoot()).play();

                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void createNewSupply(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == addNewSupplyButton) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(createSupplyResource.getURL());
                fxmlLoader.setControllerFactory(applicationContext::getBean);

                Scene scene = new Scene(fxmlLoader.load());

                Stage stage = new Stage();
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);

                new FadeIn(stage.getScene().getRoot()).play();

                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void addNewCashAccount(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == addNewCashAccountButton) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(createCashAccountResource.getURL());
                fxmlLoader.setControllerFactory(applicationContext::getBean);

                Scene scene = new Scene(fxmlLoader.load());

                Stage stage = new Stage();
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);

                new FadeIn(stage.getScene().getRoot()).play();

                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void addNewTaxRecord(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == addTaxRecordButton) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(createTaxRecordResource.getURL());
                fxmlLoader.setControllerFactory(applicationContext::getBean);

                Scene scene = new Scene(fxmlLoader.load());

                Stage stage = new Stage();
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);

                new FadeIn(stage.getScene().getRoot()).play();

                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void depositIntoCashAccount(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == depositIntoCashAccountButton) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(depositIntoCashAccountResource.getURL());
                fxmlLoader.setControllerFactory(applicationContext::getBean);

                Scene scene = new Scene(fxmlLoader.load());

                Stage stage = new Stage();
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);

                new FadeIn(stage.getScene().getRoot()).play();

                stage.showAndWait();

                cashAccounts.clear();
                cashAccounts.addAll(cashAccountOutputPort.fetchAllCashAccounts());
                refreshCashAccountTableView();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void withdrawFromCashAccount(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == withdrawFromCashAccountButton) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(withdrawFromCashAccountResource.getURL());
                fxmlLoader.setControllerFactory(applicationContext::getBean);

                Scene scene = new Scene(fxmlLoader.load());

                Stage stage = new Stage();
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);

                new FadeIn(stage.getScene().getRoot()).play();

                stage.showAndWait();

                cashAccounts.clear();
                cashAccounts.addAll(cashAccountOutputPort.fetchAllCashAccounts());
                refreshCashAccountTableView();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private AnchorPane createProductPage(int pageIndex) {
        AnchorPane pageContent = new AnchorPane();
        int fromIndex = pageIndex * PRODUCT_ITEM_PER_PAGE;
        int toIndex = Math.min(fromIndex + PRODUCT_ITEM_PER_PAGE, products.size());

        if (fromIndex < toIndex) {
            List<LocalProduct> pageItems = products.subList(fromIndex, toIndex);
            productTableView.getItems().setAll(pageItems);
        } else {
            productTableView.getItems().clear();
        }


        return pageContent;
    }

    private AnchorPane createSalesPage(int pageIndex) {
        AnchorPane pageContent = new AnchorPane();

        int fromIndex = pageIndex * SALES_ITEM_PER_PAGE;
        int toIndex = Math.min(fromIndex + SALES_ITEM_PER_PAGE, sales.size());

        if (fromIndex < toIndex) {
            List<Sales> pageItems = sales.subList(fromIndex, toIndex);

            salesTableView.getItems().setAll(pageItems);
        } else {
            salesTableView.getItems().clear();
        }

        return pageContent;
    }

    private AnchorPane createSupplyPage(int pageIndex) {
        AnchorPane pageContent = new AnchorPane();

        int fromIndex = pageIndex * SUPPLY_ITEM_PER_PAGE;
        int toIndex = Math.min(fromIndex + SUPPLY_ITEM_PER_PAGE, supplies.size());

        if (fromIndex < toIndex) {
            List<Supply> pageItems = supplies.subList(fromIndex, toIndex);

            supplyTableView.getItems().setAll(pageItems);
        } else {
            supplyTableView.getItems().clear();
        }

        return pageContent;
    }

    private AnchorPane createCashAccountPage(int pageIndex) {
        AnchorPane pageContent = new AnchorPane();

        int fromIndex = pageIndex * CASH_ACCOUNT_ITEM_PER_PAGE;
        int toIndex = Math.min(fromIndex + CASH_ACCOUNT_ITEM_PER_PAGE, cashAccounts.size());

        if (fromIndex < toIndex) {
            List<CashAccount> pageItems = cashAccounts.subList(fromIndex, toIndex);

            cashAccountTableView.getItems().setAll(pageItems);
        } else {
            cashAccountTableView.getItems().clear();
        }

        return pageContent;
    }

    private AnchorPane createTaxRecordPage(int pageIndex) {
        AnchorPane pageContent = new AnchorPane();

        int fromIndex = pageIndex * TAX_RECORD_ITEM_PER_PAGE;
        int toIndex = Math.min(fromIndex + TAX_RECORD_ITEM_PER_PAGE, taxRecords.size());

        if (fromIndex < toIndex) {
            List<TaxRecord> pageItems = taxRecords.subList(fromIndex, toIndex);

            taxRecordsTableView.getItems().setAll(pageItems);
        } else {
            taxRecordsTableView.getItems().clear();
        }

        return pageContent;
    }

    private void handleProductAdded(LocalProduct product) {
        products.add(product);

        refreshProductTableView();
    }

    private void handleSalesAdded(Sales sale) {
        sales.add(sale);

        products.clear();
        products.addAll(productOutputPort.fetchAllLocalProducts());

        refreshSalesTableView();
        refreshProductTableView();
    }

    private void handleSupplierAdded(Supplier supplier) {
        suppliers.add(supplier);

        refreshSupplierView();
    }

    private void handleSupplyAdded(Supply supply) {
        supplies.add(supply);

        products.clear();
        products.addAll(productOutputPort.fetchAllLocalProducts());

        refreshSupplyTableView();
        refreshProductTableView();
    }

    private void handleCashAccountAdded(CashAccount cashAccount) {
        cashAccounts.add(cashAccount);

        refreshCashAccountTableView();
    }

    private void handleTaxRecordAdded(TaxRecord taxRecord) {
        taxRecords.add(taxRecord);

        refreshTaxRecordsTableView();
    }

    private void refreshSupplierView() {
        dashboardSupplierCount.setText(countFormat.format(Integer.valueOf(suppliers.size())));
        supplierCount.setText(countFormat.format(Integer.valueOf(suppliers.size())));
    }

    private void refreshProductTableView() {
        productTableView.getItems().clear();

        int currentPageIndex = productPagination.getCurrentPageIndex();

        createProductPage(currentPageIndex);

        dashboardProductCount.setText(countFormat.format(Integer.valueOf(products.size())));
        dashboardProductValue.setText(
                currencyFormat.format(products.stream().map(product -> new BigDecimal(product.getSellingPrice())
                        .multiply(new BigDecimal(product.getQuantityInStock()))).reduce(BigDecimal.ZERO, BigDecimal::add)
                )
        );

        inventoryProductCount.setText(countFormat.format(Integer.valueOf(products.size())));
        inventoryProductValue.setText(
                currencyFormat.format(products.stream().map(product -> new BigDecimal(product.getSellingPrice())
                        .multiply(new BigDecimal(product.getQuantityInStock()))).reduce(BigDecimal.ZERO, BigDecimal::add)
                )
        );
        inventoryLowStockProductCount.setText(
                countFormat.format(Long.valueOf(products.stream().filter(product -> product.getQuantityInStock() <= 5).count()))
        );
        inventoryInStockProductCount.setText(
                countFormat.format(Long.valueOf(products.stream().filter(product -> product.getQuantityInStock() > 5).count()))
        );
        inventoryPercentageInStock.setText(
                percentageFormat.format(Double.valueOf(Double.valueOf(inventoryInStockProductCount.getText()) /
                        Double.valueOf(inventoryProductCount.getText()))
                )
        );
        inventoryDepletedProductCount.setText(
                countFormat.format(Long.valueOf(products.stream().filter(product -> product.getQuantityInStock() == 0).count()))
        );
    }

    private void refreshSalesTableView() {
        salesTableView.getItems().clear();

        int currentPageIndex = salesPagination.getCurrentPageIndex();

        createSalesPage(currentPageIndex);

        dashboardSalesCount.setText(countFormat.format(sales.stream().mapToInt(Sales::getQuantityPurchased).sum()));
        dashboardSalesValue.setText(
                currencyFormat.format(sales.stream().map(sale -> new BigDecimal(sale.getSellingPrice())
                        .multiply(new BigDecimal(sale.getQuantityPurchased()))).reduce(BigDecimal.ZERO, BigDecimal::add)
                )
        );

        salesCount.setText(countFormat.format(Integer.valueOf(sales.size())));
        salesQuantity.setText(countFormat.format(sales.stream().mapToInt(Sales::getQuantityPurchased).sum()));
        salesValue.setText(
                currencyFormat.format(sales.stream().map(sale -> new BigDecimal(sale.getSellingPrice())
                        .multiply(new BigDecimal(sale.getQuantityPurchased()))).reduce(BigDecimal.ZERO, BigDecimal::add)
                )
        );

        revenues.clear();
        revenues.addAll(revenueOutputPort.fetchAllRevenues());
        totalRevenueValue.setText(
                currencyFormat.format(revenues.stream()
                        .map(revenue -> new BigDecimal(revenue.getAmountReceived()))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
        );

        netIncomeValue.setText(
                currencyFormat.format(
                        (Double.valueOf(String.valueOf(revenues.stream()
                                .map(revenue -> new BigDecimal(revenue.getAmountReceived()))
                                .reduce(BigDecimal.ZERO, BigDecimal::add))) -

                                Double.valueOf(String.valueOf(expenditures.stream()
                                        .map(expenditure -> new BigDecimal(expenditure.getAmountSpent()))
                                        .reduce(BigDecimal.ZERO, BigDecimal::add)))
                        )
                )
        );
    }

    private void refreshSupplyTableView() {
        supplyTableView.getItems().clear();

        int currentPageIndex = supplyPagination.getCurrentPageIndex();

        createSupplyPage(currentPageIndex);

        dashboardSuppliesCount.setText(countFormat.format(Integer.valueOf(supplies.size())));
        suppliesCount.setText(countFormat.format(Integer.valueOf(supplies.size())));
        suppliesValue.setText(
                currencyFormat.format(supplies.stream().map(supply -> new BigDecimal(supply.getUnitPrice())
                        .multiply(new BigDecimal(supply.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add)
                )
        );

        expenditures.clear();
        expenditures.addAll(expenditureOutputPort.fetchAllExpenditures());

        totalExpenditureValue.setText(
                currencyFormat.format(expenditures.stream()
                        .map(expenditure -> new BigDecimal(expenditure.getAmountSpent()))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
        );

        netIncomeValue.setText(
                currencyFormat.format(
                        (Double.valueOf(String.valueOf(revenues.stream()
                                .map(revenue -> new BigDecimal(revenue.getAmountReceived()))
                                .reduce(BigDecimal.ZERO, BigDecimal::add))) -

                                Double.valueOf(String.valueOf(expenditures.stream()
                                        .map(expenditure -> new BigDecimal(expenditure.getAmountSpent()))
                                        .reduce(BigDecimal.ZERO, BigDecimal::add)))
                        )
                )
        );
    }

    private void refreshCashAccountTableView() {
        cashAccountTableView.getItems().clear();

        int currentPageIndex = cashAccountsPagination.getCurrentPageIndex();

        createCashAccountPage(currentPageIndex);

        totalBalanceValue.setText(currencyFormat.format(cashAccounts.stream().mapToDouble(CashAccount::getBalance).sum()));
    }

    private void refreshTaxRecordsTableView() {
        taxRecordsTableView.getItems().clear();

        int currentPageIndex = taxRecordsPagination.getCurrentPageIndex();

        createTaxRecordPage(currentPageIndex);

        totalTaxPaidValue.setText(currencyFormat.format(taxRecords.stream().mapToDouble(TaxRecord::getAmountPaid).sum()));
        dashboardTotalTaxValue.setText(currencyFormat.format(taxRecords.stream().mapToDouble(TaxRecord::getAmountPaid).sum()));

    }

    @Autowired
    private void initContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Autowired
    private void initSalesOutputPort(SalesOutputPort salesOutputPort) {
        this.salesOutputPort = salesOutputPort;
    }

    @Autowired
    private void initSupplierOutputPort(SupplierOutputPort supplierOutputPort) {
        this.supplierOutputPort = supplierOutputPort;
    }

    @Autowired
    private void initLocalProductOutputPort(LocalProductOutputPort productOutputPort) {
        this.productOutputPort = productOutputPort;
    }

    @Autowired
    private void initSupplyOutputPort(SupplyOutputPort supplyOutputPort) {
        this.supplyOutputPort = supplyOutputPort;
    }

    @Autowired
    private void initRevenueOutputPort(RevenueOutputPort revenueOutputPort) {
        this.revenueOutputPort = revenueOutputPort;
    }

    @Autowired
    private void initExpenditureOutputPort(ExpenditureOutputPort expenditureOutputPort) {
        this.expenditureOutputPort = expenditureOutputPort;
    }

    @Autowired
    private void initCashAccountOutputPort(CashAccountOutputPort cashAccountOutputPort) {
        this.cashAccountOutputPort = cashAccountOutputPort;
    }

    @Autowired
    private void initTaxRecordOutputPort(TaxRecordOutputPort taxRecordOutputPort) {
        this.taxRecordOutputPort = taxRecordOutputPort;
    }

    @Autowired
    private void initCreateSalesController(CreateSalesController createSalesController) {
        this.createSalesController = createSalesController;
    }

    @Autowired
    private void initCreateProductController(CreateProductController createProductController) {
        this.createProductController = createProductController;
    }

    @Autowired
    private void initCreateSupplierController(CreateSupplierController createSupplierController) {
        this.createSupplierController = createSupplierController;
    }

    @Autowired
    private void initCreateSupplyController(CreateSupplyController createSupplyController) {
        this.createSupplyController = createSupplyController;
    }

    @Autowired
    private void initCreateCashAccountController(CreateCashAccountController createCashAccountController) {
        this.createCashAccountController = createCashAccountController;
    }

    @Autowired
    private void initCreateTaxRecordController(CreateTaxRecordController createTaxRecordController) {
        this.createTaxRecordController = createTaxRecordController;
    }

    @Autowired
    private void initProductCardController(ProductCardController productCardController) {
        this.productCardController = productCardController;
    }

}

