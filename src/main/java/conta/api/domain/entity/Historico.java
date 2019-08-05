package conta.api.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;

@Embeddable
@Getter
public class Historico {

	@Column(name = "data")
	private LocalDateTime data;
	
	@Column(name = "valor", nullable = false)
	private Double valor;
	
	@Column(name = "valor_resultante", nullable = false)
	private Double valorResultante;
	
	@Enumerated(EnumType.STRING)
	private HistoricoTipo tipo;
	
	
	public Historico() {}

	public Historico(Double valor, Double valorResultante, HistoricoTipo tipo) {
		this.data = LocalDateTime.now();
		this.valor = valor;
		this.valorResultante = valorResultante;
		this.tipo = tipo;
	}
}
