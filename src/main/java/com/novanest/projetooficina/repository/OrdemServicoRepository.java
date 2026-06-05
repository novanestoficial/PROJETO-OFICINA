package com.novanest.projetooficina.repository;

import com.novanest.projetooficina.entity.OrdemServico;
import com.novanest.projetooficina.enums.StatusOS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, UUID> {

    List<OrdemServico> findByCliente_id(UUID cliente_id);

    List<OrdemServico> findByVeiculo_id(UUID veiculo_id);

    List<OrdemServico> findByStatus(StatusOS status);

    Optional<OrdemServico> findByNumeroOs(String numeroOs);

}
