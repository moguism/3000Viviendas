package com.macauris.gestionComunitaria.models;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Table(name="comunidades")
public class Comunidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String direccion;
    
    @OneToMany(mappedBy = "comunidad", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonManagedReference(value="comunidad-bloque")
    public List<Bloque> bloques;

    @OneToMany(mappedBy = "comunidad", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonManagedReference(value ="comunidad-contrato")
    public List<Contrato> contratos;

    @OneToMany(mappedBy = "comunidad", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "comunidad-local")
    public List<Local> locales;

    @OneToMany(mappedBy = "comunidad", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonManagedReference(value ="comunidad-deuda")
    public List<Deuda> deudas;

    @OneToMany(mappedBy = "comunidad", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "comunidad-ingreso")
    public List<Ingreso> ingresos;

    @OneToMany(mappedBy = "comunidad", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "comunidad-gasto")
    public List<Gasto> gastos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Bloque> getBloques() {
        return bloques;
    }

    public void setBloques(List<Bloque> bloques) {
        this.bloques = bloques;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public List<Local> getLocales() {
        return locales;
    }

    public void setLocales(List<Local> locales) {
        this.locales = locales;
    }

    public List<Deuda> getDeudas() {
        return deudas;
    }

    public void setDeudas(List<Deuda> deudas) {
        this.deudas = deudas;
    }

    public List<Ingreso> getIngresos() {
        return ingresos;
    }

    public void setIngresos(List<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    
}


