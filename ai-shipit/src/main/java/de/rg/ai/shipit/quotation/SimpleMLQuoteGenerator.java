package de.rg.ai.shipit.quotation;

import org.numenta.nupic.Parameters;
import org.numenta.nupic.Parameters.KEY;
import org.numenta.nupic.algorithms.Anomaly;
import org.numenta.nupic.algorithms.SpatialPooler;
import org.numenta.nupic.algorithms.TemporalMemory;
import org.numenta.nupic.datagen.ResourceLocator;
import org.numenta.nupic.examples.napi.hotgym.NetworkDemoHarness;
import org.numenta.nupic.network.Network;
import org.numenta.nupic.network.sensor.FileSensor;
import org.numenta.nupic.network.sensor.Sensor;
import org.numenta.nupic.network.sensor.SensorParams;
import org.numenta.nupic.network.sensor.SensorParams.Keys;

import de.rg.ai.shipit.IBookingListener;
import de.rg.ai.shipit.IQuotationGenerator;
import de.rg.ai.shipit.model.Quote;
import de.rg.ai.shipit.model.Quote.Key;
import de.rg.ai.shipit.model.Transport;

public class SimpleMLQuoteGenerator implements IQuotationGenerator, IBookingListener{

	@Override
	public void quoteWasBooked(Key quoteKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Quote getQuote(Transport transport) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void initializeNetwork(){
		Parameters p = NetworkDemoHarness.getParameters(); // "Default" test parameters (you will need to tweak)
		p = p.union(NetworkDemoHarness.getNetworkDemoTestEncoderParams()); // Combine "default" encoder parameters.

		Network network = Network.create("Network API Demo", p)         // Name the Network whatever you wish...
		    .add(Network.createRegion("Region 1")                       // Name the Region whatever you wish...
		        .add(Network.createLayer("Layer 2/3", p)                // Name the Layer whatever you wish...
		            .alterParameter(KEY.AUTO_CLASSIFY, Boolean.TRUE)    // (Optional) Add a CLAClassifier
		            .add(Anomaly.create())                              // (Optional) Add an Anomaly detector
		            .add(new TemporalMemory())                          // Core Component but also it's "optional"
		            .add(new SpatialPooler())                           // Core Component, but also "optional"
		            .add(Sensor.create(FileSensor::create, SensorParams.create(
		                Keys::path, "", ResourceLocator.path("rec-center-hourly.csv"))))));  // Sensors automatically connect to your source data, but you may omit this and pump data direction in!

		network.start();
	}

}
