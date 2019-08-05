package conta.api.domain.dto;

import conta.api.domain.entity.Cidade;
import conta.api.domain.entity.Estado;
import lombok.Data;

@Data
public class CidadeResponseDto {

	private Long id;
	private String nome;
	private Estado uf;
	
	
	public CidadeResponseDto(Cidade cidade) {
		this.id = cidade.getId();
		this.nome = cidade.getNome();
		this.uf = cidade.getUf();
	}
}


