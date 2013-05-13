package ie.cit.adf.web;

import ie.cit.adf.domain.NCTBooking;
import ie.cit.adf.domain.NCTBookings;
import ie.cit.adf.domain.Vehicle;
import ie.cit.adf.services.NCTService;
import ie.cit.adf.services.VehicleServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

@Controller
@RequestMapping("api")		
public class NCTRestController {
	@Autowired
	private NCTService nctService;
	@Autowired
	private VehicleServiceImpl vehicleService;
	
	@RequestMapping(value = "nct", method = RequestMethod.GET)	
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody 
	public NCTBookings nctBookings(){
		return new NCTBookings(nctService.getAllBookings());
	}
	@RequestMapping(value = "nct/{bookingId}", method = RequestMethod.GET)	
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody 
	public NCTBooking nctBooking(@PathVariable int bookingId){
		NCTBooking nctBooking = nctService.get(bookingId);
		if (nctBooking == null){
			throw new NotFoundException();  
		}
		return nctBooking;
	}
	
	@RequestMapping(value = "nct", method = RequestMethod.POST)	
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void createBooking(@RequestParam String customerFirstName, @RequestParam String customerLastName, 
				@RequestParam String registration, @RequestParam String vehicleMake, @RequestParam String vehicleModel, 
				@RequestParam String date,@RequestParam String location, 
				HttpServletRequest req, HttpServletResponse resp){
		Vehicle vehicle = vehicleService.createNewVehicle(vehicleMake, vehicleModel, registration);
		NCTBooking nctBooking = nctService.createNewBooking(customerFirstName, customerLastName, vehicle.getVehicleId(), date, location);
		
		nctBooking.setBookingId(nctService.findBookingIdByVehicleId(vehicle.getVehicleId()));
		nctBooking.setVehicleMake(vehicle.getVehicleMake());
		nctBooking.setVehicleModel(vehicle.getVehicleModel());
		nctBooking.setRegistration(vehicle.getRegistration());
		StringBuffer url = req.getRequestURL().append("/{bookingId}");  
		UriTemplate uriTemplate = new UriTemplate(url.toString());
		resp.addHeader("location", uriTemplate.expand(nctBooking.getBookingId()).toASCIIString());
	}
	
	@RequestMapping(value = "nct/{bookingId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int bookingId) {
		nctService.delete(bookingId);
	}
	@RequestMapping(value = "nct/{bookingId}", method = RequestMethod.PUT)	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateBooking(@PathVariable int bookingId, @RequestBody NCTBooking nctBooking){  
		NCTBooking existingNCTBooking = nctService.get(bookingId);
		if (existingNCTBooking == null){
			throw new NotFoundException();  
		}
		existingNCTBooking.setCustomerFirst(nctBooking.getCustomerFirst());
		existingNCTBooking.setCustomerLast(nctBooking.getCustomerLast());
		existingNCTBooking.setRegistration(nctBooking.getRegistration());
		existingNCTBooking.setDate(nctBooking.getDate());
		existingNCTBooking.setLocation(nctBooking.getLocation());
		existingNCTBooking.setStatus(nctBooking.getStatus());
	}
	@ResponseStatus(HttpStatus.NOT_FOUND) 
	class NotFoundException extends RuntimeException{
		private static final long serialVersionUID = 1L;
	}
	
}
