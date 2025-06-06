package com.example.lab7.Service;

import com.example.lab7.Entity.Estudiante;
import com.example.lab7.DTO.EstudianteRequestDTO;
import com.example.lab7.DTO.EstudianteResponseDTO;
import com.example.lab7.Repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> listarTodos() {
        return estudianteRepository.findAll();
    }

    public Optional<Estudiante> buscarPorDni(String dni) {
        return estudianteRepository.findByDni(dni);
    }

    public Estudiante registrarEstudiante(Estudiante estudiante) {
        estudiante.setFechaRegistro(LocalDateTime.now());
        estudiante.setUltimaActualizacion(null);
        estudiante.setEstado(true);
        return estudianteRepository.save(estudiante);
    }

    public Estudiante actualizarEstudiante(String dni, Estudiante nuevosDatos) {
        Optional<Estudiante> existenteOpt = estudianteRepository.findByDni(dni);

        if (existenteOpt.isPresent()) {
            Estudiante existente = existenteOpt.get();

            // Solo actualizamos los campos que vienen
            if (nuevosDatos.getNombres() != null) existente.setNombres(nuevosDatos.getNombres());
            if (nuevosDatos.getApellidos() != null) existente.setApellidos(nuevosDatos.getApellidos());
            if (nuevosDatos.getCorreoPersonal() != null) existente.setCorreoPersonal(nuevosDatos.getCorreoPersonal());
            if (nuevosDatos.getTelefono() != null) existente.setTelefono(nuevosDatos.getTelefono());
            if (nuevosDatos.getDireccion() != null) existente.setDireccion(nuevosDatos.getDireccion());
            if (nuevosDatos.getDepartamento() != null) existente.setDepartamento(nuevosDatos.getDepartamento());
            if (nuevosDatos.getProvincia() != null) existente.setProvincia(nuevosDatos.getProvincia());
            if (nuevosDatos.getCarrera() != null) existente.setCarrera(nuevosDatos.getCarrera());

            existente.setUltimaActualizacion(LocalDateTime.now());

            return estudianteRepository.save(existente);
        } else {
            throw new RuntimeException("Estudiante con DNI " + dni + " no encontrado");
        }
    }

    public void eliminarLogico(String dni) {
        Optional<Estudiante> opt = estudianteRepository.findByDni(dni);
        if (opt.isPresent()) {
            Estudiante estudiante = opt.get();
            estudiante.setEstado(false);
            estudianteRepository.save(estudiante);
        } else {
            throw new RuntimeException("Estudiante con DNI " + dni + " no encontrado");
        }
    }
    public EstudianteResponseDTO convertirAResponseDTO(Estudiante e) {
        return new EstudianteResponseDTO(
                e.getNombres(),
                e.getApellidos(),
                e.getDni(),
                e.getCodigoPucp(),
                e.getCorreoInstitucional(),
                e.getCorreoPersonal(),
                e.getTelefono(),
                e.getDireccion(),
                e.getDepartamento(),
                e.getProvincia(),
                e.getCarrera(),
                e.getSexo(),
                e.getFechaNacimiento() != null ? e.getFechaNacimiento().toString() : null,
                e.getEstado() != null && e.getEstado() ? "Activo" : "Inactivo"
        );
    }
    public Estudiante convertirDesdeRequestDTO(EstudianteRequestDTO dto) {
        Estudiante e = new Estudiante();
        e.setNombres(dto.getNombres());
        e.setApellidos(dto.getApellidos());
        e.setDni(dto.getDni());
        e.setCodigoPucp(dto.getCodigoPucp());
        e.setFechaNacimiento(dto.getFechaNacimiento());
        e.setSexo(dto.getSexo());
        e.setCorreoInstitucional(dto.getCorreoInstitucional());
        e.setCorreoPersonal(dto.getCorreoPersonal());
        e.setTelefono(dto.getTelefono());
        e.setDireccion(dto.getDireccion());
        e.setDepartamento(dto.getDepartamento());
        e.setProvincia(dto.getProvincia());
        e.setCarrera(dto.getCarrera());

        // Estos dos campos no vienen en el DTO pero deben ser seteados
        e.setFechaRegistro(java.time.LocalDateTime.now());
        e.setEstado(true); // Por defecto activo al registrar

        return e;
    }
}

