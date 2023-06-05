package de.neuefische.backend.service;


import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDService {
    public String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
