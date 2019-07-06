/**
 * 
 */
package com.br.avaliacao.campanhasApi.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.avaliacao.campanhasApi.model.Campanha;
import com.br.avaliacao.campanhasApi.repository.CampanhaRepository;

/**
 * @author Vinicius
 *
 */

@Service
public class CampanhaService {
	
	@Autowired
	public CampanhaRepository campanhas;

	public Campanha salvar(Campanha campanha){
		List<Campanha> campanhaValidacao = campanhas.findByDataInicioVigenciaBetween(campanha.getDataInicioVigencia(),campanha.getDataFimVigencia());
		
		if(!campanhaValidacao.isEmpty() && campanhaValidacao != null)
			campanhaValidacao.iterator().forEachRemaining(i-> regraAdicaoCampanha(i));
		
		return campanhas.save(campanha);		
	}
	
	public Campanha atualizar(Campanha campanha, int id){
		Campanha existente = campanhas.getOne(id);
		BeanUtils.copyProperties(campanha, existente, "id");
		existente = campanhas.save(existente);

		return existente;
	}
	
	public void regraAdicaoCampanha(Campanha campanha){
		campanha.setDataFimVigencia(campanha.getDataFimVigencia().plusDays(1));
		List<Campanha> campanhasConcorrentes =  campanhas.findByDataFimVigencia(campanha.getDataFimVigencia());
		if(!campanhasConcorrentes.isEmpty()){
			campanha.setDataFimVigencia(campanha.getDataFimVigencia().plusDays(1));
			campanhas.save(campanha);
		}
		else{
			campanhas.save(campanha);
		}	
	}
}
