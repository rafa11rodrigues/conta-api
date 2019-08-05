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

import conta.api.model.Agencia;
import conta.api.model.Cidade;
import conta.api.model.Cliente;
import conta.api.model.Conta;
import conta.api.model.ContaTipo;
import conta.api.model.Estado;
import conta.api.model.Historico;
import conta.api.model.HistoricoTipo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContaRepositoryTest {

	private Cidade cid1;
	private Cliente cli1, cli2;
	private Agencia a1, a2;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private AgenciaRepository agenciaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	
	@Before
	public void setup() {
		cid1 = cidadeRepository.save(new Cidade("Maringá", Estado.PR));
		a1 = agenciaRepository.save(new Agencia("1234", "1", cid1));
		a2 = agenciaRepository.save(new Agencia("7012", "2", cid1));
		cli1 = clienteRepository.save(new Cliente("João", "123.456.789-00"));
		cli2 = clienteRepository.save(new Cliente("Maria", "007.770.777-21"));
	}
	
	@After
	public void clean() {
		contaRepository.deleteAll();
		clienteRepository.deleteAll();
		agenciaRepository.deleteAll();
		cidadeRepository.deleteAll();
	}
	
	@Test
	public void criarConta() {
		Conta c = new Conta("12", a1, cli1, ContaTipo.CORRENTE);
		Conta s = contaRepository.save(c);
		
		Assert.assertNotNull(s.getId());
		Assert.assertEquals(c.getNumero(), s.getNumero());
	}
	
	@Test
	public void buscarContaPorAgencia() {
		contaRepository.save(new Conta("12", a1, cli1, ContaTipo.CORRENTE));
		contaRepository.save(new Conta("023", a1, cli1, ContaTipo.POUPANCA));
		contaRepository.save(new Conta("32", a1, cli2, ContaTipo.CORRENTE));
		contaRepository.save(new Conta("753", a2, cli1, ContaTipo.CORRENTE));
		contaRepository.save(new Conta("215", a2, cli2, ContaTipo.POUPANCA));
		
		List<Conta> c1 = contaRepository.findByAgencia(a1);
		List<Conta> c2 = contaRepository.findByAgencia(a2);
		
		Assert.assertEquals(3, c1.size());
		Assert.assertEquals(2, c2.size());
	}
	
	@Test
	public void verificarHistoricoDeDepositoESaque() {
		Conta c = contaRepository.save(new Conta("12", a1, cli1, ContaTipo.CORRENTE));
		c.depositar(750.00);
		c = contaRepository.save(c);
		c.sacar(250.00);
		c = contaRepository.save(c);
		
		List<Historico> h = c.getHistorico();
		
		Assert.assertEquals(2, h.size());
		
		Historico h0 = h.get(0);
		Historico h1 = h.get(1);
		
		Assert.assertEquals(750.00, h0.getValor(), 2);
		Assert.assertEquals(750.00, h0.getValorResultante(), 2);
		Assert.assertEquals(HistoricoTipo.ENTRADA, h0.getTipo());
		
		Assert.assertEquals(250.00, h1.getValor(), 2);
		Assert.assertEquals(500.00, h1.getValorResultante(), 2);
		Assert.assertEquals(HistoricoTipo.SAIDA, h1.getTipo());
	}
}
