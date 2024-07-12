package com.niiad.unbroken.application.outputPorts;

import com.niiad.unbroken.domain.entities.local.Expenditure;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExpenditureOutputPort {
    Expenditure fetchExpenditureById(Long expenditureId);

    boolean persistExpenditure(Expenditure expenditure);

    List<Expenditure> fetchAllExpenditures();
}
