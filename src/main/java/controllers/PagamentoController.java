/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import models.Pagamento;
import models.PagamentoDAO;

/**
 *
 * @author bruno
 */
public class PagamentoController {
    PagamentoDAO pagamentoDAO;
    
    public PagamentoController(){
        pagamentoDAO = new PagamentoDAO();
    }
    
    public Pagamento salvar(Pagamento pagamento){
        return pagamentoDAO.salvar(pagamento);
    }
    
    public void editar(Pagamento pagamento){
        pagamentoDAO.editar(pagamento);
    }
    
    public int excluir(Pagamento pagamento){
        return pagamentoDAO.excluir(pagamento);
    }
}
