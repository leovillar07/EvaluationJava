package com.example.evaluationjava.interfaces;

import com.example.evaluationjava.models.Empresa;

import java.util.List;

public interface IEmpresa {
    Empresa save(Empresa empresa);

    List<Empresa> findAll();

    List<Empresa> findLastThree();
}
