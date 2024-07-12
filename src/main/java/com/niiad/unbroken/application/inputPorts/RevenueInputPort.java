package com.niiad.unbroken.application.inputPorts;

import com.niiad.unbroken.application.outputPorts.RevenueOutputPort;
import com.niiad.unbroken.application.useCases.RevenueUseCase;
import com.niiad.unbroken.domain.entities.local.Revenue;
import org.springframework.stereotype.Component;

@Component
public class RevenueInputPort implements RevenueUseCase {
    private final RevenueOutputPort revenueOutputPort;

    public RevenueInputPort(RevenueOutputPort revenueOutputPort) {
        this.revenueOutputPort = revenueOutputPort;
    }

    @Override
    public Revenue generateRevenue(Revenue revenue) {
        if (persistRevenue(revenue)) {
            return revenue;
        } else {
            return null;
        }
    }

    private boolean persistRevenue(Revenue revenue) {
        return revenueOutputPort.persistRevenue(revenue);
    }

}