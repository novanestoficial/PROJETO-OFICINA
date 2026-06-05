package com.novanest.projetooficina.validate;

import com.novanest.projetooficina.entity.Cliente;
import com.novanest.projetooficina.enums.TipoCliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteValidate {

    public void validarCliente(Cliente cliente) {

        if (cliente.getNome() == null || cliente.getNome().length() < 5) {
            throw new IllegalArgumentException("Nome inválido");
        }

        if (!cliente.getEmail().contains("@") || !cliente.getEmail().contains(".")) {
            throw new IllegalArgumentException("Email inválido");
        }

        if ((cliente.getCpf() == null || cliente.getCpf().isBlank()) &&
                (cliente.getCnpj() == null || cliente.getCnpj().isBlank())) {
            throw new IllegalArgumentException("CPF ou CNPJ obrigatório");
        }

        if (cliente.getTipoCliente() == null) {
            throw new IllegalArgumentException("Tipo de cliente obrigatório");
        }

        if (cliente.getTipoCliente() == TipoCliente.PESSOA_FISICA && cliente.getCnpj() != null) {
            throw new IllegalArgumentException("Pessoa física não pode ter CNPJ");
        }

        if (cliente.getTipoCliente() == TipoCliente.PESSOA_JURIDICA && cliente.getCpf() != null) {
            throw new IllegalArgumentException("Pessoa jurídica não pode ter CPF");
        }
    }
}
