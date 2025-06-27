/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.Conexao;

/**
 *
 * @author bruno
 */
public class PagamentoDAO {
    Connection conn;
    PedidoDAO pedidoDAO;
    
    public PagamentoDAO(){
        conn = Conexao.getInstance().conectar();
        pedidoDAO = new PedidoDAO();
    }
    
    
    public Pagamento salvar(Pagamento pagamento){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO pagamento(id_pedido, metodo_pagamento, status_pagamento) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, pagamento.getPedido().getId_pedido());
            stmt.setString(2, pagamento.getMetodo_pagamento());
            stmt.setString(3, pagamento.getStatus_pagamento());
            stmt.execute();//Executa o SQL no banco
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                pagamento.setId_pagamento(rs.getInt("id_pagamento"));
            }
            else{
                pagamento.setId_pagamento(-1);
            }
            
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return pagamento;
    }

    public void editar(Pagamento pagamento){
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE pagamento SET id_pedido?, metodo_pagamento=?, status_pagamento=?, WHERE pagamento=?");
            stmt.setInt(1, pagamento.getPedido().getId_pedido());
            stmt.setString(2, pagamento.getMetodo_pagamento());
            stmt.setString(3, pagamento.getStatus_pagamento());
            stmt.setInt(6, pagamento.getId_pagamento());
            stmt.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public int excluir(Pagamento pagamento){
        int verif = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM pagamento WHERE id_pagamento=?");
            stmt.setInt(1, pagamento.getId_pagamento());
            verif = stmt.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return verif;
    }
    
    public List<Pagamento> getPagamentos(){
        List<Pagamento> lstPagamentos = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM pagamento");
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lstPagamentos.add(getPagamento(rs));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return lstPagamentos;
    }
        
    public Pagamento getPagamentos(int id_pagamento){
        Pagamento pagamento = new Pagamento();
        ResultSet rs;
        try{
            PreparedStatement ppStmt  = conn.prepareStatement("SELECT * FROM pagamento WHERE id_pagamento = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ppStmt.setInt(1, id_pagamento);
            rs = ppStmt.executeQuery();
            rs.first();
            pagamento = getPagamento(rs);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return pagamento;
    } 
    
    public Pagamento getPagamento(ResultSet rs) throws SQLException{
        Pagamento pagamento = new Pagamento();
        pagamento.setId_pagamento(rs.getInt("id_pagamento"));
        pagamento.setPedido(pedidoDAO.getPedidos(rs.getInt("id_pedido")));
        pagamento.setMetodo_pagamento(rs.getString("metodo_pagamento"));
        pagamento.setStatus_pagamento(rs.getString("status_pagamento"));
        
        return pagamento;
    }
}
