package Monopoly;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Armazena os comandos possíveis do jogo
 * @author Marcus
 */
public class Comandos {


   
    /**
     * Lista dos comandos
     */
    static List<String> cmds = new ArrayList<String>();

    public static void main(String[] args) {
        Comandos c = new Comandos();
        System.out.println(c.getCmds());
         Iterator<String> it = cmds.iterator();
         while(it.hasNext())
            System.out.println(it.next());
    }


    /**
     * Construtor
     */
    public Comandos() {
        cmds.clear();
        initComandos();
    }


    /**
     * Inicializa a colecao de comandos
     */
    public void initComandos(){
        cmds.add("roll");
        cmds.add("status");
        cmds.add("quit");
    }

    
    /**
     * Obtem a lista de comandos
     * @return a lista de comandos
     */
    public List getCmds() {
        return cmds;
      

    }


    /**
     * Exibe os comandos
     */
    public void showComandos(){
        Iterator<String> a = cmds.iterator();
        while(a.hasNext())
            System.out.print("["+a.next()+"]");

        System.out.println("\n");
    }
}
