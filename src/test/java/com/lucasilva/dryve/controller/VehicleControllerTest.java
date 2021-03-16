package com.lucasilva.dryve.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasilva.dryve.dto.VehicleDTO;
import com.lucasilva.dryve.dto.VehicleDetailsResponse;
import com.lucasilva.dryve.enums.ErroType;
import com.lucasilva.dryve.model.Brand;
import com.lucasilva.dryve.model.Model;
import com.lucasilva.dryve.model.Vehicle;
import com.lucasilva.dryve.service.MessagingService;
import com.lucasilva.dryve.service.VehicleService;
import com.lucasilva.dryve.service.exceptions.ObjectNotFoundException;

@SpringBootTest
@AutoConfigureMockMvc
public class VehicleControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private VehicleService vehicleService;
	
	@MockBean
	private MessagingService messagingService;

	@Test
	public void saveCategoriaWithSucess() throws Exception {
		Brand brand = new Brand();
		brand.setId("1L");
		brand.setName("FIAT");
		
		Model model = new Model("2L", "ECO-SPORT 1.6", brand);
		Vehicle vehicle = new Vehicle(
				8L, 
				"XYZ-1234", 
				45000.00,
				2020,
				56000.00, 
				new Date(), 
				model, 
				brand);
		
		VehicleDTO vehicleDTO = new VehicleDTO("XYZ-1234", "1L", "2L", 45000.00, 2020);
		
		doReturn(vehicle).when(vehicleService).saveVehicle(any(), any(String.class), any(String.class));
		doNothing().when(messagingService).sendVehicleMessage(any());
		
		this.mockMvc.perform(post("/dryve/vehicle")
				.content(asJsonString(vehicleDTO))
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id", is(8)))
			.andExpect(jsonPath("$.year", is(2020)));
	}
	
	@Test
	public void getVehicleByIdWithSucess() throws Exception {
		Brand brand = new Brand();
		brand.setId("1L");
		
		Model model = new Model("2L", "ECO-SPORT 1.6", brand);
		Vehicle vehicle = new Vehicle(
				8L, 
				"XYZ-1234", 
				45000.00,
				2020,
				56000.00, 
				new Date(), 
				model, 
				brand);
		
		doReturn(vehicle).when(vehicleService).getById(vehicle.getId());
		
		this.mockMvc.perform(get("/dryve/vehicle/8"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.board", is("XYZ-1234")));
	}
	
	@Test
	public void getVehicleByIdThrowException() throws Exception {
		doThrow(new ObjectNotFoundException(ErroType.VEHICLE_NOT_FOUND.toString()))
			.when(vehicleService).getById(222L);
		
		this.mockMvc.perform(get("/dryve/vehicle/222"))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void getPageByVehicleWithSucess() throws Exception {
		Brand brand = new Brand();
		brand.setId("1L");
		
		Model model1 = new Model("2L", "TORO 2.0", brand);
		Model model2 = new Model("3L", "ARGO 1.0", brand);
		Vehicle vehicle1 = new Vehicle(
				8L, 
				"XYZ-1234", 
				106000.00, 
				2020,
				56000.00, 
				new Date(), 
				model1, 
				brand);
		Vehicle vehicle2 = new Vehicle(
				9L, 
				"XYZ-1235", 
				47000.00, 
				2020,
				56000.00, 
				new Date(), 
				model2, 
				brand);
		
		PageRequest pageRequest = PageRequest.of(5, 24, Direction.valueOf("DESC"), "dateRegistrer");
		List<Vehicle> listVehicle = Lists.newArrayList(vehicle1, vehicle2);
		List<VehicleDetailsResponse> listVehicleDetails = new ArrayList<>();
		listVehicle.stream().forEach(v -> listVehicleDetails.add(new VehicleDetailsResponse(v)));
		Page<VehicleDetailsResponse> pageVehicle = new PageImpl<VehicleDetailsResponse>(listVehicleDetails, pageRequest, listVehicle.size());
		
		doReturn(pageVehicle).when(vehicleService).getVehicleByPage("", 5, 24, "dateRegistrer", "DESC");
		
		this.mockMvc.perform(get("/dryve/vehicle/page")
				.contentType(MediaType.APPLICATION_JSON)
				.param("board", "")
				.param("page", "5")
				.param("linesPerPage", "24")
				.param("orderBy", "dateRegistrer")
				.param("direction", "DESC"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.content[0].board", is("XYZ-1234")));
	}
	
	static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}