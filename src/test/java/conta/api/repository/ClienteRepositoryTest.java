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
import conta.api.model.Cliente;
import conta.api.model.Endereco;
import conta.api.model.EnderecoTipo;
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
	
	Cidade cid1, cid2, cid3;
	
	private void cadastrarCidades() {
		cid1 = cidadeRepository.save(new Cidade("Maringá", Estado.PR));
		cid2 = cidadeRepository.save(new Cidade("Curitiba", Estado.PR));
		cid3 = cidadeRepository.save(new Cidade("Gramado", Estado.RS));
	}
	
	
	
	@After
	public void cleanTest() {
		clienteRepository.deleteAll();
	}
	
	
	@Test
	public void cadastrarCliente() {
		Cliente c1 = new Cliente("João", "012.345.678-90");
		Cliente c2 = new Cliente("Maria", "234.012.711-00");
		
		Cliente salvo1 = clienteRepository.save(c1);
		Cliente salvo2 = clienteRepository.save(c2);
		
		Assert.assertNotNull(salvo1.getId());
		Assert.assertNotNull(salvo2.getId());
	}
	
	public void adicionarEnderecoAoClienteCadastrado() {
		cadastrarCidades();
		Endereco e1 = new Endereco("Avenida 1", "532", "Centro", "12345-678", cid1, EnderecoTipo.RESIDENCIAL);
		Endereco e2 = new Endereco("Rua 10", "Vila 0", "00321-000", cid2, EnderecoTipo.RESIDENCIAL);
		Endereco e3 = new Endereco("Rua das Palmeiras", "70", "bloco 2", "Jardim Legal", "24816-222", cid3, EnderecoTipo.RESIDENCIAL);
		
		Cliente c1 = clienteRepository.save(new Cliente("João", "012.345.678-90"));
		Cliente c2 = clienteRepository.save(new Cliente("Maria", "234.012.711-00"));
		Cliente c3 = clienteRepository.save(new Cliente("Josefa", "111.111.111-11"));
		
		c1.addEnderecos(e1, e2);
		c2.addEnderecos(e3);
		
		c1 = clienteRepository.save(c1);
		c2 = clienteRepository.save(c2);
		
		List<Endereco> ec1 = c1.getEnderecos();
		List<Endereco> ec2 = c2.getEnderecos();
		List<Endereco> ec3 = c3.getEnderecos();
		
		Assert.assertEquals(2, ec1.size());
		Assert.assertEquals(1, ec2.size());
		Assert.assertEquals(0, ec3.size());
		
		Assert.assertNotNull(ec1.get(0).getId());
		Assert.assertNotNull(ec1.get(1).getId());
		Assert.assertNotNull(ec2.get(0).getId());
		
		enderecoRepository.deleteAll();
		cidadeRepository.deleteAll();
	}
	
	@Test
	public void removerEnderecoDoClienteCadastrado() {
		cadastrarCidades();
		Endereco e1 = new Endereco("Avenida 1", "532", "Centro", "12345-678", cid1, EnderecoTipo.RESIDENCIAL);
		Endereco e2 = new Endereco("Rua 10", "Vila 0", "00321-000", cid2, EnderecoTipo.COMERCIAL);
		Endereco e3 = new Endereco("Rua das Palmeiras", "70", "bloco 2", "Jardim Legal", "24816-222", cid3, EnderecoTipo.COBRANCA);
		
		Cliente c1 = clienteRepository.save(new Cliente("João", "012.345.678-90"));
		Cliente c2 = clienteRepository.save(new Cliente("Maria", "234.012.711-00"));
		
		c1.addEnderecos(e1, e2, e3);
		c2.addEnderecos(e1, e2, e3);
		
		c1 = clienteRepository.save(c1);
		c2 = clienteRepository.save(c2);
		
		List<Endereco> ec1 = c1.getEnderecos();
		List<Endereco> ec2 = c2.getEnderecos();
		
		ec1.clear();
		ec2.remove(1);
		
		c1 = clienteRepository.save(c1);
		c2 = clienteRepository.save(c2);
		
		ec1 = c1.getEnderecos();
		ec2 = c2.getEnderecos();
		
		Assert.assertEquals(0, ec1.size());
		Assert.assertEquals(2, ec2.size());
		
		enderecoRepository.deleteAll();
		cidadeRepository.deleteAll();
	}
}
