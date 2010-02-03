package openopoly.err;

public class HousesNotBuildableException extends GameException{
	
	public HousesNotBuildableException(){
		super("Not a buildable property.");
	}

}
