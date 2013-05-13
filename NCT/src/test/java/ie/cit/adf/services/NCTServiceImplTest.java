package ie.cit.adf.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import ie.cit.adf.domain.NCTBooking;
import ie.cit.adf.domain.Vehicle;
import ie.cit.adf.domain.dao.NCTBookingRepository;
import ie.cit.adf.domain.dao.VehicleRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class NCTServiceImplTest {

	private NCTBookingRepository nctBookingRepository;
	private NCTServiceImpl nctServiceImpl;
	private VehicleServiceImpl vehicleServiceImpl;
	private VehicleRepository vehicleRepository;
	
	@Before
	public void setup(){
		nctBookingRepository = Mockito.mock(NCTBookingRepository.class);
		vehicleRepository = Mockito.mock(VehicleRepository.class);
		nctServiceImpl = new NCTServiceImpl(nctBookingRepository, vehicleRepository);
		vehicleServiceImpl = new VehicleServiceImpl(vehicleRepository);
	}
	

	@Test
	public void testCreateNCTBooking() {
		NCTBooking createNewNCTBooking = nctServiceImpl.createNewBooking
				("Joe","Bloggs","13D3244","Ford","Mondeo","13-May-2013", "Athlone");
		Mockito.verify(nctBookingRepository).addNCTBooking(createNewNCTBooking);
		assertThat(createNewNCTBooking.getLocation(), equalTo("Athlone"));
	}

	@Test
	public void testCreateVehicle() {
		Vehicle createNewVehicle = vehicleServiceImpl.createNewVehicle("Volkswagen", "beetle", "66D5678");
		Mockito.verify(vehicleRepository).add(createNewVehicle);
		assertThat(createNewVehicle.getRegistration(), equalTo("66D5678"));
	}
	
	
}
