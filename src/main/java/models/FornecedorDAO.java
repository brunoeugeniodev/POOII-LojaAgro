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
public class FornecedorDAO {
    Connection conn;
    ProdutoDAO produtoDAO;
    
    public FornecedorDAO(){
        conn = Conexao.getInstance().conectar();
        produtoDAO = new ProdutoDAO();
    }
    
    
    public Fornecedor salvar(Fornecedor fornecedor){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO fornecedor(id_produto, nome_fornecedor, email_contato, telefone, cnpj) values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, fornecedor.getProduto().getId_produto());
            stmt.setString(2, fornecedor.getNome_fornecedor());
            stmt.setString(3, fornecedor.getEmail_contato());
            stmt.setString(4, fornecedor.getTelefone());
            stmt.setString(5, fornecedor.getCnpj());
            stmt.execute();//Executa o SQL no banco
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                fornecedor.setId_fornecedor(rs.getInt("id_fornecedor"));
            }
            else{
                fornecedor.setId_fornecedor(-1);
            }
            
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return fornecedor;
    }

    public void editar(Fornecedor fornecedor){
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE fornecedor SET id_produto=?, nome_fornecedor=?, email_contato=?, telefone=?, cnpj=?, WHERE id_fornecedor=?");
            stmt.setInt(1, fornecedor.getProduto().getId_produto());
            stmt.setString(2, fornecedor.getNome_fornecedor());
            stmt.setString(3, fornecedor.getEmail_contato());
            stmt.setString(4, fornecedor.getTelefone());
            stmt.setString(5, fornecedor.getCnpj());
            stmt.setInt(6, fornecedor.getId_fornecedor());
            stmt.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public int excluir(Fornecedor fornecedor){
        int verif = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM fornecedor WHERE id_fornecedor=?");
            stmt.setInt(1, fornecedor.getId_fornecedor());
            verif = stmt.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return verif;
    }
    
    public List<Fornecedor> getFornecedores(){
        List<Fornecedor> lstFornecedores = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM fornecedor");
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lstFornecedores.add(getFornecedor(rs));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return lstFornecedores;
    }
        
    public Fornecedor getFornecedores(int id_fornecedor){
        Fornecedor fornecedor = new Fornecedor();
        ResultSet rs;
        try{
            PreparedStatement ppStmt  = conn.prepareStatement("SELECT * FROM fornecedor WHERE id_fornecedor = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ppStmt.setInt(1, id_fornecedor);
            rs = ppStmt.executeQuery();
            rs.first();
            fornecedor = getFornecedor(rs);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return fornecedor;
    } 
    
    public Fornecedor getFornecedor(ResultSet rs) throws SQLException{
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId_fornecedor(rs.getInt("id_fornecedor"));
        fornecedor.setProduto(produtoDAO.getProdutos(rs.getInt("id_produto")));
        fornecedor.setNome_fornecedor(rs.getString("nome_fornecedor"));
        fornecedor.setEmail_contato(rs.getString("email_contato"));
        fornecedor.setTelefone(rs.getString("telefone"));
        fornecedor.setCnpj(rs.getString("cnpj"));
        
        return fornecedor;
    }
}
