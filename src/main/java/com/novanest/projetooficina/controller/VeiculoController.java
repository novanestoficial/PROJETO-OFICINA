package com.novanest.projetooficina.controller;

import com.novanest.projetooficina.entity.Veiculo;
import com.novanest.projetooficina.repository.VeiculoRepository;
import com.novanest.projetooficina.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService service;

    @PostMapping
    public Veiculo salvarVeiculo(@RequestBody Veiculo veiculo) {
        return service.criarVeiculo(veiculo);
    }

    @GetMapping
    public List<Veiculo> listarVeiculos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Veiculo buscarVeiculoPorId(@PathVariable UUID id) {
        return  service.buscarPorId(id);
    }

    @GetMapping("/marca/{marca}")
    public List<Veiculo> buscarVeiculoPorMarca(@PathVariable String marca) {
        return service.buscarPorMarca(marca);
    }

    @GetMapping("/modelo/{modelo}")
    public List<Veiculo> buscarVeiculoPorModelo(@PathVariable String modelo) {
        return service.buscarPorModelo(modelo);
    }

    @GetMapping("/placa/{placa}")
    public Veiculo buscarPorPlaca(@PathVariable String placa) {
        return service.buscarPorPlaca(placa);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Veiculo> buscarPorClienteId(@PathVariable UUID clienteId) {
        return service.buscarPorCliente(clienteId);
    }

    @PutMapping("/{id}")
    public Veiculo atualizarCliente(@PathVariable UUID id, @RequestBody Veiculo veiculo) {
        return service.atualizarVeiculo(id, veiculo);
    }

    @DeleteMapping("/{id}")
    public void excluirVeiculo(@PathVariable UUID id) {
        service.deletarVeiculo(id);
    }



}
