package com.niiad.unbroken.application.useCases;

import com.niiad.unbroken.domain.entities.local.TaxRecord;
import org.springframework.stereotype.Component;

@Component
public interface TaxRecordUseCase {
    TaxRecord fileTaxRecord(TaxRecord taxRecord);
}