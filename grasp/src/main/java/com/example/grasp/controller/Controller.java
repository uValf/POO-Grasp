package com.example.grasp.controller;

import java.util.Optional;

import com.example.grasp.model.emprestimo;
import com.example.grasp.model.livraria;
import com.example.grasp.model.livro;
import com.example.grasp.model.usuario;

/**
 * Controller — implementaçao do padrao GRASP "Controller"
 *
 * Justificativa:
 * - O Controller representa um objeto responsável por receber e coordenar uma operaçao do sistema
 *   (por exemplo, comandos vindos da interface do usuário) delegando para objetos do domínio apropriados.
 *
 * - Aqui, Controller age como um ponto de entrada (controlador) para operações da "aplicaçao":
 *   adicionar livro, registrar usuário, emprestar e devolver.
 *
 * Desejamos evitar que a UI (ou a classe Main) manipule diretamente os detalhes do domínio (como regras de empréstimo).
 * Assim, Main invoca métodos neste Controller que por sua vez delegam para o domínio (livraria).
 *
 * Observações sobre Low Coupling e High Cohesion:
 * - Controller tem responsabilidade de orquestrar; nao implementa regras de negócio detalhadas (alta coesao).
 * - O acoplamento fica baixo porque o controlador chama apenas a API pública de livraria e nao manipula internomente os campos.
 */
public class Controller {
    private final livraria livraria;

    public Controller(livraria livraria) {
        this.livraria = livraria;
    }

    public void addlivro(String isbn, String titulo) {
        livro livro = new livro(isbn, titulo);
        livraria.addlivro(livro);
        System.out.println("Livro adicionado: " + livro);
    }

    public void registrarusuario(String id, String nome) {
        usuario usuario = new usuario(id, nome);
        livraria.registrarusuario(usuario);
        System.out.println("Usuário registrado: " + usuario);
    }

    public void emprestimolivro(String usuarioId, String isbn) {
        Optional<usuario> usuarioOpt = livraria.acharusuarioId(usuarioId);
        Optional<livro> livroOpt = livraria.acharlivroIsbn(isbn);

        if (usuarioOpt.isEmpty()) {
            System.out.println("Nao foi possível realizar o empréstimo: usuário nao encontrado.");
            return;
        }
        if (livroOpt.isEmpty()) {
            System.out.println("Nao foi possível realizar o empréstimo: livro nao encontrado.");
            return;
        }

        try {
            emprestimo emprestimo = livraria.criaremprestimo(usuarioOpt.get(), livroOpt.get());
            System.out.println("Empréstimo criado: " + emprestimo);
        } catch (Exception e) {
            System.out.println("Falha ao criar empréstimo: " + e.getMessage());
        }
    }

    public void returnlivro(String isbn) {
        Optional<livro> livroOpt = livraria.acharlivroIsbn(isbn);
        if (livroOpt.isEmpty()) {
            System.out.println("Livro nao encontrado: " + isbn);
            return;
        }

        // Encontra o empréstimo ativo para este livro
        Optional<emprestimo> ativaremprestimo = livraria.getemprestimos().stream()
                .filter(l -> l.getlivro().getIsbn().equals(isbn) && l.getreturnData() == null)
                .findFirst();

        if (ativaremprestimo.isEmpty()) {
            System.out.println("Nenhum empréstimo ativo encontrado para o livro: " + isbn);
            return;
        }

        livraria.returnemprestimo(ativaremprestimo.get());
        System.out.println("Livro devolvido: " + ativaremprestimo.get().getlivro().gettitulo());
    }
}