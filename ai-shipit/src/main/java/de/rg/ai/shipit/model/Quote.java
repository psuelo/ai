package de.rg.ai.shipit.model;

import java.util.UUID;

public class Quote {

	public static class Key {

		private String key;

		public Key(String key) {
			assert (key != null && !key.isEmpty());
			this.key = key;
		}

		@Override
		public String toString() {
			return key;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Key other = (Key) obj;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			return true;
		}
	}

	private final Key key;
	private final Transport transport;
	private final Price price;

	public Quote(Key key, Transport transport, Price price) {
		this.key = key;
		this.transport = transport;
		this.price = price;
	}
	
	public Quote newHigherQuote(double priceIncrement){
		return new Quote(new Key(UUID.randomUUID().toString()), this.transport, this.price.incrementBy(priceIncrement));
	}
	public Quote newLowerQuote(double priceDecrement){
		return new Quote(new Key(UUID.randomUUID().toString()), this.transport, this.price.decrementBy(priceDecrement));
	}

	public boolean areYouThisQuote(Quote.Key quoteKey){
		return this.key.equals(quoteKey);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quote other = (Quote) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
}
