/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.ModelColuna;

/**
 *
 * @author alefss
 * @date 27 de jun de 2019
 */
public class Select {

    private final StringBuilder builder = new StringBuilder();
    private int count = 0;
    private final ArrayList<ModelColuna> colunas = new ArrayList<>();

    public Select(String TABLE) {
        builder.append("SELECT * FROM ")
                .append(TABLE);
    }

    public Select where(ModelColuna coluna) {
        if (count == 0) {
            builder.append(" WHERE ")
                    .append(coluna.getNome())
                    .append(" = ? ");
        } else {
            builder.append(" AND ")
                    .append(coluna.getNome())
                    .append(" = ? ");
        }
        colunas.add(coluna);
        count++;
        return this;
    }

    public Select where(String coluna, String valor) {
        if (count == 0) {
            builder.append(" WHERE ")
                    .append(coluna)
                    .append(" = ? ");
        } else {
            builder.append(" AND ")
                    .append(coluna)
                    .append(" = ? ");
        }
        colunas.add(new ModelColuna(coluna, valor, null));
        count++;
        return this;

    }

    public String getQuerry() {
        return builder.toString();
    }

    public ArrayList<ModelColuna> getColunas() {
        return colunas;
    }

}
