package sw.com.api.model;


import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document( collection="planetas" )
public class Planeta
{
	
	@Id
	private BigInteger id;
	
	private String nome;
	private String clima;
	private String terreno;
	private Integer qtdAparicoesFilmes;
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getClima() {
		return clima;
	}
	public void setClima(String clima) {
		this.clima = clima;
	}
	public String getTerreno() {
		return terreno;
	}
	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}
	public Integer getQtdAparicoesFilmes() {
		return qtdAparicoesFilmes;
	}
	public void setQtdAparicoesFilmes(Integer qtdAparicoesFilmes) {
		this.qtdAparicoesFilmes = qtdAparicoesFilmes;
	}
}
