package com.novanest.projetooficina.controller;


import com.novanest.projetooficina.entity.Cliente;
import com.novanest.projetooficina.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public Cliente salvarCliente(@RequestBody Cliente cliente) {
        return service.criarCliente(cliente);
    }

    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable UUID id, @RequestBody Cliente cliente) {
        return service.atualizarCliente(id, cliente);
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Cliente buscarClientePorId(@PathVariable UUID id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable UUID id) {
        service.deletarCliente(id);
    }

}
