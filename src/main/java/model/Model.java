/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import model.ModelColuna.Tipo;
/**
 *
 * @author alefss
 */
public abstract class Model {
    
    private final int cod;
    private final Date dataCriacao;
    private final Time horaCriacao;
    private final Date dataModificacao;
    private final Time horaModificacao;
    protected ArrayList<ModelColuna> colunas;
    
    public static final String COD = "COD";
    
    protected Model() {
        this.cod = 0;
        this.dataCriacao = null;
        this.horaCriacao = null;
        this.dataModificacao = null;
        this.horaModificacao = null;                
    }
    
    protected Model(int cod, Date dataCriacao, Time horaCriacao, Date dataModificacao, Time horaModificacao) {
        this.cod = cod;
        this.dataCriacao = dataCriacao;
        this.horaCriacao = horaCriacao;
        this.dataModificacao = dataModificacao;
        this.horaModificacao = horaModificacao;                
    }

    public int getCod() {
        return cod;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Time getHoraCriacao() {
        return horaCriacao;
    }

    public Date getDataModificacao() {
        return dataModificacao;
    }

    public Time getHoraModificacao() {
        return horaModificacao;
    }
    
    public ArrayList<ModelColuna> getModelColunas() {
        colunas = new ArrayList<>();
        setModelColunas();
        return colunas;
    }
    
    protected void putModelColuna(String nome, String valor, Tipo tipo) {
       this.colunas.add(new ModelColuna(nome, valor, tipo));
    }
    
    protected void putModelColuna(String nome, int valor, Tipo tipo) {
       this.colunas.add(new ModelColuna(nome, Integer.toString(valor), tipo));
    }
    
    abstract protected void setModelColunas(); 
    abstract public String getTableName();    
}
