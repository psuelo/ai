package de.rg.ai.shipit;

import java.util.List;

import de.rg.ai.shipit.model.Quote;

public class BookingService {

	private List<IBookingListener> bookingListeners;
	
	public void book(Quote.Key quoteKey){
		for (IBookingListener bookingListener : bookingListeners) {
			bookingListener.quoteWasBooked(quoteKey);
		}
	}
}
