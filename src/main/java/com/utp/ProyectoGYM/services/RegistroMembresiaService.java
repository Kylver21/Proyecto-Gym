package com.utp.ProyectoGYM.services;

import com.utp.ProyectoGYM.dto.RegistroMembresiaDTO;
import com.utp.ProyectoGYM.modelo.RegistroMembresia;
import com.utp.ProyectoGYM.modelo.Usuario;
import com.utp.ProyectoGYM.modelo.Membresia;
import com.utp.ProyectoGYM.repositorio.RegistroMembresiaRepositorio;
import com.utp.ProyectoGYM.repositorio.UsuarioRepositorio;
import com.utp.ProyectoGYM.repositorio.MembresiaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistroMembresiaService {

    @Autowired
    private RegistroMembresiaRepositorio registroMembresiaRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private MembresiaRepositorio membresiaRepositorio;

    public List<RegistroMembresiaDTO> obtenerTodos() {
        return registroMembresiaRepositorio.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public Optional<RegistroMembresiaDTO> obtenerPorId(Long id) {
        return registroMembresiaRepositorio.findById(id)
                .map(this::convertirADTO);
    }

    public RegistroMembresiaDTO guardar(RegistroMembresiaDTO dto) {
        RegistroMembresia entidad = convertirAEntidad(dto);
        RegistroMembresia guardado = registroMembresiaRepositorio.save(entidad);
        return convertirADTO(guardado);
    }

    public void eliminar(Long id) {
        registroMembresiaRepositorio.deleteById(id);
    }

    public List<RegistroMembresiaDTO> obtenerPorUsuario(Long usuarioId) {
        return registroMembresiaRepositorio.findByUsuarioId(usuarioId).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private RegistroMembresiaDTO convertirADTO(RegistroMembresia entidad) {
        RegistroMembresiaDTO dto = new RegistroMembresiaDTO();
        dto.setId(entidad.getId());
        dto.setFechaInicio(entidad.getFechaInicio());
        dto.setFechaFin(entidad.getFechaFin());
        if (entidad.getUsuario() != null) {
            dto.setUsuarioId(entidad.getUsuario().getId());
        }
        if (entidad.getMembresia() != null) {
            dto.setMembresiaId(entidad.getMembresia().getId());
        }
        return dto;
    }

    private RegistroMembresia convertirAEntidad(RegistroMembresiaDTO dto) {
        RegistroMembresia entidad = new RegistroMembresia();
        entidad.setId(dto.getId());
        entidad.setFechaInicio(dto.getFechaInicio());
        entidad.setFechaFin(dto.getFechaFin());
        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepositorio.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            entidad.setUsuario(usuario);
        }
        if (dto.getMembresiaId() != null) {
            Membresia membresia = membresiaRepositorio.findById(dto.getMembresiaId())
                    .orElseThrow(() -> new RuntimeException("Membresía no encontrada"));
            entidad.setMembresia(membresia);
        }
        return entidad;
    }
}