package ie.cit.adf.services;

import ie.cit.adf.domain.NCTBooking;
import ie.cit.adf.domain.dao.NCTBookingRepository;
import ie.cit.adf.domain.dao.VehicleRepository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
public class NCTServiceImpl implements NCTService{

	private NCTBookingRepository nctBookings;
	
	public NCTServiceImpl(NCTBookingRepository nctBookings, VehicleRepository nctVehicles){
		this.nctBookings = nctBookings;
	}
	public List<NCTBooking> getAllBookings() {
		return nctBookings.getAllNCTBookings();
	}
	public int findBookingIdByVehicleId(String vehicleId){
		return nctBookings.findIdByVehicleId(vehicleId);
	}
	public NCTBooking createNewBooking(String text) {
		NCTBooking nctBooking = new NCTBooking();
		nctBooking.setRegistration(text);
		nctBooking.setCustomerFirst("Aine");
		nctBooking.setCustomerLast("Hurney");
		nctBooking.setLocation("Galway");
		nctBooking.setDate("14-Jun-2013");
		nctBooking.setStatus("Active");
		nctBookings.addNCTBooking(nctBooking);
		return nctBooking;
	}
	
	@Transactional
	public void closeBooking(int bookingId) {
		NCTBooking nctBooking = nctBookings.findByBookingId(bookingId);	
		if (nctBooking != null){										
			nctBooking.setStatus("Cancelled");
			nctBookings.updateNCTBooking(nctBooking);	
		}	
	}
	@Transactional
	public void openBooking(int bookingId) {
		NCTBooking nctBooking = nctBookings.findByBookingId(bookingId);		// Connection 1 needed Week 10 00:55:20
		if (nctBooking != null){											// we need Transactions support!!
			nctBooking.setStatus("Active");
			nctBookings.updateNCTBooking(nctBooking);		// Connection 2 needed Week 10 00:55:20 we need Transactions support!!					
		}				
	}
	@Override
	public NCTBooking createNewBooking(String customerFirstName,
			String customerLastName, String vehicleId, String date, String location) {
		NCTBooking nctBooking = new NCTBooking();
		nctBooking.setCustomerFirst(customerFirstName);
		nctBooking.setCustomerLast(customerLastName);
		nctBooking.setVehicleId(vehicleId);
		nctBooking.setDate(date);
		nctBooking.setLocation(location);
		nctBooking.setStatus("Active");
		nctBookings.addNCTBooking(nctBooking);
		return nctBooking;
		
	}
	@Override
	public NCTBooking get(int bookingId) {
		return nctBookings.findByBookingId(bookingId);
	}
	@Override
	public void delete(int bookingId) {
		nctBookings.deleteNCTBooking(bookingId);
	}
	@Override
	public NCTBooking createNewBooking(String customerFirstName,
			String customerLastName, String registration, String vehicleMake,
			String vehicleModel, String date, String location) {
		NCTBooking nctBooking = new NCTBooking();
		nctBooking.setCustomerFirst(customerFirstName);
		nctBooking.setCustomerLast(customerLastName);
		nctBooking.setRegistration(registration);
		nctBooking.setVehicleMake(vehicleMake);
		nctBooking.setVehicleModel(vehicleModel);
		nctBooking.setDate(date);
		nctBooking.setLocation(location);
		nctBooking.setStatus("Active");
		nctBookings.addNCTBooking(nctBooking);
		
		nctBooking.setBookingId(findBookingIdByVehicleId(nctBooking.getRegistration()));
		
		return nctBooking;
	}


}
