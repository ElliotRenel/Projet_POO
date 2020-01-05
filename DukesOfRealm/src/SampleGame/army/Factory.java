package SampleGame.army;

import java.util.LinkedList;
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
	public Factory() {
		nb_rounds = 0; 
		nb_to_train = 0; 
		training_queue = new LinkedList<Pair<Soldier, Integer>>(); 
		current = null;
	}
	
	/**
	 * Add a training order to the factory
	 * @param type The soldier you want to train
	 * @param quantity The quantity of this specific soldiers you want to train
	 */
	public void addTraining(Soldier type, int quantity) {
		training_queue.add(new Pair<Soldier, Integer> (type,quantity));
		if(current==null) {
			this.nextTraining();
		}
	}
	
	/**
	 * Update the current training when nb_to_train goes to 0
	 */
	private void nextTraining() {
		if(!training_queue.isEmpty()) {
			Pair<Soldier, Integer> tmp = training_queue.remove();
			current = tmp.getKey();
			nb_to_train = tmp.getValue();		
			nb_rounds = current.getTime_prod();
		}else
			current = null;
	}
	
	/**
	 * Reset the whole factory production
	 */
	public void resetFactory() {
		current = null;
		nb_rounds = 0;
		nb_to_train = 0;
		while(!training_queue.isEmpty())
			training_queue.remove();
	}
	
	/**
	 * Verifies if production of the soldier has finished.
	 * @return The produced soldier, null if nothing was trained.
	 */
	public Soldier update() {		
		if(current!=null) {
			nb_rounds--;
			if(nb_rounds==0) {
				nb_to_train--;
				Soldier new_soldier = current.trainNew();
				if(nb_to_train==0)
					this.nextTraining();
				else
					nb_rounds = current.getTime_prod();
				return new_soldier;
			}
		}
		return null;
	}	
	
	/**
	 * Print factory status, used for debugging 
	 */
	public void printStatus() {
		System.out.println("Current : "+current+"\n"
				+ "Tour restant : "+nb_rounds+"\n"
						+ "Troupe restant : "+nb_to_train);
	}
}
