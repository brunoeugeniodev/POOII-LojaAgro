/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import models.Fornecedor;
import models.FornecedorDAO;

/**
 *
 * @author bruno
 */
public class FornecedorController {
    FornecedorDAO fornecedorDAO;
    
    public FornecedorController(){
        fornecedorDAO = new FornecedorDAO();
    }
    
    public Fornecedor salvar(Fornecedor fornecedor){
        return fornecedorDAO.salvar(fornecedor);
    }
    
    public void editar (Fornecedor fornecedor){
        fornecedorDAO.editar(fornecedor);
    }
    
    public int excluir(Fornecedor fornecedor){
        return fornecedorDAO.excluir(fornecedor);
    }
    
    public List<Fornecedor> getFornecedores(){
        return fornecedorDAO.getFornecedores();
    }
    
    public Fornecedor getFornecedores(int id_fornecedor){
        return fornecedorDAO.getFornecedores(id_fornecedor);
    }
}
