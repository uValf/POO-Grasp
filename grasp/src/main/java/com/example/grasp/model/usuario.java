package com.example.grasp.model;

/**
 * Modelo simples de usuário.
 * Alta coesao: armazena dados do usuário.
 * Baixo acoplamento: nao conhece detalhes do mecanismo de empréstimo.
 */
public class usuario {
    private final String id;
    private final String nome;

    public usuario(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getId() { return id; }
    public String getnome() { return nome; }

    @Override
    public String toString() {
        return String.format("usuário[id=%s, nome=%s]", id, nome);
    }
}