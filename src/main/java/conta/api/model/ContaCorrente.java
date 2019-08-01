package conta.api.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "conta_corrente")
public class ContaCorrente {

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String numero;
	
	private Agencia agencia;
	
	private Cliente cliente;
	
	private Double saldo;
	
	private List<String> historico;
}
