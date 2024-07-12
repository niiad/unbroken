package com.niiad.unbroken.application.useCases;

import com.niiad.unbroken.domain.entities.local.Supply;
import org.springframework.stereotype.Component;

@Component
public interface SupplyUseCase {
    Supply createSupply(Supply supply);
}
