package de.rg.ai.shipit.quotation;

import java.util.UUID;

import de.rg.ai.shipit.IBookingListener;
import de.rg.ai.shipit.IQuotationGenerator;
import de.rg.ai.shipit.model.Price;
import de.rg.ai.shipit.model.Quote;
import de.rg.ai.shipit.model.Quote.Key;
import de.rg.ai.shipit.model.Transport;

public class SimpleAdaptiveQuoteGenerator implements IQuotationGenerator, IBookingListener {

	private static final double INITIAL_PRICE = 100d;
	private static final double PRICE_ADAPTION_VALUE = 1d;

	private Quote currentQuote = new Quote(new Key(UUID.randomUUID().toString()), new Transport(), new Price(INITIAL_PRICE));
	
	private boolean quoteWasBooked = false;
	
	public Quote getQuote(Transport transport) {
		currentQuote = getNewQuote(transport);
		quoteWasBooked = false;
		return currentQuote;
	}

	private Quote getNewQuote(Transport transport) {
		Quote result;
		if (quoteWasBooked){
			result = currentQuote.newHigherQuote(PRICE_ADAPTION_VALUE);
		}
		else{
			result = currentQuote.newLowerQuote(PRICE_ADAPTION_VALUE);
		}
		return result;
	}

	public void quoteWasBooked(Key quoteKey) {
		quoteWasBooked = currentQuote.areYouThisQuote(quoteKey);
	}
}
