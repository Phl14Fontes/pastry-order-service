package com.mba.orderservice.application.service;

import com.mba.orderservice.application.port.in.RetrieveMenuUseCase;
import com.mba.orderservice.domain.enums.Flavor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService implements RetrieveMenuUseCase {

    @Override
    public List<Flavor> getMenu() {
        return Arrays.stream(Flavor.values()).toList();
    }
}
