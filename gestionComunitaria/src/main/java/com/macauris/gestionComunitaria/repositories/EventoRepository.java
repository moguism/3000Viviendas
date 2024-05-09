/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.macauris.gestionComunitaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.macauris.gestionComunitaria.models.Evento;

/**
 *
 * @author Santiago Torres
 */
public interface EventoRepository extends JpaRepository<Evento, Long>{
    
}
