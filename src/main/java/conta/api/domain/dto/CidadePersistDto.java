package conta.api.domain.dto;

import conta.api.domain.entity.Estado;
import lombok.Data;

@Data
public class CidadePersistDto {

	private String nome;
	private Estado uf;
}
