package conta.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import conta.api.domain.entity.Cidade;
import conta.api.domain.entity.Estado;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	Optional<Cidade> findByNome(String nome);
	
	List<Cidade> findByUf(Estado uf);
}
