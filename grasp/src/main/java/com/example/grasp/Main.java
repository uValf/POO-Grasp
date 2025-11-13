package com.example.grasp;

import com.example.grasp.controller.Controller;
import com.example.grasp.model.livraria;

/**
 * Classe Main apenas para demonstrar o funcionomento.
 *
 * Observaçao:
 * - Main faz chamadas ao Controller (Controller) e nao manipula as classes de domínio diretamente.
 *   Isso segue o princípio Controller: separar a lógica de orquestraçao da lógica de negócio.
 */
public class Main {
    public static void main(String[] args) {
        livraria livraria = new livraria();
        Controller controller = new Controller(livraria);

        // Cenário de demonstraçao
        controller.addlivro("978-0134685991", "Java para leigos");
        controller.addlivro("978-0201633610", "Nintendo Wii");

        controller.registrarusuario("u1", "Ronaldo");
        controller.registrarusuario("u2", "Jamelao");

        // Emprestar um livro
        controller.emprestimolivro("u1", "978-0134685991");

        // Tentativa de emprestar o mesmo livro de novo (deve falhar por indisponibilidade)
        controller.emprestimolivro("u2", "978-0134685991");

        // Devolver o livro
        controller.returnlivro("978-0134685991");

        // Agora emprestar novamente deve funcionar
        controller.emprestimolivro("u2", "978-0134685991");
    }
}