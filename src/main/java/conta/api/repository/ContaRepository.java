package conta.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import conta.api.domain.entity.Agencia;
import conta.api.domain.entity.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

	Optional<Conta> findByNumero(String numero);
	
	List<Conta> findByAgencia(Agencia agencia);
}
