package conta.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import conta.api.verifier.Verifier;
import lombok.Data;

@Entity
@Table(name = "conta")
@Data
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "numero", nullable = false)
	private String numero;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "agencia_id", referencedColumnName = "id")
	private Agencia agencia;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_id", referencedColumnName = "id")
	private Cliente cliente;
	
	@Column(name = "saldo", nullable = false)
	private Double saldo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private ContaTipo tipo;
	
	@ElementCollection
	@CollectionTable(name = "historico", joinColumns = {@JoinColumn(name = "conta_id")})
	private List<Historico> historico;
	
	public void depositar(Double valor) {
		Verifier.valorPositivo(valor, "valor do depósito deve ser positivo");
		this.saldo += valor;
	}
	
	public void sacar(Double valor) {
		Verifier.valorPositivo(valor, "valor do saque deve ser positivo");
		this.saldo -= valor;
	}
}
