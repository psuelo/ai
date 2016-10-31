package de.rg.ai.shipit;

import de.rg.ai.shipit.model.Quote;

public interface IBookingListener {

	void quoteWasBooked(Quote.Key quoteKey);
}
