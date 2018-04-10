/**
 * Enum que representa los diferentes tipos de empleados que existen en el callcenter
 */
enum TipoEmpleadoEnum {
    OPERATOR(1, "Operador"),
    SUPERVISOR(2, "Supervisor"),
    DIRECTOR(3, "Director");

    private Integer value;
    private String descripcion;

    public Integer getValue() {
        return value;
    }

    public String getDescripcion() {
        return descripcion;
    }

    TipoEmpleadoEnum(Integer i, String descripcion) {
        this.value = i;
        this.descripcion = descripcion;
    }

}
