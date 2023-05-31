package de.neuefische.backend.service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@NoArgsConstructor
public class ShuffleService {
    public List<String> shuffleList(List<String> list) {
        Collections.shuffle(list);
        return list;
    }
}
