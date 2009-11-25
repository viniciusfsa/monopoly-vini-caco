package Monopoly;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marcus
 */
public class Jogador {

    private int id = 0;

    public int getId() {
        return id;
    }


    public void addDinheiro(int dinheiro){
        this.dinheiro+=dinheiro;
    }

    public void retirarDinheiro(int dinheiro){
        this.addDinheiro(-dinheiro);
    }

    public void setId(int id) {
        this.id = id;
    }
    private String Nome = "";
    private String CorPeao = "";
    private int dinheiro=1500;
    
    private ArrayList<String> propriedades = new ArrayList<String>();

    public static void main(String[] args) {
        Jogador j = new Jogador("maria", "preto", 1);
        j.addPropriedade("casa boa");
        j.addPropriedade("casa boa2");

        ArrayList a = j.getPropriedades();
        Iterator i = a.iterator();
        while (i.hasNext())
            System.out.println(i.next());

    }

    public Jogador(String Nome, String CorPeao, int id) {
        this(Nome);
        this.setCorPeao(CorPeao);
    }

    public Jogador (String Nome){
        this.Nome = Nome;
    }

    


    public String getCorPeao() {
        return CorPeao;
    }

    public int getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(int dinheiro) {
        this.dinheiro = dinheiro;
    }

    public void setCorPeao(String CorPeao) {
        this.CorPeao = CorPeao;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public void addPropriedade(String nome){
        propriedades.add(nome);
        
    }

    public ArrayList getPropriedades(){
        return propriedades;
    }

    
  

}
