package com.niiad.unbroken.application.outputPorts;

import com.niiad.unbroken.domain.entities.local.Revenue;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RevenueOutputPort {
    Revenue fetchRevenueById(Long revenueId);

    boolean persistRevenue(Revenue revenue);

    List<Revenue> fetchAllRevenues();
}
