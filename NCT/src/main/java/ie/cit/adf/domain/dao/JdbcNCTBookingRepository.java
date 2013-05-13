package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.NCTBooking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

@Transactional
//@Secured("ROLE_USER")
public class JdbcNCTBookingRepository implements NCTBookingRepository{
	
	private JdbcTemplate jdbcTemplate;
	private NCTBookingMapper nctBookingMapper = new NCTBookingMapper();

	public JdbcNCTBookingRepository(DataSource dataSource){
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public NCTBooking findByBookingId(int nctBookingId) {
		return jdbcTemplate.queryForObject(
				"SELECT " +
				"NCTBOOKINGID, " +
				"NCTBOOKINGCUSTOMERFIRST, NCTBOOKINGCUSTOMERLAST, " +
				"NCTBOOKINGVEHICLEID, " +
				"NCTBOOKINGDATE, NCTBOOKINGLOCATION, NCTBOOKINGSTATUS, " +
				"VEHICLEID, VEHICLEMAKE, " +
				"VEHICLEMODEL, VEHICLEREGISTRATION " +
				"FROM NCTBOOKINGS, VEHICLES " +
				"WHERE NCTBOOKINGVEHICLEID = VEHICLEID AND " +
				"NCTBOOKINGID=?",
				nctBookingMapper, nctBookingId);
	}
	@Override
	public int findIdByVehicleId(String vehicleId) {

		NCTBooking nctBooking =  jdbcTemplate.queryForObject(
				"SELECT " +
				"NCTBOOKINGID, " +
				"NCTBOOKINGCUSTOMERFIRST, NCTBOOKINGCUSTOMERLAST, " +
				"NCTBOOKINGVEHICLEID, " +
				"NCTBOOKINGDATE, NCTBOOKINGLOCATION, NCTBOOKINGSTATUS, " +
				"VEHICLEID, VEHICLEMAKE, " +
				"VEHICLEMODEL, VEHICLEREGISTRATION " +
				"FROM NCTBOOKINGS, VEHICLES " +
				"WHERE NCTBOOKINGVEHICLEID = VEHICLEID AND " +
				"NCTBOOKINGVEHICLEID=?",
				nctBookingMapper, vehicleId);

		return nctBooking.getBookingId();
	}
	@Override
	public List<NCTBooking> getAllNCTBookings() {
		return jdbcTemplate.query(
				"SELECT " +
				"NCTBOOKINGID, " +
				"NCTBOOKINGCUSTOMERFIRST, NCTBOOKINGCUSTOMERLAST, " +
				"NCTBOOKINGVEHICLEID, " +
				"NCTBOOKINGDATE, NCTBOOKINGLOCATION, NCTBOOKINGSTATUS, " +
				"VEHICLEID, VEHICLEMAKE, " +
				"VEHICLEMODEL, VEHICLEREGISTRATION " +
				"FROM NCTBOOKINGS, VEHICLES WHERE " +
				"NCTBOOKINGVEHICLEID = VEHICLEID", 
				nctBookingMapper);
	}
	@Override
	public void addNCTBooking(NCTBooking nctBooking) {
		jdbcTemplate.update("" +
				"INSERT INTO NCTBOOKINGS (" +
				"NCTBOOKINGCUSTOMERFIRST,NCTBOOKINGCUSTOMERLAST," +
				"NCTBOOKINGVEHICLEID," +
				"NCTBOOKINGDATE,NCTBOOKINGLOCATION," +
				"NCTBOOKINGSTATUS) " +
				"VALUES(?,?,?,?,?,?)", 
				nctBooking.getCustomerFirst(), nctBooking.getCustomerLast(), 
				nctBooking.getVehicleId(),
				nctBooking.getDate(), nctBooking.getLocation(),  
				nctBooking.getStatus());
	}
	@Override
	public void deleteNCTBooking(int nctBookingId) {
		jdbcTemplate.update("DELETE FROM NCTBOOKINGS WHERE NCTBOOKINGID=?", nctBookingId);
	}
	@Override
	public void updateNCTBooking(NCTBooking nctBooking) {
		jdbcTemplate.update("UPDATE NCTBOOKINGS SET " +
				"NCTBOOKINGCUSTOMERFIRST=?," +
				"NCTBOOKINGCUSTOMERLAST=?," +
				"NCTBOOKINGVEHICLEID =?, " +
				"NCTBOOKINGDATE =?, " +
				"NCTBOOKINGLOCATION =?, " +
				"NCTBOOKINGSTATUS=? " +
				"WHERE " +
				"NCTBOOKINGID=?", 
				nctBooking.getCustomerFirst(), 
				nctBooking.getCustomerLast(), 
				nctBooking.getVehicleId(), 
				nctBooking.getDate(), 
				nctBooking.getLocation(), 
				nctBooking.getStatus(), 
				nctBooking.getBookingId());
	}

	class NCTBookingMapper implements RowMapper<NCTBooking>{
		@Override
		public NCTBooking mapRow(ResultSet rs, int rowNum) throws SQLException {
			NCTBooking nctBooking = new NCTBooking();
			nctBooking.setBookingId(rs.getInt("NCTBOOKINGID"));
			nctBooking.setCustomerFirst(rs.getString("NCTBOOKINGCUSTOMERFIRST"));
			nctBooking.setCustomerLast(rs.getString("NCTBOOKINGCUSTOMERLAST"));
			nctBooking.setVehicleId(rs.getString("VEHICLEID"));
			nctBooking.setRegistration(rs.getString("VEHICLEREGISTRATION"));
			nctBooking.setVehicleMake(rs.getString("VEHICLEMAKE"));
			nctBooking.setVehicleModel(rs.getString("VEHICLEMODEL"));
			nctBooking.setDate(rs.getString("NCTBOOKINGDATE"));
			nctBooking.setLocation(rs.getString("NCTBOOKINGLOCATION"));
			nctBooking.setStatus(rs.getString("NCTBOOKINGSTATUS"));			
			return nctBooking;
		}
	}
}
