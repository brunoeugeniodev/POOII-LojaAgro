/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import models.Pedido_Produto;
import models.Pedido_ProdutoDAO;

/**
 *
 * @author bruno
 */
public class Pedido_ProdutoController {
    Pedido_ProdutoDAO pedido_produtoDAO;
    
    public Pedido_ProdutoController(){
        pedido_produtoDAO = new Pedido_ProdutoDAO();
    }
    
    public Pedido_Produto salvar(Pedido_Produto pedido_produto){
        return pedido_produtoDAO.salvar(pedido_produto);
    }
    
    public void editar(Pedido_Produto pedido_produto){
        pedido_produtoDAO.editar(pedido_produto);
    }
    
    public int excluir(Pedido_Produto pedido_produto){
        return pedido_produtoDAO.excluir(pedido_produto);
    }
    
    public List<Pedido_Produto> getPedidos_Produtos(){
        return pedido_produtoDAO.getPedidos_Produtos();
    }
    
    public Pedido_Produto getPedidos_Produtos(int id_pedido_produto){
        return pedido_produtoDAO.getPedidos_Produtos(id_pedido_produto);
    }
}
