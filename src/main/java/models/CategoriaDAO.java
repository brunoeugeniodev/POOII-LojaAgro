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
public class CategoriaDAO {
    Connection conn;
    
    public CategoriaDAO(){
        conn = Conexao.getInstance().conectar();
    }
    
    
    public Categoria salvar(Categoria categoria){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO categoria(nome_categoria, descricao_categoria) values(?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, categoria.getNome_categoria());
            stmt.setString(2, categoria.getDescricao());
            stmt.execute();//Executa o SQL no banco
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                categoria.setId_categoria(rs.getInt("id_categoria"));
            }
            else{
                categoria.setId_categoria(-1);
            }
            
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return categoria;
    }

    public void editar(Categoria categoria){
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE categoria SET nome_categoria?, descricao_cateogria=?, WHERE id_cateogira=?");
            stmt.setString(1, categoria.getNome_categoria());
            stmt.setString(2, categoria.getDescricao());
            stmt.setInt(3, categoria.getId_categoria());
            stmt.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public int excluir(Categoria categoria){
        int verif = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM categoria WHERE id_categoria=?");
            stmt.setInt(1, categoria.getId_categoria());
            verif = stmt.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return verif;
    }
    
    public List<Categoria> getCategorias(){
        List<Categoria> lstCategorias = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM categoria");
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lstCategorias.add(getCategoria(rs));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return lstCategorias;
    }
        
    public Categoria getCategorias(int id_categoria){
        Categoria categoria = new Categoria();
        ResultSet rs;
        try{
            PreparedStatement ppStmt  = conn.prepareStatement("SELECT * FROM categoria WHERE id_categoria = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ppStmt.setInt(1, id_categoria);
            rs = ppStmt.executeQuery();
            rs.first();
            categoria = getCategoria(rs);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return categoria;
    } 
    
    public Categoria getCategoria(ResultSet rs) throws SQLException{
        Categoria categoria = new Categoria();
        categoria.setId_categoria(rs.getInt("id_categoria"));
        categoria.setNome_categoria(rs.getString("nome_categoria"));
        categoria.setDescricao(rs.getString("descricao_categoria"));
        return categoria;
    }
}
