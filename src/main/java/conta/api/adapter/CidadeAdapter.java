package conta.api.adapter;

import conta.api.domain.dto.CidadeResponseDto;
import conta.api.domain.entity.Cidade;

public class CidadeAdapter {

	public static CidadeResponseDto entityToResponse(Cidade cidade) {
		return new CidadeResponseDto(cidade);
	}
}
