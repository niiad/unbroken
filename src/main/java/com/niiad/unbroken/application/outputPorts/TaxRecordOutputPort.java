package com.niiad.unbroken.application.outputPorts;

import com.niiad.unbroken.domain.entities.local.TaxRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TaxRecordOutputPort {
    TaxRecord fetchTaxRecordById(Integer taxRecordId);

    boolean persistTaxRecord(TaxRecord taxRecord);

    List<TaxRecord> fetchAllTaxRecords();
}