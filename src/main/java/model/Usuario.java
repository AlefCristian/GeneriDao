package model;

import model.ModelColuna.Tipo;

/**
 * Created by alef on 19/11/2016.
 */
public class Usuario extends Model {    
    //Colunas da tabela
    public static final String TABLE_NAME = "USUARIO";
    public static final String LOGIN = "LOGIN";
    public static final String PASSWORD = "PASSWORD";
    public static final String NIVEL = "NIVEL";    
    
    //Atributos
    private final String login;
    private final String password;
    private String nivel;
    
    public Usuario() {
        this.login = null;
        this.password = null;
    }

    public Usuario(String login, String password, String nivel) {
        super();
        this.login = login;
        this.password = password;
        this.nivel = nivel;       
    }

    protected Usuario(int cod, String login, String password, String nivel) {
        super(cod, null, null, null, null);
        this.login = login;
        this.password = password;
        this.nivel = nivel;       
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }
    
    public Usuario setNivel(String nivel) {
        this.nivel = nivel;
        return this;
    }

    public String getNivel() {
        return this.nivel;
    }

    @Override
    public void setModelColunas() {
        putModelColuna(COD, getCod(), Tipo.INT);
        putModelColuna(LOGIN, getLogin(), Tipo.TEXT);
        putModelColuna(PASSWORD, getPassword(), Tipo.TEXT);
        putModelColuna(NIVEL, getNivel(), Tipo.TEXT);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
