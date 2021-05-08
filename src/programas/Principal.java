package programas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;



import classes.Produtos;
import classes.Vendas;

public class Principal {
    public static void main(String[] args) {
        List<Produtos> produtos = new ArrayList<>();
        List<Vendas> compras = new ArrayList<>();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int opcao = -1;
        Scanner ler = new Scanner(System.in);
        do {
            System.out.println("------------------------------------");
            System.out.println("--------- SEJAM BEM VINDOS ---------");
            System.out.println("------------------------------------ \n");
            System.out.println("1 - Cadastro de produtos");
            System.out.println("2 - Relatórios");
            System.out.println("3 - Realizar vendas");
            System.out.println("0 - sair.");
            System.out.println("------------------------------------ \n");
            opcao = ler.nextInt();
            ler.nextLine();

            switch(opcao){
                case 1: 
                    System.out.println("------------- CADASTROS ------------ \n");
                    int op;
                    do {
                        System.out.println("1 - Consultar \n2 - Incluir \n0 - Voltar ao menu principal \n");
                        op = ler.nextInt();
                        ler.nextLine();

                        switch(op) {
                            case 1: 
                            System.out.println("***CONSULTANDO***");
                            if(produtos.isEmpty()){
                                System.out.println("Ainda não há registros de produtos. \n");
                            }else {
                                System.out.println("Produtos em estoque: \n");
                                System.out.printf( "%-16.15s\t%-18.15s\t%-20.20s\t%-20.15s\n", "NOME", "CODIGO", "QUANTIDADE", "VALOR");
                                System.out.println("-----------------------------------------------------------------------------------------");
                                produtos.stream()
                                .sorted(Comparator.comparing(Produtos::getNome))
                                .forEach(System.out::println);
                                System.out.println("------------------------------------------------------------------------------------------ \n");
                            }
                            break;

                            case 2:
                            System.out.println("***CADASTRANDO*** \n");
                            System.out.println("Qual o nome do produto deseja cadastrar:");
                            String nvProd = ler.nextLine();
                            boolean nomerepitido = false;
                            boolean codrepitido = false;
                                System.out.println("Qual codigo deseja incluir ao produto:");
                                int cod = ler.nextInt();
                                for (Produtos pro : produtos) {
                                    if(pro.getNome().equalsIgnoreCase(nvProd)){
                                        nomerepitido = true;
                                    }

                                    if(pro.getCodigo() == cod){
                                        codrepitido = true;
                                    }
                                }
                                if(codrepitido == true ){
                                    System.out.println("Codigo já cadastrado.");
                                }else if (nomerepitido == true){
                                    System.out.println("Nome já cadastrado.");
                                    break;
                                }else{
                                    System.out.println("Qual o valor do produto:");
                                    double nvValor = ler.nextDouble();
                                    System.out.println("Qual a quantidade em estoque:");
                                    int quant = ler.nextInt();
                                    produtos.add(new Produtos(nvProd, cod, quant, nvValor));
                                }

                                
                            break;

                            case 0:
                             System.out.println("Voltando ao menu... \n");
                            break;

                            default:
                            System.out.println("*** OPÇÃO INVALIDA ***");
                            break;
                        }

                    } while (op != 0);

                break;
//---------------------------------------------------------------------------------------------------------------------------------------------------//
                case 2:
                    System.out.println("------------ RELATÓRIOS ------------ \n");
                    int op2= -1;
                    do {
                        System.out.println("1 - Produtos. \n2 - Vendas por periodo - detalhado.");
                        System.out.println("3 - Vendas por periodo - consolidado. \n0 - Voltar ao menu.");
                        op2 = ler.nextInt();
                        ler.nextLine();
                        switch(op2){
                            case 1: 
                            //Data da venda, nome do produto, quantidade, valor unitário e valor total.//
                            System.out.println("*** PRODUTOS ***");
                            if(produtos.isEmpty()){
                                System.out.println("Não há produtos registrados");
                            }else {
                                System.out.println("Produtos em estoque: \n");
                                System.out.printf("%-16.15s\t%-18.15s\t%-20.20s\t%-20.15s\n","NOME", "CODIGO", "QUANTIDADE", "VALOR");
                                System.out.println("--------------------------------------------------------------------------------------------------");
                                produtos.stream()
                                .sorted(Comparator.comparing(Produtos::getNome))
                                .forEach(System.out::println);
                                System.out.println("--------------------------------------------------------------------------------------------------");

                                DoubleSummaryStatistics dados = produtos.stream()
                                .collect(Collectors.summarizingDouble(Produtos::getValor));
                                System.out.printf("O valor minimo: %s         - O valor maximo: %s         - O valor médio: %s \n\n", dados.getMin(), dados.getMax(), dados.getAverage());
                            }
                            break;

                            case 2:
                                System.out.println("*** VENDAS POR PERIODOS - DETALHADO *** \n");
                                if(compras.isEmpty()){
                                    System.out.println("Ainda não foi efetuada nenhuma venda.");
                                }else{
                                    System.out.println("Data de inicio:");
                                    String dataInicial = ler.nextLine();
                                    System.out.println("Data final:");
                                    String dataFinal = ler.nextLine();

                                    List<Vendas> filtrarVendas = compras.stream().filter(p -> {
                                        Vendas v = (Vendas)p;
                                        return (v.getDataDeVenda().isEqual(LocalDate.parse(dataInicial, df)) || v.getDataDeVenda().isEqual(LocalDate.parse(dataFinal, df)))|| (v.getDataDeVenda().isBefore(LocalDate.parse(dataFinal, df)) && (v.getDataDeVenda().isAfter(LocalDate.parse(dataInicial, df))));
                                    }).collect(Collectors.toList());

                                    for (Vendas f : filtrarVendas) {
                                        System.out.println("Produtos em estoque: \n");
                                        System.out.printf("%-16.15s\t%-18.15s\t%-20.20s\t%-20.15s\t%-20.15s\n", "DATA", "NOME", "QUANTIDADE", "VALOR UNITARIO", "VALOR TOTAL");
                                        System.out.println("----------------------------------------------------------------------------------------------------------------");
                                        System.out.printf("%-16.15s\t%-18.15s\t%-20.20s\t%-20.15s\t%-20.15s\n", f.getDataDeVenda(), f.getProduto().getNome(), f.getQuantVendida(), f.getProduto().getValor(), f.Calcular());
                                        System.out.println("----------------------------------------------------------------------------------------------------------------");
                                        
                                    }
                                    
                                }   
                            break;

                            case 3:
                                System.out.println("*** VENDAS POR PERIODOS - CONSOLIDADO *** \n");
                                if(compras.isEmpty()){
                                    System.out.println("Ainda não foi efetuada nenhuma venda."); 
                                }else {
                                    System.out.println("Data de inicio:");
                                    String dataInicial = ler.nextLine();
                                    System.out.println("Data final:");
                                    String dataFinal = ler.nextLine();

                                    List<Vendas> filtrarVendas = compras.stream().filter(p -> {
                                        Vendas fv = (Vendas)p;
                                        return (fv.getDataDeVenda().isEqual(LocalDate.parse(dataInicial, df)) || fv.getDataDeVenda().isEqual(LocalDate.parse(dataFinal, df)))|| (fv.getDataDeVenda().isBefore(LocalDate.parse(dataFinal, df)) && (fv.getDataDeVenda().isAfter(LocalDate.parse(dataInicial, df))));
                                    }).collect(Collectors.toList());

                                    for (Vendas f : filtrarVendas) {
                                        System.out.println("Produtos em estoque: \n");
                                        System.out.printf("%-16.15s\t%-18.20s\t%-20.20s\n", "DATA DE VENDA", "QUANT.TOTAL-VENDA", "VALOR TOTAL-VENDIDO");
                                        System.out.println("-------------------------------------------------------------------------------");
                                        System.out.printf("%-16.15s\t%-18.20s\t%-20.20s\n", f.getDataDeVenda(), f.CalcularTotal(), f.ValorTotal());
                                        System.out.println("------------------------------------------------------------------------------- \n");
                                        
                                    }    
                                }
                            break;

                            case 0:
                                System.out.println("Voltando ao menu...");
                            break;


                            default:
                            System.out.println("*** OPÇÃO INVALIDA ***");
                            break;
                        }
                    } while (op2 != 0 );
                break;
//---------------------------------------------------------------------------------------------------------------------------------------------------//
                case 3:
                    System.out.println("-------------- VENDAS -------------- \n");
                    if(produtos.isEmpty()){
                        System.out.println("Ainda não há produto registrado.");
                    }else{
                    System.out.println("Qual produto deseja comprar:");
                    String prod = ler.nextLine();
                    boolean acheiProduto = false;
                    for (Produtos p : produtos) {
                        if(p.getNome().equalsIgnoreCase(prod)){
                            acheiProduto = true;
                            System.out.println("Insira a data nesse formato - dd/mm/aaaa");
                            String data = ler.nextLine();
                            System.out.println("Qual a quantidade:");
                            int quantidade = ler.nextInt();
                            ler.nextLine();
                            if(p.getQuantidaEstoque() < quantidade){
                                System.out.println("Não há quantidade suficiente em estoque. \n");
                                break;
                            }else {  
                                p.removerQuant(quantidade);
                                compras.add(new Vendas(quantidade, p, LocalDate.parse(data, df)));
                                System.out.println("*** COMPRA REALIZADA *** \n");
                                break;   
                            }
                        }
                    }
                    if(acheiProduto == false){
                        System.out.println("Produto não cadastrado.");
                    }

                    }
                break;
//-------------------------------------------------------------------------------------------------------------------------//
                 case 0:
                 System.out.println("Saindo... \nEspero ter ajudado... \nEncerrado. ");
                 return;

                 default:
                 System.out.println("*** OPÇÃO INVALIDA *** \n");
                 return;
            }
        } while (opcao != 0);
        ler.close();
    }
}
