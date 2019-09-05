package sw.com.api.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;


import sw.com.api.model.Planeta;
import sw.com.api.repository.planeta.PlanetaRepositoryQuery;

public interface PlanetaRepository extends MongoRepository<Planeta, BigInteger>,PlanetaRepositoryQuery{
	
}
