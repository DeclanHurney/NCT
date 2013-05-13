package ie.cit.adf.services;

import ie.cit.adf.domain.Vehicle;
import ie.cit.adf.domain.dao.VehicleRepository;

import java.util.List;

public class VehicleServiceImpl implements VehicleService {
	private VehicleRepository nctVehicles;
	
	public VehicleServiceImpl(VehicleRepository nctVehicles){
		this.nctVehicles = nctVehicles;
	}
	@Override
	public List<Vehicle> getAllVehicles() {
		return nctVehicles.getAll();
	}
	@Override
	public Vehicle createNewVehicle(String vehicleMake, String vehicleModel, String registration) {
		Vehicle vehicle = new Vehicle(vehicleMake, vehicleModel);
		vehicle.setRegistration(registration);
		nctVehicles.add(vehicle);
		return vehicle;
	}
	public VehicleRepository getNctVehicles() {
		return nctVehicles;
	}
}
