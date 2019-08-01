package conta.api.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Embeddable
@Data
public class Historico {

	@Column(name = "data")
	private LocalDateTime data;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Conta conta;
	
	@Column(name = "valor", nullable = false)
	private Double valor;
	
	@Column(name = "valor_resultante", nullable = false)
	private Double valorResultante;
	
	@Enumerated(EnumType.STRING)
	private HistoricoTipo tipo;
}
