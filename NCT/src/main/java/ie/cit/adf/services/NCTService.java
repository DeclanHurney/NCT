package ie.cit.adf.services;

import ie.cit.adf.domain.NCTBooking;

import java.util.List;
public interface NCTService {

	List<NCTBooking> getAllBookings();

	NCTBooking createNewBooking(String customerFirstName, String customerLastName, String registration, String vehicleMake, 
			String vehicleModel, String date, String location);

	NCTBooking createNewBooking(String customerFirstName, String customerLastName,
			String vehicleId, String date, String location);

	NCTBooking get(int bookingId);

	void delete(int bookingId);

	void closeBooking(int bookingId);

	void openBooking(int bookingId);
	int findBookingIdByVehicleId(String vehicleId);
}
