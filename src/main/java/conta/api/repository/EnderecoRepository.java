package conta.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import conta.api.domain.entity.Cidade;
import conta.api.domain.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	List<Endereco> findByCep(String cep);
	
	List<Endereco> findByCidade(Cidade cidade);
}
