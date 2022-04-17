package model.civilization;

public class Person {

	private Job job;
	private Tile tile;//TODO: merge with parham
	private Currency currency;

	public Person(Tile tile) {
		this.tile = tile;
		// TODO - implement model.civilization.Person.model.civilization.Person
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param newTile
	 */
	public void changeTile(Tile newTile) {
		// TODO - implement model.civilization.Person.changeTile
		//TODO: update job
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param newJob
	 */
	public void changeJob(Job newJob) {
		// TODO - implement model.civilization.Person.changeJob
		//TODO: update currency
		throw new UnsupportedOperationException();
	}

}