package ie.cit.adf.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import ie.cit.adf.domain.NCTBooking;
import ie.cit.adf.domain.dao.NCTBookingRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class NCTServiceImplTest {

	private NCTServiceImpl tested;
	private NCTBookingRepository nctRepository;

	@Before
	public void setup() {
		nctRepository = Mockito.mock(NCTBookingRepository.class);
		tested = new NCTServiceImpl(nctRepository, null);
	}

	@Test
	public void testCreateNCTBooking() {
		NCTBooking createNewNCTBooking = tested.createNewBooking
				("Joe","Bloggs","13D3244","Ford","Mondeo","13-May-2013", "Athlone");
		Mockito.verify(nctRepository).addNCTBooking(createNewNCTBooking);
		assertThat(createNewNCTBooking.getLocation(), equalTo("Athlone"));
	}


	
	
}
