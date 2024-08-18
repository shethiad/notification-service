package com.ds.notificationservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer
{
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);


    @KafkaListener(topics = "events-topic", groupId = "group_id")
    public void consume(String message) throws IOException
    {

        ObjectMapper mapper  = new ObjectMapper();

        Event data =  mapper.readValue(message, Event.class);

        logger.info(data.getType() + " ===> " + data.getMessage());
    }
}

