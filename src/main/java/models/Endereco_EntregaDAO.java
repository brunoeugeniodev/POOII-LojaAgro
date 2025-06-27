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
public class Endereco_EntregaDAO {
    Connection conn;
    ClienteDAO clienteDAO;
    
    public Endereco_EntregaDAO(){
        conn = Conexao.getInstance().conectar();
        clienteDAO = new ClienteDAO();
    }
    
    public Endereco_Entrega salvar(Endereco_Entrega endereco_entrega){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO endereco_entrega(id_cliente, rua, numero, bairro, cidade, estado, cep, complemento) values(?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, endereco_entrega.getCliente().getId_cliente());
            stmt.setString(2, endereco_entrega.getRua());
            stmt.setInt(3, endereco_entrega.getNumero());
            stmt.setString(4, endereco_entrega.getBairro());
            stmt.setString(5, endereco_entrega.getCidade());
            stmt.setString(6, endereco_entrega.getEstado());
            stmt.setString(7, endereco_entrega.getCep());
            stmt.setString(8, endereco_entrega.getComplemento());
            stmt.execute();//Executa o SQL no banco
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                endereco_entrega.setId_endereco(rs.getInt("id_endereco_entrega"));
            }
            else{
                endereco_entrega.setId_endereco(-1);
            }
            
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return endereco_entrega;
    }

    public void editar(Endereco_Entrega endereco_entrega){
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE endereco_entrega SET id_cliente=?, rua=?, numero=?, bairro=?, cidade=?, estado=?, cep=?, complemento=?, WHERE id_endereco_entrega=?");
            stmt.setInt(1, endereco_entrega.getCliente().getId_cliente());
            stmt.setString(2, endereco_entrega.getRua());
            stmt.setInt(3, endereco_entrega.getNumero());
            stmt.setString(4, endereco_entrega.getBairro());
            stmt.setString(5, endereco_entrega.getCidade());
            stmt.setString(6, endereco_entrega.getEstado());
            stmt.setString(7, endereco_entrega.getCep());
            stmt.setString(8, endereco_entrega.getComplemento());
            stmt.setInt(9, endereco_entrega.getId_endereco());
            stmt.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public int excluir(Endereco_Entrega endereco_entrega){
        int verif = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM endereco_entrega WHERE id_endereco_entrega=?");
            stmt.setInt(1, endereco_entrega.getId_endereco());
            verif = stmt.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return verif;
    }
    
    public List<Endereco_Entrega> getEndereco_entregas(){
        List<Endereco_Entrega> lstEnderecos = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM endereco_entrega");
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lstEnderecos.add(getEndereco_entrega(rs));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return lstEnderecos;
    }
    
    public Endereco_Entrega getEndereco_entregas(int id_endereco){
        Endereco_Entrega endereco_entrega = new Endereco_Entrega();
        ResultSet rs;
        try{
            PreparedStatement ppStmt  = conn.prepareStatement("SELECT * FROM endereco_entrega WHERE id_endereco_entrega = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ppStmt.setInt(1, id_endereco);
            rs = ppStmt.executeQuery();
            rs.first();
            endereco_entrega = getEndereco_entrega(rs);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return endereco_entrega;
    } 
    
    
    public Endereco_Entrega getEndereco_entrega(ResultSet rs) throws SQLException{
        Endereco_Entrega endereco_entrega = new Endereco_Entrega();       
        endereco_entrega.setId_endereco(rs.getInt("id_endereco_entrega"));
        endereco_entrega.setCliente(clienteDAO.getClientes(rs.getInt("id_cliente")));
        endereco_entrega.setRua(rs.getString("rua"));
        endereco_entrega.setNumero(rs.getInt("numero"));
        endereco_entrega.setBairro(rs.getString("bairro"));
        endereco_entrega.setEstado(rs.getString("estado"));
        endereco_entrega.setCep(rs.getString("cep"));
        endereco_entrega.setEstado(rs.getString("complemento"));
        
        return endereco_entrega;
    }
}
