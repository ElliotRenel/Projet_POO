package SampleGame.army;

public class Factory {
	private int nb_round;
	private Soldier toTrain;
	
	public Factory(Soldier toTrain) {
		this.toTrain = toTrain;
		this.nb_round = toTrain.getTime_prod();
	}
	
	public int update() {
		if(nb_round==0) {
			nb_round = toTrain.getTime_prod();
			return 1;
		}
		nb_round--;
		return 0;
	}	
}
