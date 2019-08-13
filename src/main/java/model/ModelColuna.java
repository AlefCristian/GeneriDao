/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author alefss
 * @date 21 de jun de 2019
 */
public class ModelColuna {
    private final String nome;
    private final String valor;
    private final Tipo tipo;

    public ModelColuna(String nome, String valor, Tipo tipo) {
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getValor() {
        return valor;
    }
    
    public Tipo getTipo() {
        return tipo;
    }
    
    public enum Tipo {
        INT, TEXT, VARCHAR, DOUBLE, BOOLEAN, BYTE, TIME, DATE
    }
}
