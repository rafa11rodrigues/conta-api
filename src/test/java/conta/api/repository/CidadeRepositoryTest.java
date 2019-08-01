package conta.api.repository;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import conta.api.model.Cidade;
import conta.api.model.Estado;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CidadeRepositoryTest {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	
	@After
	public void cleanTest() {
		cidadeRepository.deleteAll();
	}
	
	@Test
	public void cadastrarNovaCidade() {
		Cidade cidade = new Cidade("Maringá", Estado.PR);
		Cidade cidadeSalva = cidadeRepository.save(cidade);
		
		Assert.assertNotNull(cidadeSalva.getId());
		Assert.assertEquals(cidade.getNome(), cidadeSalva.getNome());
		Assert.assertEquals(cidade.getEstado(), cidadeSalva.getEstado());
	}
	
	@Test
	public void buscarCidadePorNome() {
		Cidade cidade = new Cidade("Maringá", Estado.PR);
		cidadeRepository.save(cidade);
		
		Cidade cidadeBuscada = cidadeRepository.findByNome("Maringá").orElse(null);
		
		Assert.assertNotNull(cidadeBuscada);
		Assert.assertEquals(cidade.getNome(), cidadeBuscada.getNome());
		Assert.assertEquals(cidade.getEstado(), cidadeBuscada.getEstado());
	}
	
	@Test
	public void buscarCidadePorEstado() {
		cidadeRepository.save(new Cidade("Maringá", Estado.PR));
		cidadeRepository.save(new Cidade("Curitiba", Estado.PR));
		cidadeRepository.save(new Cidade("São Paulo", Estado.SP));
		
		List<Cidade> cidadesPR = cidadeRepository.findByEstado(Estado.PR);
		List<Cidade> cidadesSP = cidadeRepository.findByEstado(Estado.SP);
		List<Cidade> cidadesRS = cidadeRepository.findByEstado(Estado.RS);
		
		Assert.assertEquals(2, cidadesPR.size());
		Assert.assertEquals(1, cidadesSP.size());
		Assert.assertEquals(0, cidadesRS.size());
	}
	
	/*@Test
	public void deveViolarUniqueConstraint() {
		Cidade c1 = new Cidade("Maringá", Estado.PR);
		Cidade c2 = new Cidade("Maringá", Estado.PR);
		
		Cidade s1 = cidadeRepository.save(c1);
		Cidade s2 = cidadeRepository.save(c2);
		
		Assert.assertNotNull(s1);
		Assert.assertNull(s2);
	}*/
}
