package SampleGame.army;

/**
 * Allows to produce a Soldier in a number of limited turns
 * @author thdupont
 *
 */
public class Factory {
	private int nb_round;
	private Soldier toTrain;
	
	/**
	 * Creates a new Factory to produce a new soldier
	 * @param toTrain The soldier to be trained
	 */
	public Factory(Soldier toTrain) {
		this.toTrain = toTrain;
		this.nb_round = toTrain.getTime_prod();
	}
	
	/**
	 * Verifies if production of the soldier has finished.
	 * @return 1 if production is finished, 0 else.
	 */
	public int update() {
		if(nb_round==0) {
			nb_round = toTrain.getTime_prod();
			return 1;
		}
		nb_round--;
		return 0;
	}	
}
