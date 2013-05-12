package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

public class JdbcVehicleRepository implements VehicleRepository{
	
	private JdbcTemplate jdbcTemplate;
	private VehicleMapper vehicleMapper = new VehicleMapper();

	public JdbcVehicleRepository(DataSource dataSource){
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public Vehicle findById(String vehicleId) {
		return jdbcTemplate.queryForObject(
				"SELECT VEHICLEID, VEHICLEMAKE, VEHICLEMODEL, VEHICLEREGISTRATION FROM VEHICLES " +
				"WHERE VEHICLEID = ?", vehicleMapper, vehicleId);
	}
	@Override
	public List<Vehicle> getAll() {
		return jdbcTemplate.query(
				"SELECT * FROM VEHICLES", vehicleMapper);
	}

	@Override
	public void add(Vehicle vehicle) {
		jdbcTemplate.update("INSERT INTO VEHICLES (VEHICLEID, VEHICLEMAKE,VEHICLEMODEL,VEHICLEREGISTRATION) VALUES(?,?,?,?)", 
				vehicle.getVehicleId(), vehicle.getVehicleMake(), vehicle.getVehicleModel(), 
				vehicle.getRegistration());
	}
	class VehicleMapper implements RowMapper<Vehicle>{
		@Override
		public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
			Vehicle vehicle = new Vehicle(rs.getString("VEHICLEMAKE"), rs.getString("VEHICLEMODEL"));
			vehicle.setRegistration(rs.getString("VEHICLEREGISTRATION"));
			vehicle.setVehicleId(rs.getString("VEHICLEID"));
			return vehicle;
		}
	}

}
