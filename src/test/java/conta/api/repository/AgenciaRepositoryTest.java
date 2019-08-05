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

import conta.api.domain.entity.Agencia;
import conta.api.domain.entity.Cidade;
import conta.api.domain.entity.Estado;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AgenciaRepositoryTest {

	@Autowired
	private AgenciaRepository agenciaRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	
	private Cidade buscarCidade(String nome) {
		return cidadeRepository.findByNome(nome).orElse(null);
	}
	
	@Before
	public void cadastrarCidades() {
		cidadeRepository.save(new Cidade("Maringá", Estado.PR));
		cidadeRepository.save(new Cidade("Curitiba", Estado.PR));
		cidadeRepository.save(new Cidade("São Paulo", Estado.SP));
		cidadeRepository.save(new Cidade("Campinas", Estado.SP));
		cidadeRepository.save(new Cidade("Porto Alegre", Estado.RS));
	}
	
	@After
	public void cleanTest() {
		agenciaRepository.deleteAll();
		cidadeRepository.deleteAll();
	}
	
	@Test
	public void cadastrarAgencia() {
		Cidade c = buscarCidade("Maringá");
		Agencia a = new Agencia("1234", "1", c);
		
		Agencia salva = agenciaRepository.save(a);
		
		Assert.assertNotNull(salva.getId());
		Assert.assertEquals(a.getNumero(), salva.getNumero());
		Assert.assertEquals(a.getCidade().getId(), salva.getCidade().getId());
	}
	
	@Test
	public void buscarAgenciaPorCidade() {
		Cidade c1 = buscarCidade("Maringá");
		Cidade c2 = buscarCidade("São Paulo");
		Cidade c3 = buscarCidade("Curitiba");
		
		agenciaRepository.save(new Agencia("1234", "1", c1));
		agenciaRepository.save(new Agencia("9876", "2", c1));
		agenciaRepository.save(new Agencia("0007", "3", c2));
		
		List<Agencia> a1 = agenciaRepository.findByCidade(c1);
		List<Agencia> a2 = agenciaRepository.findByCidade(c2);
		List<Agencia> a3 = agenciaRepository.findByCidade(c3);
		
		Assert.assertEquals(2, a1.size());
		Assert.assertEquals(1, a2.size());
		Assert.assertEquals(0, a3.size());
	}
	
	@Test
	public void buscarAgenciaPorEstado() {
		Cidade c1 = buscarCidade("Maringá");
		Cidade c2 = buscarCidade("São Paulo");
		Cidade c3 = buscarCidade("Curitiba");
		
		agenciaRepository.save(new Agencia("1234", "1", c1));
		agenciaRepository.save(new Agencia("9876", "2", c1));
		agenciaRepository.save(new Agencia("0007", "3", c2));
		agenciaRepository.save(new Agencia("5523", "4", c3));
		
		List<Agencia> a1 = agenciaRepository.findByUf(c1.getUf());
		List<Agencia> a2 = agenciaRepository.findByUf(c2.getUf());
		List<Agencia> a3 = agenciaRepository.findByUf(c3.getUf());
		
		Assert.assertEquals(3, a1.size());
		Assert.assertEquals(1, a2.size());
		Assert.assertEquals(3, a3.size());
	}
}
