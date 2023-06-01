package de.neuefische.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShuffleService {
    public List<String> shuffleList(List<String> list) {
        List<String> shuffledList = list;
        Collections.shuffle(shuffledList);
        return shuffledList;
    }
}
