package com.example.evaluationjava.services;

import com.example.evaluationjava.interfaces.IEmpresa;
import com.example.evaluationjava.models.Empresa;
import com.example.evaluationjava.repositories.EmpresaRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SEmpresa implements IEmpresa {
    private final EmpresaRepo repository;

    public SEmpresa(EmpresaRepo repository) {
        this.repository = repository;
    }

    @Override
    public Empresa save(Empresa empresa) {
        return this.repository.save(empresa);
    }

    @Override
    public List<Empresa> findAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Empresa> findLastThree() {
        return this.repository.findFirst3ByOrderByIdEmpresaDesc();
    }
}
