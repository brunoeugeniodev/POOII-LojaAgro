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
import util.ManipulaData;

/**
 *
 * @author bruno
 */
public class PedidoDAO {
    Connection conn;
    ManipulaData md;
    ClienteDAO clienteDAO;
    
    public PedidoDAO(){
        conn = Conexao.getInstance().conectar();
        md = new ManipulaData();
        clienteDAO = new ClienteDAO();
    }
    
    
    public Pedido salvar(Pedido pedido){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO pedido(id_cliente, status_pedido, valor_total, data_pedido) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, pedido.getCliente().getId_cliente());
            stmt.setString(2, pedido.getStatus_pedido());
            stmt.setDouble(3, pedido.getValor_total());
            stmt.setString(4, pedido.getData_pedido());
            stmt.execute();//Executa o SQL no banco
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                pedido.setId_pedido(rs.getInt("id_pedido"));
            }
            else{
                pedido.setId_pedido(-1);
            }
            
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return pedido;
    }

    public void editar(Pedido pedido){
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE pedido SET id_cliente=?, status_pedido=?, valor_total=?, data_pedido=?, WHERE id_pedido=?");
            stmt.setInt(1, pedido.getCliente().getId_cliente());
            stmt.setInt(1, pedido.getCliente().getId_cliente());
            stmt.setString(2, pedido.getStatus_pedido());
            stmt.setDouble(3, pedido.getValor_total());
            stmt.setString(4, pedido.getData_pedido());
            stmt.setInt(6, pedido.getId_pedido());
            stmt.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public int excluir(Pedido pedido){
        int verif = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM pedido WHERE id_pedido=?");
            stmt.setInt(1, pedido.getId_pedido());
            verif = stmt.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return verif;
    }
    
    public List<Pedido> getPedidos(){
        List<Pedido> lstPedidos = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM pedido");
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lstPedidos.add(getPedido(rs));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return lstPedidos;
    }
        
    public Pedido getPedidos(int id_pedido){
        Pedido pedido = new Pedido();
        ResultSet rs;
        try{
            PreparedStatement ppStmt  = conn.prepareStatement("SELECT * FROM pedido WHERE id_pedido = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ppStmt.setInt(1, id_pedido);
            rs = ppStmt.executeQuery();
            rs.first();
            pedido = getPedido(rs);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return pedido;
    } 
    
    public Pedido getPedido(ResultSet rs) throws SQLException{
        Pedido pedido = new Pedido();
        pedido.setId_pedido(rs.getInt("id_pedido"));
        pedido.setCliente(clienteDAO.getClientes(rs.getInt("id_cliente")));
        pedido.setStatus_pedido(rs.getString("status_pedido"));
        pedido.setValor_total(rs.getDouble("valor_total"));
        pedido.setData_pedido(rs.getString("data_pedido"));
        
        return pedido;
    }
}
