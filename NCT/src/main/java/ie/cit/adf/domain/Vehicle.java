package ie.cit.adf.domain;

import java.util.UUID;

public class Vehicle {

	private String vehicleMake;
	private String vehicleModel;
	private String registration;
	private String vehicleId;

	public Vehicle(String vehicleMake, String vehicleModel) {
		this.vehicleMake = vehicleMake;
		this.vehicleModel = vehicleModel;
		vehicleId = UUID.randomUUID().toString();
	}
	
	public String getVehicleMake() {
		return vehicleMake;
	}

	public void setVehicleMake(String vehicleMake) {
		this.vehicleMake = vehicleMake;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleId() {
		return vehicleId;
	}

}
