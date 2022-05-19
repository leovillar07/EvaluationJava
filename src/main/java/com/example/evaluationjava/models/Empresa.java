package com.example.evaluationjava.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "empresa")
public class Empresa {

    private static final Long serialVersionUID = 81195L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpresa;

    @Column(name = "ruc", length = 11, nullable = false)
    private String ruc;

    @Column(name = "razonsocial", nullable = false)
    private String razonSocial;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "estado")
    private Boolean estado;

    @PrePersist
    public void preview() {
        this.estado = true;
    }
}
