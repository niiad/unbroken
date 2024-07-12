package com.niiad.unbroken.application.inputPorts;

import com.niiad.unbroken.application.outputPorts.SupplyOutputPort;
import com.niiad.unbroken.application.useCases.SupplyUseCase;
import com.niiad.unbroken.domain.entities.local.Supply;
import org.springframework.stereotype.Component;

@Component
public class SupplyInputPort implements SupplyUseCase {
    private final SupplyOutputPort supplyOutputPort;

    public SupplyInputPort(SupplyOutputPort supplyOutputPort) {
        this.supplyOutputPort = supplyOutputPort;
    }

    @Override
    public Supply createSupply(Supply supply) {
        if (persistSupply(supply)) {
            return supply;
        } else {
            return null;
        }
    }

    private boolean persistSupply(Supply supply) {
        return supplyOutputPort.persistSupply(supply);
    }
}