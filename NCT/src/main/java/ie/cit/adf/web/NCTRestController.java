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
@RequestMapping("api")		//Annotates entire controller. Every method is prefixed.
//REST application interface. Various public services can access this	
public class NCTRestController {
	@Autowired
	private NCTService nctService;
	@Autowired
	private VehicleServiceImpl vehicleService;
	
	//A. START GET ALL BOOKINGS
	//curl -X GET -i http://localhost:8080/NCT/api/nct	
	@RequestMapping(value = "nct", method = RequestMethod.GET)	
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody //no view associated. Result to the desired format Week 6 1:15:40. //http://localhost:8080/NCT/
	public NCTBookings nctBookings(){
		return new NCTBookings(nctService.getAllBookings());
	/*before implementing this wrapper solution, JSON used the following code to return nctBookings....return nctService.getAllBookings();	
	 *Now because of requirement for XML representation, we need to a wrapper class called NCTBookings*/				
	}
	//A. END GET ALL BOOKINGS
	
	//B. START GET ONE BOOKING
	//curl -X GET -i http://localhost:8080/NCT/api/nct/{bookingId} - using bookingId of specific NCTBooking item Week 7 0:20:00
	//curl http://localhost:8080/NCT/api/nct/1 -H "Accept: application/json"
	@RequestMapping(value = "nct/{bookingId}", method = RequestMethod.GET)	
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody //no view associated. Result to the desired format Week 6 1:15:40. //http://localhost:8080/NCT/
	public NCTBooking nctBooking(@PathVariable int bookingId){
		NCTBooking nctBooking = nctService.get(bookingId);
		if (nctBooking == null){
			throw new NotFoundException();  //Implemented below. Week 7 0:20:40.
		}
		return nctBooking;
	}
	//B. END GET ONE BOOKING
	
	//C. START CREATE ONE BOOKING
	/*CREATE -- CREATE - - CREATE - - CREATE - - CREATE - - CREATE - - CREATE - - CREATE - - CREATE - - CREATE*/
	//curl -X POST -i http://localhost:8080/NCT/api/nct/text=NCTItem1}
	//curl --data "customerFirstName=Margaret&customerLastName=Murphy&registration=07G8000&vehicleMake=Peugeot&vehicleModel=406&date=21/07/13&location=Athlone" http://localhost:8080/NCT/api/nct
	@RequestMapping(value = "nct", method = RequestMethod.POST)	
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
//	public void createBooking(@RequestParam String customerFirstName, @RequestParam String customerLastName,
//			@RequestParam String vehicleId, @RequestParam String date, @RequestParam String location, HttpServletRequest req, HttpServletResponse resp){
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
		StringBuffer url = req.getRequestURL().append("/{bookingId}");  //req url is listed above within curl, append bookingId to it
		UriTemplate uriTemplate = new UriTemplate(url.toString());
		//here we are adding the URI of the new resource to the header - described in Week 6 10:00, 15:00
		resp.addHeader("location", uriTemplate.expand(nctBooking.getBookingId()).toASCIIString());
	}
	
	//C. END CREATE ONE BOOKING
	
	//D. START DELETE ONE BOOKING
	// curl -X DELETE -i http://localhost:8080/NCT/api/nct/{bookingId}
	//curl -X DELETE -i http://localhost:8080/NCT/api/nct/1
	@RequestMapping(value = "nct/{bookingId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int bookingId) {
		nctService.delete(bookingId);
	}
	//D. END DELETE ONE BOOKING
	
	//E. START UPDATE ONE BOOKING
	/*UPDATE -- UPDATE - - UPDATE - - UPDATE - - UPDATE - - UPDATE - - UPDATE - - UPDATE - - UPDATE - - UPDATE*/
	//curl -X PUT -i http://localhost:8080/NCT/api/nct/text=NCTItem1} - using bookingId of specific NCTBooking item Week 7 0:21:40
	@RequestMapping(value = "nct/{bookingId}", method = RequestMethod.PUT)	//Entire NCTBooking is now being accepted in either XML or JSON format Week 7 0:23:00
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateBooking(@PathVariable int bookingId, @RequestBody NCTBooking nctBooking){  //Week 7 0:23:30 input is automatically mapped to the required representation
		NCTBooking existingNCTBooking = nctService.get(bookingId);
		if (existingNCTBooking == null){
			throw new NotFoundException();  //Implemented below. Week 7 0:20:40.
		}
		existingNCTBooking.setCustomerFirst(nctBooking.getCustomerFirst());
		existingNCTBooking.setCustomerLast(nctBooking.getCustomerLast());
		existingNCTBooking.setRegistration(nctBooking.getRegistration());
		existingNCTBooking.setDate(nctBooking.getDate());
		existingNCTBooking.setLocation(nctBooking.getLocation());
		existingNCTBooking.setStatus(nctBooking.getStatus());
	}
	//E. END UPDATE ONE BOOKING
	
	//F. START EXCEPTION
	@ResponseStatus(HttpStatus.NOT_FOUND) //Week 7 0:20:40.
	class NotFoundException extends RuntimeException{
		private static final long serialVersionUID = 1L;
	}
	//F. END EXCEPTION
	
}
