package conta.api.service;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import conta.api.domain.dto.CidadePersistDto;
import conta.api.domain.dto.CidadeResponseDto;
import conta.api.domain.entity.Cidade;
import conta.api.domain.entity.Estado;
import conta.api.repository.CidadeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CidadeServiceTest {
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@After
	public void clean() {
		cidadeRepository.deleteAll();
	}

	@Test
	public void criarCidade() {
		CidadePersistDto dto = new CidadePersistDto();
		dto.setNome("Maringá");
		dto.setUf(Estado.PR);
		CidadeResponseDto response = cidadeService.create(dto);
		
		Assert.assertNotNull(response.getId());
	}
	
	@Test
	public void alterarCidade() {
		CidadePersistDto dto = new CidadePersistDto();
		dto.setNome("Maringá");
		dto.setUf(Estado.PR);
		CidadeResponseDto response = cidadeService.create(dto);
		
		Long id = response.getId();
		dto.setNome("Gramado");
		dto.setUf(Estado.RS);
		
		response = cidadeService.update(dto, id);
		
		Assert.assertEquals(id, response.getId());
		Assert.assertEquals("Gramado", response.getNome());
		Assert.assertEquals(Estado.RS, response.getUf());
	}
	
	@Test
	public void deletarCidade() {
		CidadePersistDto dto = new CidadePersistDto();
		dto.setNome("Maringá");
		dto.setUf(Estado.PR);
		CidadeResponseDto response = cidadeService.create(dto);
		
		Long id = response.getId();
		cidadeService.delete(id);
		
		List<Cidade> cidades = cidadeService.findAll();
		
		Assert.assertEquals(0, cidades.size());
	}
	
	@Test
	public void buscarCidadePorNome() {
		CidadePersistDto dto = new CidadePersistDto();
		dto.setNome("Maringá");
		dto.setUf(Estado.PR);
		CidadeResponseDto response = cidadeService.create(dto);
		
		Cidade c = cidadeService.findByNome("Maringá");
		
		Assert.assertNotNull(c);
	}
	
	@Test
	public void buscarPorId() {
		CidadePersistDto dto = new CidadePersistDto();
		dto.setNome("Maringá");
		dto.setUf(Estado.PR);
		CidadeResponseDto response = cidadeService.create(dto);
		
		Long id = response.getId();
		
		Cidade c = cidadeService.findById(id);
		
		Assert.assertNotNull(c);
	}
}
