package com.niiad.unbroken.application.useCases;

import com.niiad.unbroken.domain.entities.local.Expenditure;
import org.springframework.stereotype.Component;

@Component
public interface ExpenditureUseCase {
    Expenditure makeAnExpenditure(Expenditure expenditure);
}
