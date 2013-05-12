package ie.cit.adf.services;

import ie.cit.adf.domain.Vehicle;

import java.util.List;

public interface VehicleService {
	List<Vehicle> getAllVehicles();
	Vehicle createNewVehicle(String vehicleMake, String vehicleModel, String registration);
}
