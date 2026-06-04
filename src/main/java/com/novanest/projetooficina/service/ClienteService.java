package com.novanest.projetooficina.service;

import com.novanest.projetooficina.entity.Cliente;
import com.novanest.projetooficina.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    // =========================
    // CRIAR CLIENTE
    // =========================
    public Cliente criarCliente(Cliente cliente) {

        validarCliente(cliente);

        return clienteRepository.save(cliente);
    }

    // =========================
    // LISTAR TODOS
    // =========================
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    // =========================
    // BUSCAR POR ID
    // =========================
    public Cliente buscarPorId(UUID id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    // =========================
    // ATUALIZAR CLIENTE
    // =========================
    public Cliente atualizarCliente(UUID id, Cliente dados) {

        Cliente cliente = buscarPorId(id);

        cliente.setNome(dados.getNome());
        cliente.setEmail(dados.getEmail());
        cliente.setTelefone(dados.getTelefone());
        cliente.setEndereco(dados.getEndereco());
        cliente.setCidade(dados.getCidade());
        cliente.setEstado(dados.getEstado());
        cliente.setWhatsapp(dados.getWhatsapp());
        cliente.setObservacoes(dados.getObservacoes());

        return clienteRepository.save(cliente);
    }

    // =========================
    // DELETAR CLIENTE
    // =========================
    public void deletarCliente(UUID id) {
        Cliente cliente = buscarPorId(id);
        clienteRepository.delete(cliente);
    }

    // =========================
    // VALIDAÇÕES
    // =========================
    private void validarCliente(Cliente cliente) {

        if (cliente.getNome() == null || cliente.getNome().length() < 5) {
            throw new IllegalArgumentException("Nome inválido");
        }

        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        if (cliente.getCpf() != null &&
                clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        if (cliente.getCnpj() != null &&
                clienteRepository.existsByCnpj(cliente.getCnpj())) {
            throw new IllegalArgumentException("CNPJ já cadastrado");
        }
    }
}
