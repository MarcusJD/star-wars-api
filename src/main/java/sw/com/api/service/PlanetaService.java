package sw.com.api.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.StringUtils;


import sw.com.api.model.PlanetAPI;
import sw.com.api.model.Planeta;
import sw.com.api.repository.PlanetaRepository;

@Service
public class PlanetaService
{
	public final String urlAPI = "https://swapi.co/api/planets/";
	
	@Autowired
	PlanetaRepository planetaRepository;
	
	public ResponseEntity<Planeta> adicionarPlaneta( Planeta planeta )
	{
		planetaRepository.save( planeta );
		
		return ResponseEntity.ok( ).body( planeta );
	}
	
	public ResponseEntity<List<Planeta>> listarPlanetas( )
	{
		List<Planeta> planetas = planetaRepository.findAll( );
		
		if( planetas.isEmpty( ) )
			return ResponseEntity.noContent( ).build( );
		
		List<Planeta> planetasAux = new ArrayList<Planeta>( );

		planetas.forEach( planeta-> {
					planeta.setQtdAparicoesFilmes( getPlanetAPIFilms( planeta.getId( ) ) );
					planetasAux.add( planeta );
							} );
		
		return ResponseEntity.ok( ).body( planetasAux );
	}
	
	public ResponseEntity<Planeta> listarPlanetaPorId( BigInteger id )
	{
		Optional<Planeta> planetaOptional = planetaRepository.findById( id );
		
		if( !planetaOptional.isPresent( ) )
			return ResponseEntity.notFound( ).build( );
		
		Planeta planeta = planetaOptional.get( );
		planeta.setQtdAparicoesFilmes( getPlanetAPIFilms( planeta.getId( ) ) );
		
		return ResponseEntity.ok( ).body( planeta );
	}
	
	public ResponseEntity<List<Planeta>> listarPlanetaPorNome( String nome )
	{
		List<Planeta> planetaOptional = planetaRepository.findPlanetaByNomeLike( nome );
		
		if( StringUtils.isEmpty( planetaOptional ) )
			return ResponseEntity.noContent( ).build( );
		
		return ResponseEntity.ok( ).body( planetaOptional );
	}
	
	public ResponseEntity<Object> removerPlaneta( BigInteger id )
	{
		Optional<Planeta> planetaOptional = planetaRepository.findById( id );

		if( !planetaOptional.isPresent( ) )
			throw new EmptyResultDataAccessException( 1 );
		
		planetaRepository.deleteById( id );
		
		return ResponseEntity.noContent( ).build( );
	}
	
	/**
	 * Obtém a quantidade de filmes de determinado planeta pelo ID.
	 * @param idPlaneta
	 * @return
	 */
	private int getPlanetAPIFilms( BigInteger idPlaneta )
	{
		PlanetAPI planetApi = new PlanetAPI( );
		
		try
		{
            RestTemplate restTemplate = new RestTemplate( );
            HttpHeaders       headers = new HttpHeaders ( );
            headers.setAccept( Arrays.asList( MediaType.APPLICATION_JSON ) );
            headers.add( "user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36" );
            HttpEntity<String> entity = new HttpEntity<String>( "parameters", headers );

            planetApi = restTemplate.exchange( urlAPI+idPlaneta, HttpMethod.GET,entity,PlanetAPI.class ).getBody( );
        } 
		catch ( Exception ex )
		{
           ex.printStackTrace( );
        }
		
		return planetApi.getFilms( ).size( );
	}
}
