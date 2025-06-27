/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;
import java.util.List;
import models.Categoria;
import models.CategoriaDAO;

/**
 *
 * @author bruno
 */
public class CategoriaController {
    CategoriaDAO categoriaDAO;
    
    public CategoriaController(){
        categoriaDAO = new CategoriaDAO();
    }
    
    public Categoria salvar(Categoria categoria){
        return categoriaDAO.salvar(categoria);
    }
    
    public void editar(Categoria categoria){
        categoriaDAO.editar(categoria);
    }
    
    public int excluir(Categoria categoria){
        return categoriaDAO.excluir(categoria);
    }
    
    public List<Categoria> getCategorias(){
        return categoriaDAO.getCategorias();
    }
    
    public Categoria getCategorias(int id_categoria){
        return categoriaDAO.getCategorias(id_categoria);
    }
}
