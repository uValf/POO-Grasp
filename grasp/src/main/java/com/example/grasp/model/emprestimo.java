package com.example.grasp.model;

import java.time.LocalDate;

/**
 * Representa um empréstimo de um livro para um usuário.
 * Coesao: responsável apenas pelos dados do empréstimo.
 *
 * Nota: A criaçao de emprestimo será encarregada pela classe livraria (Creator).
 */
public class emprestimo {
    private final usuario usuario;
    private final livro livro;
    private final LocalDate emprestimoData;
    private LocalDate returnData;

    public emprestimo(usuario usuario, livro livro, LocalDate emprestimoData) {
        this.usuario = usuario;
        this.livro = livro;
        this.emprestimoData = emprestimoData;
    }

    public usuario getusuario() { return usuario; }
    public livro getlivro() { return livro; }
    public LocalDate getemprestimoData() { return emprestimoData; }
    public LocalDate getreturnData() { return returnData; }

    public void setreturnData(LocalDate returnData) { this.returnData = returnData; }

    @Override
    public String toString() {
        return String.format("emprestimo[usuario=%s, livro=%s, emprestimoData=%s, returnData=%s]",
                usuario, livro.gettitulo(), emprestimoData, returnData);
    }
}