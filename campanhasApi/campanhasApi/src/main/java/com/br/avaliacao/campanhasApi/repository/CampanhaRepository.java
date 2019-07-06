/**
 * 
 */
package com.br.avaliacao.campanhasApi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.avaliacao.campanhasApi.model.Campanha;

/**
 * @author Vinicius
 *
 */
public interface CampanhaRepository extends JpaRepository<Campanha, Integer> {

	public List<Campanha> findByDataFimVigenciaAfter(LocalDate dataAtual);

	public List<Campanha> findByDataFimVigencia(LocalDate dataFim);
	
	public List<Campanha> findByDataInicioVigenciaBetween(LocalDate dataIni, LocalDate dataFim);


	
	

}
