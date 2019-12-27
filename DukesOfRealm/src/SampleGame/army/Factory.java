package SampleGame.army;

import java.util.Queue;

import javafx.util.Pair;

/**
 * Allows to produce a Soldier in a number of limited turns
 * @author thdupont
 *
 */
public class Factory {
	private Queue<Pair<Soldier, Integer>> training_queue;
	Soldier current;
	int nb_rounds, nb_to_train;
	
	
	/**
	 * Creates a new Factory to produce a new soldiers
	 */
	public Factory() {nb_rounds = 0; nb_to_train = 0;}
	
	/**
	 * Add a training order to the factory
	 * @param type The soldier you want to train
	 * @param quantity The quantity of this specific soldiers you want to train
	 */
	public void addTraining(Soldier type, int quantity) {
		training_queue.add(new Pair<Soldier, Integer> (type,quantity));
	}
	
	private void nextTraining() {
		Pair<Soldier, Integer> tmp = training_queue.remove();
		current = tmp.getKey();
		nb_to_train = tmp.getValue();		
		nb_rounds = current.getTime_prod();
	}
	
	/**
	 * Verifies if production of the soldier has finished.
	 * @return The produced soldier, null if nothing was trained.
	 */
	public Soldier update() {
		if(nb_to_train>0) {
			if(nb_rounds==0) {
				nb_to_train--;
				if(nb_to_train>0) {
					nb_rounds = current.getTime_prod();
					this.nextTraining();
				}
				return current.trainNew();
			}
		}
		return null;
	}	
}
