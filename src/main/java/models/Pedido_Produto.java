/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author bruno
 */
public class Pedido_Produto {
    private int id_pedido_produto;
    private Pedido pedido;
    private Produto produto;
    private int quantidade;
    private double subtotal;

    public int getId_pedido_produto() {
        return id_pedido_produto;
    }

    public void setId_pedido_produto(int id_pedido_produto) {
        this.id_pedido_produto = id_pedido_produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    
}
