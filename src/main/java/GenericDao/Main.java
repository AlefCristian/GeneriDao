/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GenericDao;

import dao.CREATE;
import dao.DaoUsuario;
import dao.Select;
import java.util.List;
import model.Usuario;

/**
 *
 * @author alef
 */
public class Main {

    static List<Usuario> usuarios;
    static Select select;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //deubom(testCreate());
        //deubom(testUpdate());
        //deubom(searchAll());
        //deubom(testSearch());
        //testeCreateString();
        createTable();
        System.out.println("Termiando o codigo");
    }
    
    public static void createTable() {
        new DaoUsuario();
    }

    public static void testeCreateString() {
        Usuario usuario = new Usuario("", "", "");

        System.out.println(new CREATE(usuario).getString());
    }

    public static void deubom(boolean result) {
        System.out.println("---------------------------Fim do teste");
        if (result) {
            System.out.println("---------------------------Deu bom o teste");
        } else {
            System.out.println("---------------------------Deu ruim o teste");
        }

        System.out.println("-------------------------------------");
    }

    public static boolean testCreate() {
        System.out.println("--------------------------------Testando o Insert");
		Usuario usuario = new Usuario("alefcr", "admin", "garcon");
		showUser(usuario);
		usuarios = new DaoUsuario().findAllData();
		return true;
    }

    public static boolean testUpdate() {
        return false;
    }

    public static boolean testSearch() {
       return false;

    }

    public static boolean searchAll() {
        usuarios = new DaoUsuario().findAllData();
        
        usuarios.forEach((model) -> {
            showUser(model);
        });
        return false;

    }

    public static void showUser(Usuario user) {
        System.out.println("id: " + user.getCod());
        System.out.println("login: " + user.getLogin());
        System.out.println("passowrd: " + user.getPassword());
        System.out.println("Nivel: " + user.getNivel());
        System.out.println("");
    }
}
