package de.neuefische.backend.service;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@NoArgsConstructor
@Service
public class ServiceGenerateID {
    String id;

    public String generateID(){
        return UUID.randomUUID().toString();
    }
}
