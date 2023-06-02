package de.neuefische.backend.repo;

import de.neuefische.backend.model.Result;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface HighscoreRepo extends MongoRepository<Result, String> {
}
