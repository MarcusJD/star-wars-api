package sw.com.api.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;

import sw.com.api.model.Planeta;

public interface PlanetaRepository extends MongoRepository<Planeta, BigInteger> {
	
}
