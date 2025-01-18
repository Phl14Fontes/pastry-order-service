package com.mba.orderservice.application.dto.response;

import com.mba.orderservice.application.dto.AvailableFriedPastryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RetrieveMenuResponseDto {
    List<AvailableFriedPastryDto> availableMenu;
}
