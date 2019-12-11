package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ElementSub {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private final int id;
	private final String name;
	private final int atomicNumber;
	private final String symbol;
	private final String metalGroup;

	public ElementSub(int id, String name, int atomicNumber, String symbol, String metalGroup) {
		this.id = id;
		this.name = name;
		this.atomicNumber = atomicNumber;
		this.symbol = symbol;
		this.metalGroup = metalGroup;
	}

	public int getId(){
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public int getAtomicNumber() {
		return atomicNumber;
	}

	public String getMetalGroup() {
		return metalGroup;
	}
}
