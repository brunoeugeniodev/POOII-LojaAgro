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
public class ProdutoDAO {
    Connection conn;
    CategoriaDAO categoriaDAO;
    FornecedorDAO fornecedorDAO;
    
    public ProdutoDAO(){
        conn = Conexao.getInstance().conectar();
        categoriaDAO = new CategoriaDAO();
        fornecedorDAO = new FornecedorDAO();
    }
    
    public Produto salvar(Produto produto){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO produto(id_categoria, id_fornecedor, nome_produto, preco, unidade_medida, quantidade_estoque) values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, produto.getCategoria().getId_categoria());
            stmt.setInt(2, produto.getFornecedor().getId_fornecedor());
            stmt.setString(3, produto.getNome_produto());
            stmt.setDouble(4, produto.getPreco());
            stmt.setString(5, produto.getUnidade_medida());
            stmt.setInt(5, produto.getQuantidade_estoque());
            stmt.execute();//Executa o SQL no banco
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                produto.setId_produto(rs.getInt("id_produto"));
            }
            else{
                produto.setId_produto(-1);
            }
            
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return produto;
    }

    public void editar(Produto produto){
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE produto SET id_categoria=?, id_fornecedor=?, nome_produto=?, preco=?, unidade_medida=?, quantidade_estoque=?, WHERE id_produto=?");
            stmt.setInt(1, produto.getCategoria().getId_categoria());
            stmt.setInt(2, produto.getFornecedor().getId_fornecedor());
            stmt.setString(3, produto.getNome_produto());
            stmt.setDouble(4, produto.getPreco());
            stmt.setString(5, produto.getUnidade_medida());
            stmt.setInt(5, produto.getQuantidade_estoque());
            stmt.setInt(6, produto.getId_produto());
            stmt.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public int excluir(Produto produto){
        int verif = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM produto WHERE id_produto=?");
            stmt.setInt(1, produto.getId_produto());
            verif = stmt.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return verif;
    }
    
    public List<Produto> getProdutos(){
        List<Produto> lstProdutos = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM produto");
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lstProdutos.add(getProduto(rs));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return lstProdutos;
    }
    
    public Produto getProdutos(int id_produto){
        Produto produto = new Produto();
        ResultSet rs;
        try{
            PreparedStatement ppStmt  = conn.prepareStatement("SELECT * FROM produto WHERE id_produto = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ppStmt.setInt(1, id_produto);
            rs = ppStmt.executeQuery();
            rs.first();
            produto = getProduto(rs);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return produto;
    } 
    
    
    public Produto getProduto(ResultSet rs) throws SQLException{
        Produto produto = new Produto();       
        produto.setId_produto(rs.getInt("id_produto"));
        produto.setCategoria(categoriaDAO.getCategorias(rs.getInt("id_categoria")));
        produto.setFornecedor(fornecedorDAO.getFornecedores(rs.getInt("id_fornecedor")));
        produto.setNome_produto(rs.getString("nome_produto"));
        produto.setPreco(rs.getDouble("preco"));
        produto.setUnidade_medida(rs.getString("unidade_medida"));
        produto.setQuantidade_estoque(rs.getInt("quantidade_estoque"));
        return produto;
    }
}
