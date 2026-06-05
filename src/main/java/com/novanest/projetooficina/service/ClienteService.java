package com.novanest.projetooficina.service;

import com.novanest.projetooficina.entity.Cliente;
import com.novanest.projetooficina.exception.ClienteNaoEncontradoException;
import com.novanest.projetooficina.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));
    }

    // =========================
    // BUSCAR POR EMAIL
    // =========================
    public Cliente buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Email não encontrado"));
    }


    // =========================
    // BUSCAR POR CPF
    // =========================
    public Cliente buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ClienteNaoEncontradoException("CPF não encontrado"));
    }

    // =========================
    // BUSCAR POR NOME IGNORANDO PARTES INCOMPLETAS
    // =========================
    public List<Cliente> buscarPorNome(String nome) {
        List<Cliente> clientes = clienteRepository.findByNomeContainingIgnoreCase(nome);

        if (clientes.isEmpty()) {
            throw new ClienteNaoEncontradoException("Nenhum cliente encontrado com esse nome");
        }

        return clientes;
    }

    // =========================
    // ATUALIZAR CLIENTE
    // =========================
    public Cliente atualizarCliente(UUID id, Cliente dados) {

        Cliente cliente = buscarPorId(id);

        if (dados.getEmail() != null && !dados.getEmail().equals(cliente.getEmail())) {
            if (clienteRepository.existsByEmail(dados.getEmail())) {
                throw new IllegalArgumentException("Email já cadastrado");
            }
            cliente.setEmail(dados.getEmail());
        }

        if (dados.getCpf() != null && !dados.getCpf().equals(cliente.getCpf())) {
            if (clienteRepository.existsByCpf(dados.getCpf())) {
                throw new IllegalArgumentException("CPF já cadastrado");
            }
            cliente.setCpf(dados.getCpf());
        }

        if (dados.getNome() != null) cliente.setNome(dados.getNome());
        if (dados.getTelefone() != null) cliente.setTelefone(dados.getTelefone());
        if (dados.getEndereco() != null) cliente.setEndereco(dados.getEndereco());
        if (dados.getCidade() != null) cliente.setCidade(dados.getCidade());
        if (dados.getEstado() != null) cliente.setEstado(dados.getEstado());
        if (dados.getWhatsapp() != null) cliente.setWhatsapp(dados.getWhatsapp());
        if (dados.getObservacoes() != null) cliente.setObservacoes(dados.getObservacoes());

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

        if (cliente.getEmail() == null || cliente.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email obrigatório");
        }

        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        if (cliente.getCpf() != null && !cliente.getCpf().isBlank()) {
            if (clienteRepository.existsByCpf(cliente.getCpf())) {
                throw new IllegalArgumentException("CPF já cadastrado");
            }
        }

        if (cliente.getCnpj() != null && !cliente.getCnpj().isBlank()) {
            if (clienteRepository.existsByCnpj(cliente.getCnpj())) {
                throw new IllegalArgumentException("CNPJ já cadastrado");
            }
        }
    }
}
