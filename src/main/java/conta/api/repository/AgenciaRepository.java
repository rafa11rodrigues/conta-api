package conta.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import conta.api.model.Agencia;
import conta.api.model.Cidade;
import conta.api.model.Estado;

public interface AgenciaRepository extends JpaRepository<Agencia, Long> {

	Optional<Agencia> findByNumero(String numero);
	
	List<Agencia> findByCidade(Cidade cidade);
	
	@Query("SELECT a FROM Agencia a WHERE a.cidade.estado = :estado")
	List<Agencia> findByEstado(@Param("estado") Estado estado);
}
