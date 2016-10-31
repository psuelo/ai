package de.rg.ai.shipit;

import de.rg.ai.shipit.model.Quote;
import de.rg.ai.shipit.model.Transport;

public interface IQuotationGenerator {
	Quote getQuote(Transport transport);
}
