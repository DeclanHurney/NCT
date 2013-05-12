package ie.cit.adf.domain;

import java.util.ArrayList;
import java.util.List;

public class NCTBookings {

	private List<NCTBooking> nctBookings = new ArrayList<NCTBooking>();

	public NCTBookings() {
	}
	
	public NCTBookings(List<NCTBooking> nctBookings) {
		super();
		this.nctBookings  = nctBookings;
	}

}
