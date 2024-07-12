package com.niiad.unbroken.application.useCases;

import com.niiad.unbroken.domain.entities.local.Revenue;
import org.springframework.stereotype.Component;

@Component
public interface RevenueUseCase {
    Revenue generateRevenue(Revenue revenue);
}
