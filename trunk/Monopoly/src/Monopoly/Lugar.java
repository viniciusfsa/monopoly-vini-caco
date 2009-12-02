package Monopoly;

/**
 * Contem as informacoes de um lugar do tabuleiro
 * @author Marcus
 */
public class Lugar {


    /**
     * Nome do lugar
     */
    private String Nome="";

    /**
     * Preco de compra do lugar
     */
    private int Preco=0;

    /**
     * Nome do dono do lugar
     */
    private String dono = "bank";


    /**
     * Instancia um novo lugar
     * @param Posicao a posicao
     * @param Nome o nome
     * @param Preco o preco de compra
     */
    public Lugar(int Posicao, String Nome, int Preco) {
        this.Nome= Nome;        
        this.Preco=Preco;
    }


    /**
     * Obtem o nome do lugar
     * @return o nome
     */
    public String getNome() {
        return Nome;
    }

    /**
     * Define um nome para o lugar
     * @param Nome o nome do lugar
     */
    public void setNome(String Nome) {
        this.Nome = Nome;
    }

   

    
    /**
     * Obtem o preco de compra do lugar
     * @return o preco
     */
    public int getPreco() {
        return Preco;
    }



    /**
     * Define um preco para o lugar
     * @param Preco o preco
     */
    public void setPreco(int Preco) {
        this.Preco = Preco;
    }

    /**
     * Consulta o grupo de algum lugar
     * @param placeID o id do lugar
     * @return o nome do grupo
     */
    public String getGrupo(int placeID) {
        if(placeID==1||placeID==3){
            return "purple";}
        else if(placeID==6||placeID==8||placeID==9)
            return "light blue";
        else if(placeID==4||placeID==38)
            return "tax";
        else if(placeID==5||placeID==15||placeID==25||placeID==35)
            return "railroad";
        else if(placeID==7||placeID==22||placeID==36)
            return "chance";
        else if(placeID==10||placeID==20||placeID==30||placeID==40)
            return "corner";
        else if(placeID==11||placeID==13||placeID==14)
            return "pink";
        else if(placeID==12||placeID==28)
            return "utility";
        else if(placeID==16||placeID==18||placeID==19)
            return "orange";
        else if(placeID==21||placeID==23||placeID==24)
            return "red";
        else if(placeID==26||placeID==27||placeID==29)
            return "yellow";
        else if(placeID==31||placeID==32||placeID==34)
            return "green";
        else if(placeID==37||placeID==39)
            return "indigo";
        else
            return "chest";
    }


    /**
     * Obtem o preco de compra do lugar
     * @return
     */
    public int getPrecoCompra() {
        return this.getPreco();
    }

    
    /**
     * Obtem o nome do dono do lugar
     * @return nome do dono
     */
    public String getDono() {
        return dono;
    }


}
