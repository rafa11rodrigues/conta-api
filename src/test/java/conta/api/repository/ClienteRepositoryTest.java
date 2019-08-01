package conta.api.repository;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import conta.api.model.Cidade;
import conta.api.model.Cliente;
import conta.api.model.Endereco;
import conta.api.model.Estado;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteRepositoryTest {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@Before
	public void cadastrarEnderecos() {
		Cidade c1 = new Cidade("Maringá", Estado.PR);
		Cidade c2 = new Cidade("Curitiba", Estado.PR);
		Cidade c3 = new Cidade("Gramado", Estado.RS);
		
		c1 = cidadeRepository.save(c1);
		c2 = cidadeRepository.save(c2);
		c3 = cidadeRepository.save(c3);
		
		enderecoRepository.save(new Endereco("Avenida 1", "532", "Centro", "12345-678", c1));
		enderecoRepository.save(new Endereco("Rua 10", "Vila 0", "00321-000", c2));
		enderecoRepository.save(new Endereco("Rua das Palmeiras", "70", "bloco 2", "Jardim Legal", "24816-222", c3));
		enderecoRepository.save(new Endereco("Avenida dos Pomares", "25", "Jardim Frutífero", "22222-222", c3));
	}
	
	@After
	public void cleanTest() {
		clienteRepository.deleteAll();
		enderecoRepository.deleteAll();
		cidadeRepository.deleteAll();
	}
	
	@Test
	public void cadastrarCliente() {
		Cidade c3 = cidadeRepository.findByNome("Gramado").orElse(null);
		
		Endereco e1 = enderecoRepository.findByCep("12345-678").get(0);
		Endereco e2 = enderecoRepository.findByCep("24816-222").get(0);
		List<Endereco> e3 = enderecoRepository.findByCidade(c3);
		
		List<Endereco> l1 = new ArrayList<>();
		l1.add(e1);
		
		List<Endereco> l2 = new ArrayList<>();
		l2.add(e2);
		
		Cliente p1 = new Cliente("João", "012.345.678-90", l1);
		Cliente p2 = new Cliente("Maria", "234.012.711-00", l2);
		Cliente p3 = new Cliente("Josefa", "111.111.111-11", e3);
		Cliente p4 =new Cliente("Fulano", "000.000.000-00");
		
		Cliente salvo1 = clienteRepository.save(p1);
		Cliente salvo2 = clienteRepository.save(p2);
		Cliente salvo3 = clienteRepository.save(p3);
		Cliente salvo4 = clienteRepository.save(p4);
		
		Assert.assertNotNull(salvo1.getId());
		Assert.assertNotNull(salvo2.getId());
		Assert.assertNotNull(salvo3.getId());
		Assert.assertNotNull(salvo4.getId());
	}
	
	@Test
	public void buscarClientePorEndereco() {
		Endereco e1 = enderecoRepository.findByCep("12345-678").get(0);
		Endereco e2 = enderecoRepository.findByCep("24816-222").get(0);
		
		List<Endereco> l1 = new ArrayList<>();
		l1.add(e1);
		
		clienteRepository.save(new Cliente("João", "012.345.678-90", l1));
		clienteRepository.save(new Cliente("Maria", "234.012.711-00", l1));
		
		List<Cliente> c1 = clienteRepository.findByEndereco(e1);
		List<Cliente> c2 = clienteRepository.findByEndereco(e2);
		
		Assert.assertEquals(2, c1.size());
		Assert.assertEquals(0, c2.size());
	}
}
