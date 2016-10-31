package de.rg.ai.shipit.model;

public class Price {
	
	private double price;

	public Price(double price) {
		this.price = price;
	}

	public Price incrementBy(double priceIncrement){
		return new Price(price + priceIncrement);
	}

	public Price decrementBy(double priceDecrement){
		return new Price(price - priceDecrement);
	}
	
	@Override
	public String toString() {
		return String.valueOf(price);
	}
}
