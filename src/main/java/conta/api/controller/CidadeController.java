package conta.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import conta.api.domain.dto.CidadePersistDto;
import conta.api.domain.dto.CidadeResponseDto;
import conta.api.domain.entity.Cidade;
import conta.api.service.CidadeService;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping("/all")
	public ResponseEntity<?> listarCidades() {
		List<Cidade> cidades = cidadeService.findAll();
		return ResponseEntity.ok(cidades);
	}
	
	@GetMapping
	public ResponseEntity<?> buscarPorNome(@RequestParam("nome") String nome) {
		Cidade c = cidadeService.findByNome(nome);
		return ResponseEntity.ok(c);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		Cidade c = cidadeService.findById(id);
		return ResponseEntity.ok(c);
	}
	
	@PostMapping
	public ResponseEntity<?> cadastrarCidade(CidadePersistDto dto) {
		CidadeResponseDto r = cidadeService.create(dto);
		return ResponseEntity.created(null).body(r);
	}
}
