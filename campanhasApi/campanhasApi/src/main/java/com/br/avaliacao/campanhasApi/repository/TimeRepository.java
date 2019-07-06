/**
 * 
 */
package com.br.avaliacao.campanhasApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.avaliacao.campanhasApi.model.Time;

/**
 * @author Vinicius
 *
 */
public interface TimeRepository extends JpaRepository<Time,Integer>{

}
