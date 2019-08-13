/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.ArrayList;
import model.Model;
import model.ModelColuna;

/**
 *
 * @author alefss
 * @date 6 de jul de 2019
 */
public class CREATE {
    private final StringBuilder builder = new StringBuilder();
    
    public CREATE(Model model) {
        builder.append("CREATE TABLE IF NOT EXISTS ")
                .append(model.getTableName())
                .append(" ( ")
                .append(Model.COD)
                .append(" INT ")
                .append("PRIMARY KEY AUTO_INCREMENT");
        setColumns(model);
    }
    
    private void setColumns(Model model) {
        ArrayList<ModelColuna> colunas = model.getModelColunas();
        ModelColuna col;
        
        for (int i = 1; i < colunas.size(); i++) {
            col = colunas.get(i);
            builder.append(", ")
                    .append(col.getNome())
                    .append(" ")
                    .append(col.getTipo());
        }
        builder.append(")");
    }
    
    public String getString() {
        return builder.toString();
    }

}
