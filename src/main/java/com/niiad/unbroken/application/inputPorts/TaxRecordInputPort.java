package com.niiad.unbroken.application.inputPorts;

import com.niiad.unbroken.application.outputPorts.TaxRecordOutputPort;
import com.niiad.unbroken.application.useCases.TaxRecordUseCase;
import com.niiad.unbroken.domain.entities.local.TaxRecord;
import org.springframework.stereotype.Component;

@Component
public class TaxRecordInputPort implements TaxRecordUseCase {
    private final TaxRecordOutputPort taxRecordOutputPort;

    public TaxRecordInputPort(TaxRecordOutputPort taxRecordOutputPort) {
        this.taxRecordOutputPort = taxRecordOutputPort;
    }

    @Override
    public TaxRecord fileTaxRecord(TaxRecord taxRecord) {
        if (persistTaxRecord(taxRecord)) {
            return taxRecord;
        } else {
            return null;
        }
    }

    private boolean persistTaxRecord(TaxRecord taxRecord) {
        return taxRecordOutputPort.persistTaxRecord(taxRecord);
    }
}
