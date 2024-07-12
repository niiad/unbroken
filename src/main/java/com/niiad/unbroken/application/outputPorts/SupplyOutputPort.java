package com.niiad.unbroken.application.outputPorts;

import com.niiad.unbroken.domain.entities.local.Supply;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SupplyOutputPort {
    boolean persistSupply(Supply supply);

    List<Supply> fetchAllSupplies();
}
