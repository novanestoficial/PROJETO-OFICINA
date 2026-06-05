package com.novanest.projetooficina.validate;

import com.novanest.projetooficina.entity.Veiculo;
import org.springframework.stereotype.Component;

@Component
public final class VeiculoValidate {

    // =========================
    // VALIDAÇÕES
    // =========================
    public void validarVeiculo(Veiculo veiculo) {

        // ================= PLACA =================
        if (veiculo.getPlaca() == null || veiculo.getPlaca().isBlank()) {
            throw new IllegalArgumentException("Placa inválida!");
        }

        if (veiculo.getPlaca().length() < 7) {
            throw new IllegalArgumentException("Placa inválida!");
        }

        // ================= MARCA =================
        if (veiculo.getMarca() == null || veiculo.getMarca().isBlank()) {
            throw new IllegalArgumentException("Marca obrigatória!");
        }

        if (veiculo.getMarca().length() > 25) {
            throw new IllegalArgumentException("Marca muito longa!");
        }

        // ================= MODELO =================
        if (veiculo.getModelo() == null || veiculo.getModelo().isBlank()) {
            throw new IllegalArgumentException("Modelo obrigatório!");
        }

        if (veiculo.getModelo().length() > 80) {
            throw new IllegalArgumentException("Modelo muito longo!");
        }

        // ================= ANO =================
        int anoAtual = java.time.Year.now().getValue();

        if (veiculo.getAno() == null) {
            throw new IllegalArgumentException("Ano obrigatório!");
        }

        if (veiculo.getAno() < 1885 || veiculo.getAno() > anoAtual + 1) {
            throw new IllegalArgumentException("Ano inválido!");
        }

        // ================= COR =================
        if (veiculo.getCor() == null || veiculo.getCor().isBlank()) {
            throw new IllegalArgumentException("Cor obrigatória!");
        }

        if (veiculo.getCor().length() > 25) {
            throw new IllegalArgumentException("Cor inválida!");
        }

        // ================= QUILOMETRAGEM =================
        if (veiculo.getQuilometragem() == null || veiculo.getQuilometragem() < 0) {
            throw new IllegalArgumentException("Quilometragem inválida!");
        }

        // ================= CLIENTE =================
        if (veiculo.getCliente() == null || veiculo.getCliente().getId() == null) {
            throw new IllegalArgumentException("Cliente inválido!");
        }
    }
}
