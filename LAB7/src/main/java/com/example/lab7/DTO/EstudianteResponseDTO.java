package com.example.lab7.DTO;

public class EstudianteResponseDTO {

    private String nombres;
    private String apellidos;
    private String dni;
    private String codigoPucp;
    private String correoInstitucional;
    private String correoPersonal;
    private String telefono;
    private String direccion;
    private String departamento;
    private String provincia;
    private String carrera;
    private String sexo;
    private String fechaNacimiento; // en formato ISO yyyy-MM-dd
    private String estado; // "Activo" o "Inactivo"

    // Constructor por defecto
    public EstudianteResponseDTO() {}

    // Constructor con todos los campos
    public EstudianteResponseDTO(String nombres, String apellidos, String dni, String codigoPucp,
                                 String correoInstitucional, String correoPersonal, String telefono,
                                 String direccion, String departamento, String provincia, String carrera,
                                 String sexo, String fechaNacimiento, String estado) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.codigoPucp = codigoPucp;
        this.correoInstitucional = correoInstitucional;
        this.correoPersonal = correoPersonal;
        this.telefono = telefono;
        this.direccion = direccion;
        this.departamento = departamento;
        this.provincia = provincia;
        this.carrera = carrera;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
    }

    // Getters y Setters

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCodigoPucp() {
        return codigoPucp;
    }

    public void setCodigoPucp(String codigoPucp) {
        this.codigoPucp = codigoPucp;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public String getCorreoPersonal() {
        return correoPersonal;
    }

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}


