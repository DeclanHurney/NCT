package ie.cit.adf.services;

import ie.cit.adf.domain.NCTBooking;

import java.util.ArrayList;
import java.util.List;

public class InMemoryNCTServiceImpl implements NCTService{

	private List<NCTBooking> nctBookings= new ArrayList<NCTBooking>();
	
	public InMemoryNCTServiceImpl(){
		NCTBooking nctBooking = new NCTBooking();
		nctBooking.setCustomerFirst("Jack");
		nctBooking.setCustomerLast("Jones");
		nctBooking.setRegistration("05C6000");
		nctBooking.setLocation("Cork");
		nctBooking.setDate("18-Jun-2013");
		nctBooking.setStatus("Active");
		nctBookings.add(nctBooking);
	}
	public List<NCTBooking> getAllBookings() {
		return nctBookings;
	}

	@Override
	public int findBookingIdByVehicleId(String registration) {
		for (NCTBooking nctBooking : nctBookings){
			if (nctBooking.getRegistration().equalsIgnoreCase(registration)){
				return nctBooking.getBookingId();
			}
		}
		
		return 0;
	}
	
	public void closeBooking(int bookingId) {
		for (NCTBooking nctBooking : nctBookings){
			if (nctBooking.getBookingId() == bookingId){
				nctBooking.setStatus("Cancelled");
			}
		}	
	}
	public void openBooking(int bookingId) {
		for (NCTBooking nctBooking : nctBookings){
			if (nctBooking.getBookingId() == bookingId){
				nctBooking.setStatus("Active");
			}
		}
	}
	@Override
	public NCTBooking get(int bookingId) {
		for (NCTBooking nctBooking : nctBookings){
			if (nctBooking.getBookingId() == bookingId){
				return nctBooking;
			}
		}
		return null;
	}
	@Override		
	public void delete(int bookingId) {
		for (NCTBooking nctBooking : nctBookings){
			if (nctBooking.getBookingId() == bookingId){
				nctBookings.remove(nctBooking);
				break;
			}
		}
	}
	@Override
	public NCTBooking createNewBooking(String customerFirstName,
			String customerLastName, String vehicleId, String date,
			String location) {
			NCTBooking nctBooking = new NCTBooking();
			nctBooking.setCustomerFirst(customerFirstName);
			nctBooking.setCustomerLast(customerLastName);
			nctBooking.setLocation(location);
			nctBooking.setDate(date);
			nctBooking.setStatus("Active");
			nctBookings.add(nctBooking);
			return nctBooking;
		}
	@Override
	public NCTBooking createNewBooking(String customerFirstName,
			String customerLastName, String registration, String vehicleMake,
			String vehicleModel, String date, String location) {
		return null;
	}
		
}
