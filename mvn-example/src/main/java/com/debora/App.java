package com.debora;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import com.debora.model.Produto;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int operation = 0, qtd = 0;
        String name, price;
        Double total = 0.0;
        List<Produto> listaProdutos = new ArrayList<Produto>();
        System.out.println("Hello Roberto!");
        System.out.println("O que gostaria de fazer ?");
        while (operation < 3) {
            System.out.printf("1) Listar Produtos;\n" +
                    "2) Cadastrar Produto\n" +
                    "3) Sair\n");
            operation = Integer.parseInt(input.nextLine());
            if (operation == 3)
                break;
            if (operation == 1) {
                for (Produto p : listaProdutos) {
                    total += p.getValor();
                    System.out.println(p);
                }
                System.out.printf("Valor total dos produtos: %.2f\n", total);
            } else {
                System.out.println("Quantos produtos deseja cadastrar?");
                qtd = Integer.parseInt(input.nextLine());
                for (int i = 0; i < qtd; i++) {
                    Produto p = new Produto();
                    System.out.println("Digite o nome:");
                    System.out.println("Digite o valor:");
                    name = input.nextLine();
                    price = input.nextLine();
                    p.setNome(name);
                    p.setValor(Double.parseDouble(price));
                    listaProdutos.add(p);
                }
            }
        }
        input.close();
        System.out.println("Até logo!");
    }
}
