package com.mba.orderservice.application.port.out;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mba.orderservice.application.dto.DoneOrderEventDto;

public interface MessageProducer {
    void sendEvent(DoneOrderEventDto eventSchemaDto) throws JsonProcessingException;
}
