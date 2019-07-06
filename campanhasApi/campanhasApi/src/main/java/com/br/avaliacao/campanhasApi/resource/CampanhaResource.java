package com.br.avaliacao.campanhasApi.resource;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.avaliacao.campanhasApi.model.Campanha;
import com.br.avaliacao.campanhasApi.repository.CampanhaRepository;
import com.br.avaliacao.campanhasApi.service.CampanhaService;

@RestController
@RequestMapping("/campanhas")
public class CampanhaResource {

	@Autowired
	private CampanhaRepository campanhas;

	@Autowired
	private CampanhaService campanhaSerivice;

	@PostMapping
	public Campanha adicionar(@Valid @RequestBody Campanha campanha) {
		return campanhaSerivice.salvar(campanha);
	}

	@GetMapping
	public List<Campanha> buscarTodos() {
		System.out.println();
		return campanhas.findByDataFimVigenciaAfter(LocalDate.now());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Campanha> buscarPorId(@PathVariable int id) {
		Campanha campanha = campanhas.getOne(id);

		return campanha == null ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(campanha);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Campanha> atualizar(@PathVariable int id,
			@Valid @RequestBody Campanha campanha) {
		return ResponseEntity.ok(campanhaSerivice.atualizar(campanha, id));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable int id) {
		Campanha campanha = campanhas.getOne(id);

		if (campanha == null) {
			return ResponseEntity.notFound().build();
		}

		campanhas.delete(campanha);

		return ResponseEntity.noContent().build();
	}

}
