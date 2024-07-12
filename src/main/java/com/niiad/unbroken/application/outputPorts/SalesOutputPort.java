package com.niiad.unbroken.application.outputPorts;

import com.niiad.unbroken.domain.entities.local.Sales;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SalesOutputPort {
    Sales fetchSalesById(Integer salesId);

    boolean persistSales(Sales sales);

    List<Sales> fetchAllSales();
}