package ie.cit.adf.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name= "nct")
public class NCTBooking {
	private String registration;
	private String date;
	private String location;
	private String customerFirst;
	private String customerLast;
	private String status;
	private int bookingId;
	private String vehicleId;
	private String vehicleMake;
	private String vehicleModel;
	private int bookingReservation;
	
	public NCTBooking() {
	}
	
	public String getRegistration() {
		return registration;
	}
	public void setRegistration(String registration) {
		this.registration = registration;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCustomerFirst() {
		return customerFirst;
	}
	public void setCustomerFirst(String customerFirst) {
		this.customerFirst = customerFirst;
	}
	public String getCustomerLast() {
		return customerLast;
	}
	public void setCustomerLast(String customerLast) {
		this.customerLast = customerLast;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleMake(String vehicleMake) {
		this.vehicleMake = vehicleMake;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getVehicleMake() {
		return vehicleMake;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public int getBookingReservation() {
		return bookingReservation;
	}

	public void setBookingReservation(int bookingReservation) {
		this.bookingReservation = bookingReservation;
	}
}
