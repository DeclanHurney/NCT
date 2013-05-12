package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.NCTBooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryNCTBookingRepository implements NCTBookingRepository{
	
	private Map<Integer, NCTBooking> inMemoryNCTBookings = new HashMap<Integer, NCTBooking>();
	
	@Override
	public NCTBooking findByBookingId(int bookingId) {
		return inMemoryNCTBookings.get(bookingId);
	}
	@Override
	public List<NCTBooking> getAllNCTBookings() {
		return new ArrayList<NCTBooking>(inMemoryNCTBookings.values());
	}
	@Override
	public void addNCTBooking(NCTBooking nctBooking) {
		inMemoryNCTBookings.put(nctBooking.getBookingId(), nctBooking);
	}
	@Override
	public void updateNCTBooking(NCTBooking nctBooking) {
		// TODO Auto-generated method stub
	}
	@Override
	public int findIdByVehicleId(String registration) {
		return 0;
	}
	@Override
	public void deleteNCTBooking(int bookingId) {
		inMemoryNCTBookings.remove(bookingId);		
	}
}
