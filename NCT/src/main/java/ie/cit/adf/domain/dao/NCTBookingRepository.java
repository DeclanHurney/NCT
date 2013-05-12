package ie.cit.adf.domain.dao;

import java.util.List;

import ie.cit.adf.domain.NCTBooking;
public interface NCTBookingRepository {
	NCTBooking findByBookingId(int bookingId);
	List <NCTBooking> getAllNCTBookings();
	void addNCTBooking(NCTBooking nctBooking);
	void deleteNCTBooking(int bookingId);
	void updateNCTBooking(NCTBooking nctBooking);
	int findIdByVehicleId(String registration);
}
