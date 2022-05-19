package com.example.evaluationjava.controllers;

import com.example.evaluationjava.models.Empresa;
import com.example.evaluationjava.services.SEmpresa;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/empresa")
public class EmpresaRestController {

    private final SEmpresa empresaService;

    public EmpresaRestController(SEmpresa empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/list")
    public String findAll() {
        JSONObject response = new JSONObject();
        List<Empresa> empresas;

        try {
            empresas = this.empresaService.findAll();
        } catch (DataAccessException dataAccessException) {
            response.put("mensaje", "ERROR");
            response.put("error", dataAccessException.getMostSpecificCause().getMessage());
            return response.toString();
        }

        response.put("mensaje", "EXITO");
        response.put("data", empresas);
        return response.toString();
    }

    @GetMapping("/list/last/three")
    public String findLastThree() {
        JSONObject response = new JSONObject();
        List<Empresa> empresas;

        try {
            empresas = this.empresaService.findLastThree();
        } catch (DataAccessException dataAccessException) {
            response.put("mensaje", "ERROR");
            response.put("error", dataAccessException.getMostSpecificCause().getMessage());
            return response.toString();
        }

        response.put("mensaje", "EXITO");
        response.put("data", empresas);
        return response.toString();
    }

    @PostMapping("/save")
    public String save(@RequestBody Empresa empresa) {
        JSONObject response = new JSONObject();
        Gson gson = new Gson();
        Empresa empresaSave;

        try {
            empresaSave = this.empresaService.save(empresa);
        } catch (DataAccessException dataAccessException) {
            response.put("mensaje", "ERROR");
            response.put("error", dataAccessException.getMostSpecificCause().getMessage());
            return response.toString();
        }

        response.put("mensaje", "EXITO");
        response.put("data", gson.toJson(empresaSave));
        return response.toString();
    }
}
