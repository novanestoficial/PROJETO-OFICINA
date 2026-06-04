package com.novanest.projetooficina.repository;

import com.novanest.projetooficina.entity.OrdemServico;
import com.novanest.projetooficina.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, UUID> {
}
