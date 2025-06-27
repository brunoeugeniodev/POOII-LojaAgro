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
public class Pedido_ProdutoDAO {
    Connection conn;
    ProdutoDAO produtoDAO;
    PedidoDAO pedidoDAO;
    
    public Pedido_ProdutoDAO(){
        conn = Conexao.getInstance().conectar();
        produtoDAO = new ProdutoDAO();
        pedidoDAO = new PedidoDAO();
    }
    
    
    public Pedido_Produto salvar(Pedido_Produto pedidoP){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO pedido_produto(id_produto, id_pedido, quantidade, subtotal) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, pedidoP.getProduto().getId_produto());
            stmt.setInt(2, pedidoP.getPedido().getId_pedido());
            stmt.setInt(3, pedidoP.getQuantidade());
            stmt.setDouble(4, pedidoP.getSubtotal());
            stmt.execute();//Executa o SQL no banco
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                pedidoP.setId_pedido_produto(rs.getInt("id_pedido_produto"));
            }
            else{
                pedidoP.setId_pedido_produto(-1);
            }
            
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return pedidoP;
    }

    public void editar(Pedido_Produto pedidoP){
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE pedido_produto SET id_produto=?, id_pedido=?, quantidade=?, subtotal=?, WHERE id_pedido_produto=?");
            stmt.setInt(1, pedidoP.getProduto().getId_produto());
            stmt.setInt(2, pedidoP.getPedido().getId_pedido());
            stmt.setInt(3, pedidoP.getQuantidade());
            stmt.setDouble(4, pedidoP.getSubtotal());
            stmt.setInt(5, pedidoP.getId_pedido_produto());
            stmt.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public int excluir(Pedido_Produto pedidoP){
        int verif = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM pedido_produto WHERE id_pedido_produto=?");
            stmt.setInt(1, pedidoP.getId_pedido_produto());
            verif = stmt.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return verif;
    }
    
    public List<Pedido_Produto> getPedidos_Produtos(){
        List<Pedido_Produto> lstPedidosP = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM pedido_produto");
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lstPedidosP.add(getPedido_produto(rs));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return lstPedidosP;
    }
        
    public Pedido_Produto getPedidos_Produtos(int id_pedido_produto){
        Pedido_Produto pedidoP = new Pedido_Produto();
        ResultSet rs;
        try{
            PreparedStatement ppStmt  = conn.prepareStatement("SELECT * FROM pedido_produto WHERE id_pedido_produto = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ppStmt.setInt(1, id_pedido_produto);
            rs = ppStmt.executeQuery();
            rs.first();
            pedidoP = getPedido_produto(rs);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return pedidoP;
    } 
    
    public Pedido_Produto getPedido_produto(ResultSet rs) throws SQLException{
        Pedido_Produto pedidoP = new Pedido_Produto();
        pedidoP.setId_pedido_produto(rs.getInt("id_pedido_produto"));
        pedidoP.setProduto(produtoDAO.getProdutos(rs.getInt("id_produto")));
        pedidoP.setPedido(pedidoDAO.getPedidos(rs.getInt("id_pedido")));
        pedidoP.setQuantidade(rs.getInt("quantidade"));
        pedidoP.setSubtotal(rs.getDouble("subtotal"));
        
        return pedidoP;
    }
}
