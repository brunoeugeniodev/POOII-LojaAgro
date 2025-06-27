/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import models.Endereco_Entrega;
import models.Endereco_EntregaDAO;

/**
 *
 * @author bruno
 */
public class Endereco_EntregaController {
    Endereco_EntregaDAO enderecoDAO;
    
    public Endereco_EntregaController(){
        enderecoDAO = new Endereco_EntregaDAO();
    }
    
    public Endereco_Entrega salvar(Endereco_Entrega endereco){
        return enderecoDAO.salvar(endereco);
    }
    
    public void editar(Endereco_Entrega endereco){
        enderecoDAO.editar(endereco);
    }
    
    public int excluir(Endereco_Entrega endereco){
        return enderecoDAO.excluir(endereco);
    }
    
    public List<Endereco_Entrega> getEndereco_entregas(){
        return enderecoDAO.getEndereco_entregas();
    }
    
    public Endereco_Entrega getEnderecos(int id_enderecos){
        return enderecoDAO.getEndereco_entregas(id_enderecos);
    }
}
