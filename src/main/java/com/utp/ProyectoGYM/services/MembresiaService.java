package com.utp.ProyectoGYM.services;

import com.utp.ProyectoGYM.dto.MembresiaDTO;
import com.utp.ProyectoGYM.modelo.Membresia;
import com.utp.ProyectoGYM.repositorio.MembresiaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MembresiaService {

    @Autowired
    private MembresiaRepositorio membresiaRepositorio;

    public List<MembresiaDTO> obtenerTodos() {
        return membresiaRepositorio.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public Optional<MembresiaDTO> obtenerPorId(Long id) {
        return membresiaRepositorio.findById(id)
                .map(this::convertirADTO);
    }

    public MembresiaDTO guardar(MembresiaDTO membresiaDTO) {
        Membresia membresia = convertirAEntidad(membresiaDTO);
        Membresia guardado = membresiaRepositorio.save(membresia);
        return convertirADTO(guardado);
    }

    public void eliminar(Long id) {
        membresiaRepositorio.deleteById(id);
    }

    private MembresiaDTO convertirADTO(Membresia membresia) {
        MembresiaDTO dto = new MembresiaDTO();
        dto.setId(membresia.getId());
        dto.setTipo(membresia.getTipo());
        dto.setDescripcion(membresia.getDescripcion());
        dto.setPrecio(membresia.getPrecio());
        dto.setDuracionDias(membresia.getDuracionDias());
        dto.setEstado(membresia.getEstado());
        return dto;
    }

    private Membresia convertirAEntidad(MembresiaDTO dto) {
        Membresia membresia = new Membresia();
        membresia.setId(dto.getId());
        membresia.setTipo(dto.getTipo());
        membresia.setDescripcion(dto.getDescripcion());
        membresia.setPrecio(dto.getPrecio());
        membresia.setDuracionDias(dto.getDuracionDias());
        membresia.setEstado(dto.getEstado() != null ? dto.getEstado() : true);
        return membresia;
    }
}