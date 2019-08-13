package dao;

import dao.jdbc.JDBCMySQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Usuario;

public class DaoUsuario extends Dao<Usuario, JDBCMySQL> {

    public DaoUsuario() {
        super(new JDBCMySQL());
        createTable(new Usuario());
    }
    
    public List<Usuario> findAllData() {
        return findAll(new Usuario());
    }
    @Override
    protected Usuario getModel(ResultSet rs) throws SQLException {
        return new BuilderUsuario(rs.getInt(Usuario.COD), rs.getString(Usuario.LOGIN), rs.getString(Usuario.PASSWORD), rs.getString(Usuario.NIVEL));
    }

    protected static class BuilderUsuario extends Usuario {

        public BuilderUsuario(int cod, String login, String password, String nivel) {
            super(cod, login, password, nivel);
        }
    }
}
