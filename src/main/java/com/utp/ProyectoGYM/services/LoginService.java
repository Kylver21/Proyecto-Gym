package com.utp.ProyectoGYM.services;

import com.utp.ProyectoGYM.dto.LoginDTO;
import com.utp.ProyectoGYM.modelo.Login;
import com.utp.ProyectoGYM.modelo.Usuario;
import com.utp.ProyectoGYM.repositorio.LoginRepositorio;
import com.utp.ProyectoGYM.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoginService {

    @Autowired
    private LoginRepositorio loginRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public List<LoginDTO> obtenerTodos() {
        return loginRepositorio.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public Optional<LoginDTO> obtenerPorId(Long id) {
        return loginRepositorio.findById(id)
                .map(this::convertirADTO);
    }

    public LoginDTO guardar(LoginDTO dto) {
        Login login = convertirAEntidad(dto);
        Login guardado = loginRepositorio.save(login);
        return convertirADTO(guardado);
    }

    public void eliminar(Long id) {
        loginRepositorio.deleteById(id);
    }

    private LoginDTO convertirADTO(Login login) {
        LoginDTO dto = new LoginDTO();
        dto.setId(login.getId());
        dto.setFecha(login.getFecha());
        dto.setExito(login.getExito());
        if (login.getUsuario() != null) {
            dto.setUsuarioId(login.getUsuario().getId());
        }
        return dto;
    }

    private Login convertirAEntidad(LoginDTO dto) {
        Login login = new Login();
        login.setId(dto.getId());
        login.setFecha(dto.getFecha());
        login.setExito(dto.getExito());
        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepositorio.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            login.setUsuario(usuario);
        }
        return login;
    }
}