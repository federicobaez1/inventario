package com.fede.inventario.service;

import com.fede.inventario.model.Deposito;
import com.fede.inventario.repository.DepositoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepositoService {

    private final DepositoRepository depositoRepository;

    public DepositoService(DepositoRepository depositoRepository) {
        this.depositoRepository = depositoRepository;
    }

    // Obtener todos los depositos
    public List<Deposito> obtenerTodos() {
        return depositoRepository.findAll();
    }

    // Obtener un deposito por ID
    public Optional<Deposito> obtenerPorId(Long id) {
        return depositoRepository.findById(id);
    }

    // Guardar o actualizar un deposito
    public Deposito guardar(Deposito deposito) {
        return depositoRepository.save(deposito);
    }

    // Eliminar un deposito por ID
    public void eliminar(Long id) {
        depositoRepository.deleteById(id);
    }

    // Validar existencia
    public boolean existePorId(Long id) {
        return depositoRepository.existsById(id);
    }
}
