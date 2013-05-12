package ie.cit.adf.web;

import ie.cit.adf.domain.NCTBooking;
import ie.cit.adf.domain.Vehicle;
import ie.cit.adf.services.NCTService;
import ie.cit.adf.services.VehicleServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class NCTController {
	@Autowired
	private NCTService nctService;
	@Autowired
	private VehicleServiceImpl vehicleService;

	@RequestMapping("index")
	public String index(Model model){
		model.addAttribute("nctBookings", nctService.getAllBookings());
		return "nctBookings.jsp";
	}
	
	
	@RequestMapping("create")
	public String create(@RequestParam String customerFirstName, @RequestParam String customerLastName, 
			@RequestParam String registration, @RequestParam String vehicleMake, @RequestParam String vehicleModel, 
			@RequestParam String date,@RequestParam String location, 
			Model model) {	
		Vehicle vehicle = vehicleService.createNewVehicle(vehicleMake, vehicleModel, registration);
		NCTBooking nctBooking = nctService.createNewBooking(customerFirstName, customerLastName, vehicle.getVehicleId(), date, location);
		nctBooking.setBookingId(nctService.findBookingIdByVehicleId(vehicle.getVehicleId()));
		nctBooking.setVehicleMake(vehicle.getVehicleMake());
		nctBooking.setVehicleModel(vehicle.getVehicleModel());
		nctBooking.setRegistration(vehicle.getRegistration());
		String thankYou = "Thank you. The following are details of your National Car Test.";
		model.addAttribute("thankYou", thankYou);
		model.addAttribute("nctSpecificBooking", nctBooking);
		model.addAttribute("nctBookings", nctService.getAllBookings());
		return "nctBookings.jsp";
	}
	
	@RequestMapping("close")
	public String close(@RequestParam int bookingId, Model model) {
		nctService.closeBooking(bookingId);
		model.addAttribute("nctBookings", nctService.getAllBookings());
		return "nctBookings.jsp";
	}
	
	@RequestMapping("open")
	public String open(@RequestParam int bookingId, Model model) {
		nctService.openBooking(bookingId);
		model.addAttribute("nctBookings", nctService.getAllBookings());
		return "nctBookings.jsp";
	}
	
}
