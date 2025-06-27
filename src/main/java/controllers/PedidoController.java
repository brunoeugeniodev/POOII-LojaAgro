/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import models.Pedido;
import models.PedidoDAO;

/**
 *
 * @author bruno
 */
public class PedidoController {
    PedidoDAO pedidoDAO;
    
    public PedidoController(){
        pedidoDAO = new PedidoDAO();
    }
    
    public Pedido salvar(Pedido pedido){
        return pedidoDAO.salvar(pedido);
    }
    
    public void editar(Pedido pedido){
        pedidoDAO.editar(pedido);
    }
    
    public int excluir(Pedido pedido){
        return pedidoDAO.excluir(pedido);
    }
    
    public List<Pedido> getPedidos(){
        return pedidoDAO.getPedidos();
    }
    
    public Pedido getPedidos(int id_pedido){
        return pedidoDAO.getPedidos(id_pedido);
    }
}
