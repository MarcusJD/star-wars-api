package sw.com.api.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import sw.com.api.model.Planeta;
import sw.com.api.service.PlanetaService;

@RestController
@RequestMapping("/api")
public class PlanetaController
{
	
	@Autowired
	PlanetaService planetaService;
	
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ResponseEntity<Planeta> adicionarPlaneta( @RequestBody Planeta planeta )
	{
	    return planetaService.adicionarPlaneta( planeta );
	}
	
	@GetMapping
	public ResponseEntity<List<Planeta>> listarPlanetas( )
	{
		return planetaService.listarPlanetas( );
	}
	
	@GetMapping( "/{id}" )
	public ResponseEntity<Planeta> listarPorId( @PathVariable BigInteger id )
	{
		return planetaService.listarPlanetaPorId( id );
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> removerPlaneta( @PathVariable BigInteger id )
	{
		return planetaService.removerPlaneta( id );
	}
}
