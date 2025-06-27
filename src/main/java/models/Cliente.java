/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author bruno
 */

//CRUDAO
public class Cliente {
    private int id_cliente;
    private Endereco_Entrega endereco_entrega;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    public Endereco_Entrega getEndereco_entrega() {
        return endereco_entrega;
    }
    
    public void setEndereco_entrega(Endereco_Entrega endereco_entrega){
        this.endereco_entrega = endereco_entrega;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    
}
