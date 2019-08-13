package dao;

import dao.jdbc.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Model;
import model.ModelColuna;

public abstract class Dao<m, j extends JDBC> {

    protected String sql;
    protected Connection con;
    protected PreparedStatement ps;
    private int rowPs;
    private ArrayList<ModelColuna> colunas;
    private final JDBC jdbc;
    private Model model;

    public Dao(JDBC jdbc) {
        this.jdbc = jdbc;
    }
    
    protected abstract m getModel(ResultSet rs) throws SQLException;

    public int save(Model model) throws SQLException {
        this.colunas = model.getModelColunas();
        this.model = model;
        if (model.getCod() == 0) {
            sql = createInsertScript();
            return executeInsert();
        } else {
            sql = createUpdadeScript();
            executeUpdate();
            return model.getCod();
        }
    }

    protected ResultSet select(Select select) throws SQLException {
        sql = select.getQuerry();
        colunas = select.getColunas();
        ResultSet rs = executeSearch();
        return rs;
    }

    private String createInsertScript() {
        colunas = model.getModelColunas();
        ModelColuna col = colunas.get(1);
        
        StringBuilder builderSQL = new StringBuilder();
        builderSQL.append("INSERT INTO ")
                .append(model.getTableName());
        
        StringBuilder builderColunas = new StringBuilder();
        builderColunas.append(" (")
                .append(col.getNome());
        
        StringBuilder builderValores = new StringBuilder();
        builderValores.append(" ( ?");
        
        for (int i = 2; i < colunas.size(); i++) {
            col = colunas.get(i);
            builderColunas.append(", ")
                    .append(col.getNome());
            
            builderValores.append(", ?");
        }
        
         builderSQL.append(builderColunas)
                .append(") VALUES ")
                .append(builderValores)
                .append(")");
         return builderSQL.toString();        
    }

    private String createUpdadeScript() {
        ModelColuna col = colunas.get(1);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(model.getTableName())
                .append(" SET ")
                .append(col.getNome())
                .append(" = ?");

        for (int i = 2; i < colunas.size(); i++) {
            col = colunas.get(i);
            builder.append(", ")
                    .append(col.getNome())
                    .append(" = ?");
        }
        
        builder.append(" WHERE ")
                .append(Model.COD)
                .append(" = ?");

        return builder.toString();
    }

    private int executeInsert() throws SQLException {
        if (openConnection()) {
            setPsInsertUpdate();
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }
            closeConnection();
        }

        return 0;
    }

    private ResultSet executeSearch() throws SQLException {
        if (openConnection()) {
            setPsSelect();
            return ps.executeQuery();
        }
        return null;
    }

    private void executeUpdate() throws SQLException {
        if (openConnection()) {
            System.out.println(rowPs);
            setPsInsertUpdate();
            setPs(colunas.get(0).getValor());
            ps.executeUpdate();
        }
    }

    private boolean openConnection() {
        try {
            con = jdbc.getConnection();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            rowPs = 1;
        }
    }
    
    protected void createTable(Model model) {
        sql = new CREATE(model).getString();
        System.out.println(sql);
        if(openConnection()) {
            try {
                ps.execute();
            } catch (SQLException ex) {
                Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    protected List<m> findAll(Model model) {
        sql = new Select(model.getTableName()).getQuerry();
        System.out.println(sql);
        if(openConnection()) {
            try {
                ResultSet rs = ps.executeQuery();
                return getList(rs);
            } catch (SQLException ex) {
                Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Houve um probleminha de leve");
            }
        }
        return null;
    }
    
    private List<m> getList(ResultSet rs) {
        List<m> list = new ArrayList<>();
        try {                        
            while(rs.next()) {
                list.add(getModel(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }    

    protected void closeConnection() {
        try {
            ps.close();
            con.close();
        } catch (SQLException e) {
            
        }
    }

    private void setPsInsertUpdate() throws SQLException {
        for (int i = 1; i < colunas.size(); i++) {
            setPs(colunas.get(i).getValor());
        }
    }

    private void setPsSelect() throws SQLException {
        for (int i = 0; i < colunas.size(); i++) {
            setPs(colunas.get(i).getValor());
        }
    }

    private void setPs(String parameter) throws SQLException {
        ps.setString(rowPs++, parameter);
    }   

}
