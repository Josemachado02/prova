package classes;

public class Produtos {
    private String nome;
    private int codigo;
    private int quantidaEstoque;
    private double valor;

    public void removerQuant(int quant) {
        quantidaEstoque -= quant;
    }
//--------------------------------------------------------------------------------//  
public Produtos(String nome, int codigo, int quantidaEstoque, double valor) {
        this.nome = nome;
        this.codigo = codigo;
        this.quantidaEstoque = quantidaEstoque;
        this.valor = valor;
    }
//---------------------------------------------------------------------------------//
@Override
    public String toString() {
        return "NOME:" + nome + "             CÓDIGO: " + codigo +"              QUANTIDADE: " + quantidaEstoque + "           VALOR: "
                + valor;
    }
//----------------------------------------------------------------------------//   
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
//-----------------------------------------------------------------------------//
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
//------------------------------------------------------------------------------//
    public int getQuantidaEstoque() {
        return quantidaEstoque;
    }
    public void setQuantidaEstoque(int quantidaEstoque) {
        this.quantidaEstoque = quantidaEstoque;
    }
//-----------------------------------------------------------------------------//
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
//-----------------------------------------------------------------------------//
    
}
