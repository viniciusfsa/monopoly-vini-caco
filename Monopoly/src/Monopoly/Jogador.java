package Monopoly;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Contem as informacoes do Jogador
 * @author Marcus
 */
public class Jogador {

    /**
     * id do jogador
     */
    private int id = 0;

    /**
     * Nome do jogador
     */
    private String Nome = "";

    /**
     * cor do peao do jogador
     */
    private String CorPeao = "";

    /**
     * dinheiro inicial do jogador
     */
    private int dinheiro=1500;

    /**
     * Lista das posses do jogador
     */
    private ArrayList<String> propriedades = new ArrayList<String>();

    /**
     * Retorna o id do jogador
     * @return o id
     */
    public int getId() {
        return id;
    }


    /**
     * Adiciona dinheiro aa conta do jogador
     * @param dinheiro a ser adicionado
     */
    public void addDinheiro(int dinheiro){
        this.dinheiro+=dinheiro;
    }


    /**
     * Retira dinheiro da conta do jogador
     * @param dinheiro dinheiro a ser retirado
     */
    public void retirarDinheiro(int dinheiro){
        this.addDinheiro(-dinheiro);
    }


    /**
     * Define um id para o jogador
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }



    /**
     * Instancia um novo jogador
     * @param Nome nome do jogador
     * @param CorPeao cor do peao
     * @param id id
     */
    public Jogador(String Nome, String CorPeao, int id) {
        this(Nome);
        this.setCorPeao(CorPeao);
        this.id = id;
    }

    /**
     * Instancia um novo jogador
     * @param Nome nome do jogador
     */
    public Jogador (String Nome){
        this.Nome = Nome;
    }

    

    /**
     * Obtem a cor do peao do jogador
     * @return a cor do peao
     */
    public String getCorPeao() {
        return CorPeao;
    }


    /**
     * Obtem o dinheiro atual do jogador
     * @return dinheiro
     */
    public int getDinheiro() {
        return dinheiro;
    }


    /**
     * Seta um determinado valor em dinheiro para o jogador
     * @param dinheiro nova quantidade de dinheiro a ser definida
     */
    public void setDinheiro(int dinheiro) {
        this.dinheiro = dinheiro;
    }


    /**
     * Define a cor do peao do jogador
     * @param CorPeao a nova cor do peao
     */
    public void setCorPeao(String CorPeao) {
        this.CorPeao = CorPeao;
    }

    /**
     * Obtem o nome do jogador
     * @return o nome
     */
    public String getNome() {
        return Nome;
    }

    /**
     * Define um nome para o jogador
     * @param Nome o nome
     */
    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    /**
     * Adiciona uma propriedade à lista de posses do jogador
     * @param nome o nome da propriedade
     */
    public void addPropriedade(String nome){
        propriedades.add(nome);
        
    }

    /**
     * Obtem a lista de posses do Jogador
     * @return a lista de propriedades
     */
    public ArrayList getPropriedades(){
        return propriedades;
    }

    
  

}
