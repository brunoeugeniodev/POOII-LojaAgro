/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author bruno
 */
public class Conexao {
    private static Conexao uniqueInstance;

    final private String driver="org.postgresql.Driver";
    final private String url="jdbc:postgresql://localhost:5432/" + "loja";
    final private String usuario = "postgres";
    final private String senha = "123456";
    
    public static Conexao getInstance()
    {
        if(uniqueInstance == null)
        {
            uniqueInstance = new Conexao();
            System.out.println("Criando instancia unica de conexao");
        }
        System.out.println("Retornando instancia ja existente de conexao");
        
        return uniqueInstance;
    }
    
    /*Atraves desse metodo sera possivel fazer a conexao com o banco de dados.*/
    
    public Connection conectar(){
  
        Connection conn = null;

        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, senha);
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return conn;
    }
}
