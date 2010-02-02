package openopoly.control.game;

import java.util.LinkedList;

/**Classe que representa o menu de comandos
 *
 * @author Lucas
 * @author Sergio
 */
public class GameOptions {

    LinkedList options;

    /**
     * O construtor da classe instancia o vetor das opções iniciais da lista de opções
     */
    public GameOptions() {
        options = new LinkedList();
        setBasicOptions();
    }

    /**
     * Configura o menu com as funcionalidades básicas
     */
    public void setBasicOptions() {
        options.clear();
        options.add("roll");
        options.add("status");
        options.add("quit");
    }

    //Comandos de manipulação da lista de comandos
    public void addPayOption() {
        options.addLast("pay");
    }

    public void removePayOption() {
        options.remove("pay");
    }

    public void addCard() {
        options.addFirst("card");
    }

    public void removeCardOption() {
        options.remove("card");
    }

    public void addBuildOption() {
        if(!options.contains("build")){
            options.addLast("build");
        }
    }

    public void removeBuildOption() {
        if(options.contains("build")){
            options.remove("build");
        }
    }
    public void addSellOption() {
        if(!options.contains("sell")){
            options.addLast("sell");
        }
    }

    public void removeSellOption() {
        if(options.contains("sell")){
            options.remove("sell");
        }
    }

    public void addMortgageOption() {
        options.addLast("mortgage");
    }

    public void removeMortgageOption() {
        options.remove("mortgage");
    }

    public void addUnMortgageOption() {
        options.addFirst("desipotecar");
    }

    public void removeUnMortgageOption() {
        options.remove("desipotecar");
    }

    /**
     * Esse método retorna as informações do vetor do menu
     * para uma String
     * @return as opções do menu
     */
    @Override
    public String toString() {
        String options = "{";
        if (this.options != null) {
            for (int i = 0; i < this.options.size(); i++) {
                options = options.concat((String) this.options.get(i) + ",");
            }
            options = options.substring(0, options.length()-1);
        }
        return options.concat("}");
    }

    /**
     * Verifica se há a opção escolhida no menu
     * @param menuOption a opção que deve ser encontrada
     * @return true caso a opção exista no menu, false caso contrário
     */
    public boolean isOption(String menuOption){
        return options.contains(menuOption);
    }
}
