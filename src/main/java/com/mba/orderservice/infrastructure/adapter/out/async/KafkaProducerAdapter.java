package com.mba.orderservice.infrastructure.adapter.out.async;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mba.orderservice.application.dto.DoneOrderEventDto;
import com.mba.orderservice.application.port.out.MessageProducer;
import io.swagger.v3.core.util.Json;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducerAdapter implements MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    @Value("${spring.kafka.properties.template.default-topic}")
    private String ordersTopic;

    @Override
    public void sendEvent(DoneOrderEventDto eventSchemaDto) throws JsonProcessingException {
        sendMessage(eventSchemaDto);
    }

    public void sendMessage(DoneOrderEventDto dto) throws JsonProcessingException {
        String orderAsMessage = objectMapper.writeValueAsString(dto);
        kafkaTemplate.send(ordersTopic, orderAsMessage);
    }
}
