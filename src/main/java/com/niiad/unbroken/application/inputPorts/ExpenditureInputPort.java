package com.niiad.unbroken.application.inputPorts;

import com.niiad.unbroken.application.outputPorts.ExpenditureOutputPort;
import com.niiad.unbroken.application.useCases.ExpenditureUseCase;
import com.niiad.unbroken.domain.entities.local.Expenditure;
import org.springframework.stereotype.Component;

@Component
public class ExpenditureInputPort implements ExpenditureUseCase {
    private final ExpenditureOutputPort expenditureOutputPort;

    public ExpenditureInputPort(ExpenditureOutputPort expenditureOutputPort) {
        this.expenditureOutputPort = expenditureOutputPort;
    }

    @Override
    public Expenditure makeAnExpenditure(Expenditure expenditure) {
        if (persistExpenditure(expenditure)) {
            return expenditure;
        } else {
            return null;
        }
    }

    private boolean persistExpenditure(Expenditure expenditure) {
        return expenditureOutputPort.persistExpenditure(expenditure);
    }

}
