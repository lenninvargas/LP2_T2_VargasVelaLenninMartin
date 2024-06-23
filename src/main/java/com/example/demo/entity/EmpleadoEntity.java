package com.example.demo.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_empleado")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoEntity {
	
	@Id
	@Column(
			name = "dni_empleado", 
			columnDefinition = "CHAR(8)", 
			nullable = false)
	private String dniEmpleado;
	
	@Column(
			name = "nombre_empleado", 
			columnDefinition = "VARCHAR(45)",
			nullable = false)
	private String nombreEmpleado;
	
	@Column(
			name = "apellido_empleado", 
			columnDefinition = "VARCHAR(45)",
			nullable = false)
	private String apellidoEmpleado;
	
	@Column(name = "fecha_nacimiento", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private LocalDate fechaNacimiento;
	
	@Column(columnDefinition = "VARCHAR(45)", nullable = false)
	private String direccion;
	
	@Column(columnDefinition = "VARCHAR(45)", nullable = false)
	private String correo;
	
	@ManyToOne
	@JoinColumn(name = "area_id", nullable = false)
	private AreaEntity areaId;
	
}
