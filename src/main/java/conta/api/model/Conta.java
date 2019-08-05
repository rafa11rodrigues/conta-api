package conta.api.model;

import java.util.ArrayList;
import java.util.List;

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

import org.springframework.util.Assert;

import lombok.Getter;

@Entity
@Table(name = "conta")
@Getter
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "numero", nullable = false)
	private String numero;
	
	@ManyToOne
	@JoinColumn(name = "agencia_id")
	private Agencia agencia;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private ContaTipo tipo;
	
	@Column(name = "saldo", nullable = false)
	private Double saldo;
	
	@ElementCollection
	@CollectionTable(name = "historico", joinColumns = {@JoinColumn(name = "conta_id")})
	private List<Historico> historico;
	
	public Conta() {
		this.saldo = 0.0;
		this.historico = new ArrayList<>();
	}
	
	public Conta(String numero, Agencia agencia, Cliente cliente, ContaTipo tipo) {
		this();
		Assert.hasText(numero, "número da conta não pode ser nulo nem vazio");
		Assert.notNull(agencia, "agência não pode ser nula");
		Assert.notNull(cliente, "cliente não pode ser nulo");
		Assert.notNull(tipo, "tipo não pode ser nulo");
		
		this.numero = numero;
		this.agencia = agencia;
		this.cliente = cliente;
		this.tipo = tipo;
	}
	

	public void depositar(Double valor) {
		Assert.isTrue(valor > 0, "valor do depósito deve ser positivo");
		this.saldo += valor;
		this.historico.add(new Historico(valor, saldo, HistoricoTipo.ENTRADA));
	}
	
	public void sacar(Double valor) {
		Assert.isTrue(valor > 0, "valor do depósito deve ser positivo");
		this.saldo -= valor;
		this.historico.add(new Historico(valor, saldo, HistoricoTipo.SAIDA));
	}
}
