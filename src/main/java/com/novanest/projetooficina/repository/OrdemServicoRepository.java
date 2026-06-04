package com.novanest.projetooficina.repository;

import com.novanest.projetooficina.entity.OrdemServico;
import com.novanest.projetooficina.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, UUID> {
}
