package conta.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import conta.api.domain.entity.Agencia;
import conta.api.domain.entity.Cidade;
import conta.api.domain.entity.Estado;

public interface AgenciaRepository extends JpaRepository<Agencia, Long> {

	Optional<Agencia> findByNumero(String numero);
	
	List<Agencia> findByCidade(Cidade cidade);
	
	@Query("SELECT a FROM Agencia a WHERE a.cidade.uf = :uf")
	List<Agencia> findByUf(@Param("uf") Estado uf);
}
