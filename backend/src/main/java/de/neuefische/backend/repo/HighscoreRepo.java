package de.neuefische.backend.repo;

import de.neuefische.backend.model.Result;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HighscoreRepo extends MongoRepository<Result, String> {
}
