package com.mba.orderservice.application.port.in;

import com.mba.orderservice.domain.enums.Flavor;

import java.util.List;

public interface RetrieveMenuUseCase {
    List<Flavor> getMenu();
}
