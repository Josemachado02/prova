package classes;

import java.time.LocalDate;

public class Vendas {

    private int quantVendida;
    private Produtos produto;
    private LocalDate dataDeVenda;
    private int quantTotal;
    private double valorTotal;

//--------------------------------------------------------------------------------------------------------------------//
    public double ValorTotal() {
        return valorTotal = valorTotal + Calcular();
    }
//-------------------------------------------------------------------------------------------------------------------//
    public int CalcularTotal() {
        return quantTotal = quantTotal + quantVendida ;
    }
//--------------------------------------------------------------------------------------------------------------------//
    public double Calcular() {
        return quantVendida * produto.getValor();
    }
//--------------------------------------------------------------------------------------------------------------------//
    public Vendas(int quantVendida, Produtos produto, LocalDate dataDeVenda) {
        this.quantVendida = quantVendida;
        this.produto = produto;
        this.dataDeVenda = dataDeVenda;
    }
//---------------------------------------------------------------------------------------------------------------------//
    @Override
    public String toString() {
        return "Vendas [dataDeVenda=" + dataDeVenda + ", produto=" + produto + ", quantVendida=" + quantVendida + "]";
    }
//---------------------------------------------------------------------------------------------------------------------//
    public LocalDate getDataDeVenda() {
        return dataDeVenda;
    }  

    public void setDataDeVenda(LocalDate dataDeVenda) {
        this.dataDeVenda = dataDeVenda;
    }
//---------------------------------------------------------------------------------------------------------------------//
    public int getQuantVendida() {
        return quantVendida;
    }

    public void setQuantVendida(int quantVendida) {
        this.quantVendida = quantVendida;
    }
//--------------------------------------------------------------------------------------------------------------------//
    public Produtos getProduto() {
        return produto;
    }
    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

     
}
