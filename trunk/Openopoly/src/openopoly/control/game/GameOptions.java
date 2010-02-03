package openopoly.control.game;

import java.util.LinkedList;

/**Classe que representa o menu de comandos
 *
 * @author Lucas
 * @author Sergio
 */
public class GameOptions {

    LinkedList<String> options;

    /**
     * O construtor da classe instancia o vetor das opções iniciais da lista de opções
     */
    public GameOptions() {
        options = new LinkedList<String>();
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
        if(!options.contains("mortgage")){
            options.addLast("mortgage");
        }
    }
    
    public void addUnmortgageOption() {
        if(!options.contains("unmortgage")){
            options.addLast("unmortgage");
        }
    }

    public void removeMortgageOption() {
        options.remove("mortgage");
    }
    public void removeUnmortgageOption() {
        options.remove("unmortgage");
    }

    /**
     * Esse método retorna as informações do vetor do menu
     * para uma String
     * @return as opções do menu
     */
    @Override
    public String toString() {
        String strOptions = "{";
        if (this.options != null) {
            for (int i = 0; i < this.options.size(); i++) {
                strOptions = strOptions.concat((String) this.options.get(i) + ",");
            }
            strOptions = strOptions.substring(0, strOptions.length()-1);
        }
        return strOptions.concat("}");
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
