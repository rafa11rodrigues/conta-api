package conta.api.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AgenciaTest {

	private Cidade cidade;
	
	@Before
	public void criarCidade() {
		cidade = new Cidade("Maringá", Estado.PR);
	}
	
	@Test
	public void criarAgenciaComSucesso() {
		String message = null;
		Agencia agencia = null;
		
		try {
			agencia = new Agencia("1234", "1", cidade);
		}
		catch (Exception e) {
			message = e.getMessage();
		}
		
		Assert.assertNotNull(agencia);
		Assert.assertNull(message);
	}
	
	@Test
	public void testarRestricoesDeNumeroDaAgencia() {
		String message = null;
		Agencia agencia = null;
		
		try {
			agencia = new Agencia("", "1", cidade);
		}
		catch (Exception e) {
			message = e.getMessage();
		}
		
		Assert.assertNull(agencia);
		Assert.assertNotNull(message);
		Assert.assertEquals("número não pode ser nulo nem vazio", message);
		
		try {
			agencia = new Agencia(null, "1", cidade);
		}
		catch (Exception e) {
			message = e.getMessage();
		}
		
		Assert.assertNull(agencia);
		Assert.assertNotNull(message);
		Assert.assertEquals("número não pode ser nulo nem vazio", message);
	}
	
	@Test
	public void testarRestricoesDeDigitorDaAgencia() {
		String message = null;
		Agencia agencia = null;
		
		try {
			agencia = new Agencia("1234", "", cidade);
		}
		catch (Exception e) {
			message = e.getMessage();
		}
		
		Assert.assertNull(agencia);
		Assert.assertNotNull(message);
		Assert.assertEquals("dígito não pode ser nulo nem vazio", message);
		
		try {
			agencia = new Agencia("1234", null, cidade);
		}
		catch (Exception e) {
			message = e.getMessage();
		}
		
		Assert.assertNull(agencia);
		Assert.assertNotNull(message);
		Assert.assertEquals("dígito não pode ser nulo nem vazio", message);
	}
	
	@Test
	public void testarRestricoesDeCidadeDaAgencia() {
		String message = null;
		Agencia agencia = null;
		
		try {
			agencia = new Agencia("1234", "1", null);
		}
		catch(Exception e) {
			message = e.getMessage();
		}
		
		Assert.assertNull(agencia);
		Assert.assertNotNull(message);
		Assert.assertEquals("a agência precisa de uma cidade", message);
	}
}
