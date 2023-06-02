package de.neuefische.backend.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UUIDService {
    public String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
