package com.mba.orderservice.application.dto.response;

import com.mba.orderservice.domain.enums.Flavor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RetrieveMenuResponseDto {
    List<Flavor> availableMenu;
}
