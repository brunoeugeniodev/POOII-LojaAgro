/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import models.Produto;
import models.ProdutoDAO;

/**
 *
 * @author bruno
 */
public class ProdutoController {
    ProdutoDAO produtoDAO;
    
    public ProdutoController(){
        produtoDAO = new ProdutoDAO();
    }
    
    public Produto salvar(Produto produto){
        return produtoDAO.salvar(produto);
    }
    
    public void editar(Produto produto){
        produtoDAO.editar(produto);
    }
    
    public int excluir(Produto produto){
        return produtoDAO.excluir(produto);
    }
    
    public List<Produto> getProdutos(){
        return produtoDAO.getProdutos();
    }
    
    public Produto getProdutos(int id_pedido){
        return produtoDAO.getProdutos(id_pedido);
    }
}
