package com.novanest.projetooficina.service;


import com.novanest.projetooficina.entity.Veiculo;
import com.novanest.projetooficina.exception.VeiculoNaoEncontradoException;
import com.novanest.projetooficina.repository.VeiculoRepository;
import com.novanest.projetooficina.validate.VeiculoValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VeiculoService {


    private final VeiculoRepository veiculoRepository;
    private final VeiculoValidate veiculoValidate;

    // =========================
    // CRIAR VEICULO
    // =========================
    public Veiculo criarVeiculo(Veiculo veiculo) {
        veiculoValidate.validarVeiculo(veiculo);
        if (veiculoRepository.existsByPlaca(veiculo.getPlaca())) {
            throw new IllegalArgumentException("Placa já cadastrada!");
        }
        return veiculoRepository.save(veiculo);
    }

    // =========================
    // LISTAR TODOS OS VEICULOS
    // =========================
    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    // =========================
    // BUSCAR POR ID
    // =========================
    public Veiculo buscarPorId(UUID id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNaoEncontradoException("Veículo não encontrado!"));
    }

    // =========================
    // BUSCAR POR MARCA
    // =========================
    public List<Veiculo> buscarPorMarca(String marca) {
        List<Veiculo> veiculos = veiculoRepository.findByMarcaContainingIgnoreCase(marca);
        validarLista(veiculos, "Nenhum veículo encontrado para essa marca");
        return veiculos;
    }

    // =========================
    // BUSCAR POR MODELO
    // =========================
    public List<Veiculo> buscarPorModelo(String modelo) {
        List<Veiculo> veiculos = veiculoRepository.findByModeloContainingIgnoreCase(modelo);
        validarLista(veiculos, "Nenhum veículo encontrado para esse modelo");
        return veiculos;
    }

    // =========================
    // BUSCAR POR PLACA
    // =========================
    public Veiculo buscarPorPlaca(String placa) {
        return veiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new VeiculoNaoEncontradoException("Veículo não encontrado!"));
    }

    // =========================
    // BUSCAR POR CLIENTE
    // =========================
    public List<Veiculo> buscarPorCliente(UUID clienteId) {
        List<Veiculo> veiculos =
                veiculoRepository.findByCliente_id(clienteId);

        validarLista(veiculos,
                "Nenhum veículo encontrado para este cliente");

        return veiculos;
    }

    // =========================
    // ATUALIZAR VEICULO
    // =========================
    public Veiculo atualizarVeiculo(UUID id, Veiculo dados) {

        Veiculo veiculo = buscarPorId(id);

        if (dados.getPlaca() != null &&
                !dados.getPlaca().equals(veiculo.getPlaca())) {

            if (veiculoRepository.existsByPlaca(dados.getPlaca())) {
                throw new IllegalArgumentException("Placa já cadastrada!");
            }

            veiculo.setPlaca(dados.getPlaca());
        }

        if (dados.getMarca() != null) {
            veiculo.setMarca(dados.getMarca());
        }

        if (dados.getModelo() != null) {
            veiculo.setModelo(dados.getModelo());
        }

        if (dados.getAno() != null) {
            veiculo.setAno(dados.getAno());
        }

        if (dados.getCor() != null) {
            veiculo.setCor(dados.getCor());
        }

        if (dados.getQuilometragem() != null) {
            veiculo.setQuilometragem(dados.getQuilometragem());
        }

        if (dados.getCliente() != null) {
            veiculo.setCliente(dados.getCliente());
        }

        veiculoValidate.validarVeiculo(veiculo);
        return veiculoRepository.save(veiculo);
    }

    // =========================
    // DELETAR VEICULO
    // =========================
    public void deletarVeiculo(UUID id) {
        Veiculo veiculo = buscarPorId(id);
        veiculoRepository.delete(veiculo);
    }


    // =========================
    // VALIDAR LISTA
    // =========================
    private void validarLista(List<Veiculo> veiculos, String mensagem) {
        if (veiculos.isEmpty()) {
            throw new VeiculoNaoEncontradoException(mensagem);
        }
    }










}
