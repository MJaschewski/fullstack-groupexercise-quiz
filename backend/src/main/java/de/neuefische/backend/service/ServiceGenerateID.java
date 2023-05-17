package de.neuefische.backend.service;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ServiceGenerateID {
    String id;

    public ServiceGenerateID(){

    }

    public String generateID(){
        return UUID.randomUUID().toString();
    }
}
