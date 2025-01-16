package com.mba.orderservice.infrastructure.adapter.in.rest;

import com.mba.orderservice.application.dto.response.RetrieveMenuResponseDto;
import com.mba.orderservice.application.port.in.RetrieveMenuUseCase;
import com.mba.orderservice.domain.enums.Flavor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final RetrieveMenuUseCase useCase;

    @GetMapping
    public ResponseEntity<RetrieveMenuResponseDto> getMenu() {
        List<Flavor> flavors = useCase.getMenu();

        return ResponseEntity.status(HttpStatus.OK.value()).body(new RetrieveMenuResponseDto(flavors));
    }

}
