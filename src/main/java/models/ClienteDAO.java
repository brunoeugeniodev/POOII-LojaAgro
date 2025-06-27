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
public class ClienteDAO {
    Connection conn;
    Endereco_EntregaDAO enderecoDAO;
    
    public ClienteDAO(){
        conn = Conexao.getInstance().conectar();
        enderecoDAO = new Endereco_EntregaDAO();
    }
    
    
    public Cliente salvar(Cliente cliente){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO cliente(id_endereco_entrega, nome, email, telefone, cpf) values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, cliente.getEndereco_entrega().getId_endereco());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getCpf());
            stmt.execute();//Executa o SQL no banco
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                cliente.setId_cliente(rs.getInt("id_cliente"));
            }
            else{
                cliente.setId_cliente(-1);
            }
            
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return cliente;
    }

    public void editar(Cliente cliente){
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE cliente SET id_endereco_entrega=?, nome=?, email=?, telefone=?, cpf=?, WHERE id_cliente=?");
            stmt.setInt(1, cliente.getEndereco_entrega().getId_endereco());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getCpf());
            stmt.setInt(6, cliente.getId_cliente());
            stmt.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public int excluir(Cliente cliente){
        int verif = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM cliente WHERE id_cliente=?");
            stmt.setInt(1, cliente.getId_cliente());
            verif = stmt.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return verif;
    }
    
    public List<Cliente> getClientes(){
        List<Cliente> lstClientes = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM cliente");
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lstClientes.add(getCliente(rs));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return lstClientes;
    }
        
    public Cliente getClientes(int id_cliente){
        Cliente cliente = new Cliente();
        ResultSet rs;
        try{
            PreparedStatement ppStmt  = conn.prepareStatement("SELECT * FROM cliente WHERE id_cliente = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ppStmt.setInt(1, id_cliente);
            rs = ppStmt.executeQuery();
            rs.first();
            cliente = getCliente(rs);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return cliente;
    } 
    
    public Cliente getCliente(ResultSet rs) throws SQLException{
        Cliente cliente = new Cliente();
        
        cliente.setId_cliente(rs.getInt("id_cliente"));
        cliente.setEndereco_entrega(enderecoDAO.getEndereco_entregas(rs.getInt("id_endereco_entrega")));
        cliente.setNome(rs.getString("nome"));
        cliente.setEmail(rs.getString("email"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setCpf(rs.getString("cpf"));
        
        return cliente;
    }
}
