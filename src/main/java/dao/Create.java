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
 * @date 4 de jul de 2019
 */
public class Create {
    private String sql;
    private ArrayList<ModelColuna> colunas;
    public Create(Model model) {
        sql = "CREATE TABLE IF NOT EXISTS" + model.getTableName() + " (";
        sql = sql.concat("");
    }

}
