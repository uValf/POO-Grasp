package com.example.grasp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Classe livraria que mantém coleções de livros, usuários e empréstimos.
 *
 * Padrao GRASP aplicado: Creator
 * - Justificativa:
 *   Segundo o princípio Creator, uma classe B deve criar instâncias de A quando:
 *     - B contém A (agregaçao), ou
 *     - B agrega informações necessárias para criar A, ou
 *     - B está fortemente associado a A.
 *
 *   Aqui, livraria contém a coleçao de emprestimo (agrega), e tem acesso aos livro e usuario necessários.
 *   Portanto livraria é uma escolha natural para criar e gerenciar instâncias de emprestimo.
 *
 * Coesao e acoplamento:
 * - High Cohesion: livraria é responsável por gerenciar o estado da biblioteca (livros, usuários, empréstimos).
 * - Low Coupling: detalhes de como um emprestimo é usado ficam encapsulados; outras classes interagem via métodos bem definidos.
 */
public class livraria {
    private final Map<String, livro> livros = new HashMap<>();
    private final Map<String, usuario> usuarios = new HashMap<>();
    private final List<emprestimo> emprestimos = new ArrayList<>();

    public void addlivro(livro livro) {
        livros.put(livro.getIsbn(), livro);
    }

    public Optional<livro> acharlivroIsbn(String isbn) {
        return Optional.ofNullable(livros.get(isbn));
    }

    public void registrarusuario(usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
    }

    public Optional<usuario> acharusuarioId(String id) {
        return Optional.ofNullable(usuarios.get(id));
    }

    public List<emprestimo> getemprestimos() {
        return Collections.unmodifiableList(emprestimos);
    }

    /**
     * Método que cria um empréstimo (emprestimo) — aplicaçao direta do padrao Creator.
     *
     * Regras simples:
     * - Só empresta se livro disponível e usuário registrado.
     * - Ao criar o emprestimo, marca o livro como indisponível e adiciona à lista de emprestimos.
     */
    public emprestimo criaremprestimo(usuario usuario, livro livro) {
        if (!usuarios.containsKey(usuario.getId())) {
            throw new IllegalArgumentException("Usuário nao registrado: " + usuario.getId());
        }
        if (!livros.containsKey(livro.getIsbn())) {
            throw new IllegalArgumentException("Livro nao encontrado: " + livro.getIsbn());
        }
        if (!livro.estadisponivel()) {
            throw new IllegalStateException("Livro nao disponível: " + livro.gettitulo());
        }

        emprestimo emprestimo = new emprestimo(usuario, livro, LocalDate.now());
        livro.setdisponivel(false);
        emprestimos.add(emprestimo);
        return emprestimo;
    }

    /**
     * Registra a devoluçao de um empréstimo.
     */
    public void returnemprestimo(emprestimo emprestimo) {
        if (!emprestimos.contains(emprestimo)) {
            throw new IllegalArgumentException("Empréstimo nao encontrado");
        }
        emprestimo.setreturnData(LocalDate.now());
        emprestimo.getlivro().setdisponivel(true);
    }
}