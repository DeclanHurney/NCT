package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.Vehicle;

import java.util.List;

public interface VehicleRepository {
	List <Vehicle> getAll();
	void add(Vehicle vehicle);
	Vehicle findById(String vehicleId);
}
