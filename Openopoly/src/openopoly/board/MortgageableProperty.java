package openopoly.board;

import openopoly.Player;


public abstract class MortgageableProperty implements Block{
	
	protected int posGBoard;
	protected String propName, group;
	protected Player owner;
	protected int price, mortgage;
	protected boolean mortgaged = false;

	
	

    //Getters and Setters
    public String getGroup() {
        return group;
    }

    public int getMortgage() {
        return mortgage;
    }

    public int getPrice() {
        return price;
    }
    
	public void setMortgaged(boolean mortgaged) {
		this.mortgaged = mortgaged;
	}
	

    public void setGroup(String group) {
       this.group = group;
    }

    public boolean isGoToJail() {
        return false;
    }

    public boolean isMortgageable() {
        return true;
    }

    public boolean isMortgaged() {
        return this.mortgaged;
    }

    
    public boolean isOwnerAPlayer(){
        return !this.owner.isBank();
    }
    

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public String getPropName() {
        return propName;
    }

    public int getPosGB() {
        return posGBoard;
    }
    
    public int getMortgagePrice(){
    	return this.mortgage;
    }
    
    public int getUnmortgagePrice(){
    	
    	int test = (int) (this.mortgage * 1.1);
    	if ((this.mortgage * 1.1)-test>=.5){
    		test++;
    	}
    	return test;
    }
}
