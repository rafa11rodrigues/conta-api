package conta.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import conta.api.model.Cliente;
import conta.api.model.Endereco;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNome(String nome);
	
	Optional<Cliente> findByCpf(String cpf);
	
	@Query("SELECT c FROM Cliente c JOIN c.enderecos e WHERE :endereco IN e")
	List<Cliente> findByEndereco(@Param("endereco") Endereco endereco);
}
