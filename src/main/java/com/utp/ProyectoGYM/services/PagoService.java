package com.utp.ProyectoGYM.services;

import com.utp.ProyectoGYM.dto.PagoDTO;
import com.utp.ProyectoGYM.modelo.Pago;
import com.utp.ProyectoGYM.modelo.RegistroMembresia;
import com.utp.ProyectoGYM.repositorio.PagoRepositorio;
import com.utp.ProyectoGYM.repositorio.RegistroMembresiaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagoService {

    @Autowired
    private PagoRepositorio pagoRepositorio;

    @Autowired
    private RegistroMembresiaRepositorio registroMembresiaRepositorio;

    public List<PagoDTO> obtenerTodos() {
        return pagoRepositorio.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public Optional<PagoDTO> obtenerPorId(Long id) {
        return pagoRepositorio.findById(id)
                .map(this::convertirADTO);
    }

    public PagoDTO guardar(PagoDTO pagoDTO) {
        Pago pago = convertirAEntidad(pagoDTO);
        Pago guardado = pagoRepositorio.save(pago);
        return convertirADTO(guardado);
    }

    public void eliminar(Long id) {
        pagoRepositorio.deleteById(id);
    }

    private PagoDTO convertirADTO(Pago pago) {
        PagoDTO dto = new PagoDTO();
        dto.setId(pago.getId());
        dto.setMonto(pago.getMonto());
        dto.setFechaPago(pago.getFechaPago());
        dto.setMetodoPago(pago.getMetodoPago());
        if (pago.getRegistroMembresia() != null) {
            dto.setRegistroMembresiaId(pago.getRegistroMembresia().getId());
        }
        return dto;
    }

    private Pago convertirAEntidad(PagoDTO dto) {
        Pago pago = new Pago();
        pago.setId(dto.getId());
        pago.setMonto(dto.getMonto());
        pago.setFechaPago(dto.getFechaPago());
        pago.setMetodoPago(dto.getMetodoPago());
        if (dto.getRegistroMembresiaId() != null) {
            RegistroMembresia reg = registroMembresiaRepositorio.findById(dto.getRegistroMembresiaId())
                    .orElseThrow(() -> new RuntimeException("Registro de membresía no encontrado"));
            pago.setRegistroMembresia(reg);
        }
        return pago;
    }
}
