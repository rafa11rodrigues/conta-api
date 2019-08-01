package conta.api.repository;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import conta.api.model.Cidade;
import conta.api.model.Endereco;
import conta.api.model.Estado;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnderecoRepositoryTest {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	
	@Before
	public void cadastrarCidades() {
		cidadeRepository.save(new Cidade("Maringá", Estado.PR));
		cidadeRepository.save(new Cidade("Curitiba", Estado.PR));
		cidadeRepository.save(new Cidade("Gramado", Estado.RS));
	}
	
	@After
	public void cleanTest() {
		enderecoRepository.deleteAll();
		cidadeRepository.deleteAll();
	}
	
	
	@Test
	public void cadastrarEndereco() {
		Cidade c = cidadeRepository.findByNome("Maringá").orElse(null);
		
		Endereco e = new Endereco("Avenida 1", "532", "Centro", "12345-678", c);
		Endereco salvo = enderecoRepository.save(e);
		
		Assert.assertNotNull(salvo.getId());
		Assert.assertEquals(e.getCep(), salvo.getCep());
	}
	
	@Test
	public void buscarEnderecoPorCep() {
		Cidade c = cidadeRepository.findByNome("Maringá").orElse(null);
		
		enderecoRepository.save(new Endereco("Avenida 1", "532", "Centro", "12345-678", c));
			
		List<Endereco> lista = enderecoRepository.findByCep("12345-678");
		Endereco buscado1 = lista.size() > 0 ? lista.get(0) : null;
		lista = enderecoRepository.findByCep("87001-323");
		Endereco buscado2 = lista.size() > 0 ? lista.get(0) : null;
		
		Assert.assertNotNull(buscado1);
		Assert.assertNull(buscado2);
	}
	
	@Test
	public void buscarEnderecoPorCidade() {
		Cidade c1 = cidadeRepository.findByNome("Maringá").orElse(null);
		Cidade c2 = cidadeRepository.findByNome("Gramado").orElse(null);
		Cidade c3 = cidadeRepository.findByNome("Curitiba").orElse(null);
		
		enderecoRepository.save(new Endereco("Avenida 1", "532", "Centro", "12345-678", c1));
		enderecoRepository.save(new Endereco("Rua 10", "Vila 0", "00321-000", c1));
		enderecoRepository.save(new Endereco("Rua das Palmeiras", "70", "bloco 2", "Jardim Legal", "24816-222", c2));
		
		List<Endereco> b1 = enderecoRepository.findByCidade(c1);
		List<Endereco> b2 = enderecoRepository.findByCidade(c2);
		List<Endereco> b3 = enderecoRepository.findByCidade(c3);
		
		Assert.assertEquals(2, b1.size());
		Assert.assertEquals(1, b2.size());
		Assert.assertEquals(0, b3.size());
	}
}
