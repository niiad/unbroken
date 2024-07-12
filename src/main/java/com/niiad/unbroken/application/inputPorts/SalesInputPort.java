package com.niiad.unbroken.application.inputPorts;

import com.niiad.unbroken.application.outputPorts.SalesOutputPort;
import com.niiad.unbroken.application.useCases.SalesUseCase;
import com.niiad.unbroken.domain.entities.local.Sales;
import org.springframework.stereotype.Component;

@Component
public class SalesInputPort implements SalesUseCase {
    private final SalesOutputPort salesOutputPort;

    SalesInputPort(SalesOutputPort salesOutputPort) {
        this.salesOutputPort = salesOutputPort;
    }

    @Override
    public Sales makeSales(Sales sales) {
        if (persistSales(sales)) {
            return sales;
        } else {
            return null;
        }
    }

    private boolean persistSales(Sales sales) {
        return salesOutputPort.persistSales(sales);
    }

}