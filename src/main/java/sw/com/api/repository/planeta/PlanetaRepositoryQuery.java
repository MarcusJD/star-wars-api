package sw.com.api.repository.planeta;

import java.util.List;

import sw.com.api.model.Planeta;;

public interface PlanetaRepositoryQuery
{
	public List<Planeta> findPlanetaByNomeLike( String nome );
}
