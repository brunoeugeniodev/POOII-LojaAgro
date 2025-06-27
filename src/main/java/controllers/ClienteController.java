/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import models.Cliente;
import models.ClienteDAO;

/**
 *
 * @author bruno
 */
public class ClienteController {
    ClienteDAO clienteDAO;
    
    public ClienteController(){
        clienteDAO = new ClienteDAO();
    }
    
    public Cliente salvar(Cliente cliente){
        return clienteDAO.salvar(cliente);
    }
    
    public void editar(Cliente cliente){
        clienteDAO.editar(cliente);
    }
    
    public int excluir(Cliente cliente){
        return clienteDAO.excluir(cliente);
    }
    
    public List<Cliente> getClientes(){
        return clienteDAO.getClientes();
    }
    
    public Cliente getCategorias(int id_clientes){
        return clienteDAO.getClientes(id_clientes);
    }
}
    
