package openopoly.control.game;

import java.util.LinkedList;

/**Classe que representa o menu de comandos
 *
 * @author Lucas
 * @author Sergio
 */
public class GameMenuOptions {

    LinkedList menu;

    /**
     * O construtor da classe instancia o vetor das opções iniciais do menu
     */
    public GameMenuOptions() {
        menu = new LinkedList();
        setBasicMenu();
    }

    /**
     * Configura o menu com as funcionalidades básicas
     */
    public void setBasicMenu() {
        menu.clear();
        menu.add("roll");
        menu.add("status");
        menu.add("quit");
    }

    //Comandos de manipulação da lista de comandos
    public void addPayOption() {
        menu.addLast("pay");
    }

    public void removePayOption() {
        menu.remove("pay");
    }

    public void addCard() {
        menu.addFirst("card");
    }

    public void removeCardOption() {
        menu.remove("card");
    }

    public void addBuildOption() {
        if(!menu.contains("build")){
            menu.addLast("build");
        }
    }

    public void removeBuildOption() {
        if(menu.contains("build")){
            menu.remove("build");
        }
    }
    public void addSellOption() {
        if(!menu.contains("sell")){
            menu.addLast("sell");
        }
    }

    public void removeSellOption() {
        if(menu.contains("sell")){
            menu.remove("sell");
        }
    }

    public void addMortgageOption() {
        menu.addFirst("hipotecar");
    }

    public void removeMortgageOption() {
        menu.remove("hipotecar");
    }

    public void addUnMortgageOption() {
        menu.addFirst("desipotecar");
    }

    public void removeUnMortgageOption() {
        menu.remove("desipotecar");
    }

    /**
     * Esse método retorna as informações do vetor do menu
     * para uma String
     * @return as opções do menu
     */
    public String getMenu() {
        String options = "{";
        if (menu != null) {
            for (int i = 0; i < menu.size(); i++) {
                options = options.concat((String) menu.get(i) + ",");
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
        return menu.contains(menuOption);
    }
}
