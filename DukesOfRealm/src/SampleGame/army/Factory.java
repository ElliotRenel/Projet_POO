package SampleGame.army;

import java.util.LinkedList;
import java.util.Queue;

import SampleGame.army.Soldier.SoldierType;
import SampleGame.tiles.Castle;
import javafx.util.Pair;

/**
 * Allows to produce a Soldier in a number of limited turns
 * @author thdupont
 *
 */
public class Factory {
	
	public enum ProductionType{
		U,
		S;
	}
	
	private Queue<Pair<Soldier, Integer>> training_queue;
	private Soldier current;
	private Castle owner_castle;
	int nb_rounds, nb_to_train;
	public boolean upgrade;
	
	
	/**
	 * Creates a new Factory to produce new soldier
	 */
	public Factory() {
		nb_rounds = 0; 
		nb_to_train = 0; 
		training_queue = new LinkedList<Pair<Soldier, Integer>>(); 
		current = null;
	}
	
	public void setCastle(Castle castle) {
		owner_castle=castle;
	}
	
	/**
	 * Add a training order to the factory
	 * @param type The soldier you want to train
	 * @param quantity The quantity of this specific soldiers you want to train
	 */
	public void addTraining(Soldier type, int quantity) {
		training_queue.add(new Pair<Soldier, Integer> (type,quantity));
		if(current==null && !upgrade) {
			this.nextTraining();
		}
	}
	
	/**
	 * Adds an upgrade order to the factory
	 */
	public void upgradeCastle() {
		training_queue.add(null);
		if(current==null && !upgrade) {
			this.nextTraining();
		}
	}
	
	/**
	 * Update the current training when nb_to_train goes to 0
	 */
	private void nextTraining() {
		if(!training_queue.isEmpty()) {
			Pair<Soldier, Integer> tmp = training_queue.remove();
			if(tmp!=null) {
				current = tmp.getKey();
				nb_to_train = tmp.getValue();		
				nb_rounds = current.getTime_prod();
			}else {
				current = null;
				nb_to_train = 1;
				nb_rounds = 100+50*(owner_castle.getLevel()+1);
				upgrade = true;
			}
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
	public Pair<ProductionType,SoldierType> update() {		
		if(current!=null) {
			nb_rounds--;
			if(nb_rounds==0) {
				nb_to_train--;
				Pair<ProductionType,SoldierType> result = new Pair<ProductionType,SoldierType>(ProductionType.S,current.getType());
				if(nb_to_train==0)
					this.nextTraining();
				else
					nb_rounds = current.getTime_prod();
				return result;
			}
		}else if(upgrade){
			nb_rounds--;
			if(nb_rounds==0) {
				nb_to_train--;
				this.nextTraining();
				upgrade = false;
				return new Pair<ProductionType,SoldierType>(ProductionType.U,null);
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
