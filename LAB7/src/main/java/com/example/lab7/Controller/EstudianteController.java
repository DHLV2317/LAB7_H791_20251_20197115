package com.example.lab7.Controller;

import com.example.lab7.DTO.EstudianteRequestDTO;
import com.example.lab7.DTO.EstudianteResponseDTO;
import com.example.lab7.Entity.Estudiante;
import com.example.lab7.Service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    private EstudianteResponseDTO convertirAResponseDTO(Estudiante e) {
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

    @GetMapping
    public List<EstudianteResponseDTO> listarTodos() {
        return estudianteService.listarTodos()
                .stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{dni}")
    public ResponseEntity<?> buscarPorDni(@PathVariable String dni) {
        return estudianteService.buscarPorDni(dni)
                .map(est -> ResponseEntity.ok(convertirAResponseDTO(est)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody EstudianteRequestDTO dto) {
        try {
            Estudiante estudiante = estudianteService.convertirDesdeRequestDTO(dto);
            Estudiante guardado = estudianteService.registrarEstudiante(estudiante);
            return ResponseEntity.ok(convertirAResponseDTO(guardado));
        } catch (Exception e) {
            System.out.println("🛑 Error en POST /estudiantes");
            e.printStackTrace();  // Consola
            Map<String, String> error = new HashMap<>();
            error.put("tipo", e.getClass().getSimpleName());
            error.put("mensaje", "Error al registrar estudiante");
            error.put("detalle", e.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @PutMapping("/{dni}")
    public ResponseEntity<?> actualizar(@PathVariable String dni, @Valid @RequestBody EstudianteRequestDTO dto) {
        try {
            Estudiante estudiante = estudianteService.convertirDesdeRequestDTO(dto);
            Estudiante actualizado = estudianteService.actualizarEstudiante(dni, estudiante);
            return ResponseEntity.ok(convertirAResponseDTO(actualizado));
        } catch (Exception e) {
            System.out.println("🛑 Error en PUT /estudiantes/" + dni);
            e.printStackTrace();  // Consola
            Map<String, String> error = new HashMap<>();
            error.put("tipo", e.getClass().getSimpleName());
            error.put("mensaje", "Error al actualizar estudiante");
            error.put("detalle", e.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<?> eliminar(@PathVariable String dni) {
        estudianteService.eliminarLogico(dni);
        return ResponseEntity.noContent().build();
    }
}
