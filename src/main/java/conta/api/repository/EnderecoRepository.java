package conta.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import conta.api.model.Cidade;
import conta.api.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	List<Endereco> findByCep(String cep);
	
	List<Endereco> findByCidade(Cidade cidade);
}
