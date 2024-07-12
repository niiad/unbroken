package com.niiad.unbroken.application.useCases;

import com.niiad.unbroken.domain.entities.local.Sales;
import org.springframework.stereotype.Component;

@Component
public interface SalesUseCase {
    Sales makeSales(Sales sales);
}

