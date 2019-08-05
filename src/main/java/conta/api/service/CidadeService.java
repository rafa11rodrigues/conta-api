package conta.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import conta.api.adapter.CidadeAdapter;
import conta.api.domain.dto.CidadePersistDto;
import conta.api.domain.dto.CidadeResponseDto;
import conta.api.domain.entity.Cidade;
import conta.api.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	public CidadeResponseDto create(CidadePersistDto dto) {
		Cidade cidade = new Cidade(dto.getNome(), dto.getUf());
		cidade = cidadeRepository.save(cidade);

		return CidadeAdapter.entityToResponse(cidade);
	}
	
	public CidadeResponseDto update(CidadePersistDto dto, Long id) {
		Cidade cidade = cidadeRepository.findById(id).orElseThrow(() -> new RuntimeException("cidade com id " + id + "não existe"));
		cidade.alterar(dto.getNome(), dto.getUf());
		cidade = cidadeRepository.save(cidade);
		
		return CidadeAdapter.entityToResponse(cidade);
	}
	
	public void delete(Long id) {
		Cidade cidade = cidadeRepository.findById(id).orElseThrow(() -> new RuntimeException("cidade com id " + id + "não existe"));
		cidadeRepository.delete(cidade);
	}
	
	public List<Cidade> findAll() {
		return cidadeRepository.findAll();
	}
	
	public Cidade findById(Long id) {
		return cidadeRepository.findById(id).orElseThrow(() -> new RuntimeException("cidade com id " + id + "não existe"));
	}
	
	public Cidade findByNome(String nome) {
		return cidadeRepository.findByNome(nome).orElseThrow(() -> new RuntimeException("cidade com nome " + nome + " não encontrada"));
	}
}
