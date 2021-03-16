package com.lucasilva.dryve.service;

import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.lucasilva.dryve.dto.KBB;
import com.lucasilva.dryve.enums.ErroType;
import com.lucasilva.dryve.model.Brand;
import com.lucasilva.dryve.model.Model;
import com.lucasilva.dryve.model.ModelYear;
import com.lucasilva.dryve.model.Vehicle;
import com.lucasilva.dryve.repository.VehicleRepository;
import com.lucasilva.dryve.service.exceptions.ObjectNotFoundException;
import com.lucasilva.dryve.service.exceptions.RequestPriceKbbException;
import com.lucasilva.dryve.utils.RequestUtils;

@SpringBootTest
@AutoConfigureMockMvc
public class VehicleServiceTest {

	@Mock
	private VehicleRepository vehicleRepository;
	
	@Mock
	private ModelYearService modelYearService;
	
	@Mock
	private ModelService modelService;
	
	@Mock
	private BrandService brandService;
	
	@Mock
	private RequestUtils requestUtils;
	
	@InjectMocks
	private VehicleService vehicleService;
	
	@Test
	public void saveVehicleWithSucess() throws Exception {
		Brand brand = new Brand();
		brand.setId("1L");
		brand.setName("FIAT");
		
		Model model = new Model("2L", "ECO-SPORT 1.6", brand);
		ModelYear modelYear = new ModelYear("2", model, 2020, 1);
		KBB kbb = new KBB("1", 56000.00);
		
		Vehicle vehicle = new Vehicle(
				8L, 
				"XYZ-1234", 
				45000.00,
				2020,
				56000.00, 
				new Date(), 
				model, 
				brand);
		
		when(modelService.findById(vehicle.getModel().getId())).thenReturn(model);
		when(brandService.findById(vehicle.getBrand().getId())).thenReturn(brand);
		when(modelYearService.findByYear(vehicle.getYear())).thenReturn(Lists.newArrayList(modelYear));
		when(modelYearService.findByModelIdAndYear(
				vehicle.getModel().getId(), vehicle.getYear())).thenReturn(modelYear);
		when(requestUtils.getPriceKBB("1")).thenReturn(kbb);
		when(vehicleRepository.findById(vehicle.getId())).thenReturn(Optional.empty());
		
		Assertions.assertThatCode(() -> vehicleService.saveVehicle(
				vehicle, vehicle.getBrand().getId(), vehicle.getModel().getId())).doesNotThrowAnyException();
	}
	
	@Test
	public void saveVehicleButThrowExceptionBecauseBoardAlreadyExists() throws Exception {
		Brand brand = new Brand();
		brand.setId("1L");
		brand.setName("FIAT");
		
		Model model = new Model("2L", "ECO-SPORT 1.6", brand);
		Vehicle vehicle1 = new Vehicle(
				8L, 
				"XYZ-1234", 
				45000.00,
				2020,
				56000.00, 
				new Date(), 
				model, 
				brand);
		
		Vehicle vehicle2 = new Vehicle(
				9L, 
				"XYZ-1234", 
				45000.00,
				2020,
				56000.00, 
				new Date(), 
				model, 
				brand);
		
		when(vehicleRepository.saveAll(Lists.newArrayList(vehicle1, vehicle2))).thenReturn(Lists.newArrayList());
		when(vehicleRepository.findByBoard(vehicle1.getBoard())).thenReturn(vehicle1);
		
		Assertions.assertThatCode(() -> vehicleService.saveVehicle(
				vehicle2, vehicle2.getBrand().getId(), vehicle2.getModel().getId()))
		.isInstanceOf(RuntimeException.class).hasMessageContainingAll("DRV-0003");
	}
	
	@Test
	public void saveVehicleButThrowExceptionBecauseYearNotFoundInTables() throws Exception {
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
		
		when(modelService.findById(vehicle.getModel().getId())).thenReturn(model);
		when(brandService.findById(vehicle.getBrand().getId())).thenReturn(brand);
		when(modelYearService.findByYear(vehicle.getYear())).thenReturn(Lists.emptyList());
		
		Assertions.assertThatCode(() -> vehicleService.saveVehicle(
				vehicle, vehicle.getBrand().getId(), vehicle.getModel().getId()))
		.isInstanceOf(RuntimeException.class).hasMessageContainingAll("DRV-0002");
	}
	
	@Test
	public void saveVehicleButThrowExceptionBecauseModelYearNotFound() throws Exception {
		Brand brand = new Brand();
		brand.setId("1L");
		brand.setName("FIAT");
		
		Model model = new Model("2L", "ECO-SPORT 1.6", brand);
		ModelYear modelYear = new ModelYear("2", model, 2020, 1);
		
		Vehicle vehicle = new Vehicle(
				8L, 
				"XYZ-1234", 
				45000.00,
				2020,
				56000.00, 
				new Date(), 
				model, 
				brand);
		
		when(modelService.findById(vehicle.getModel().getId())).thenReturn(model);
		when(brandService.findById(vehicle.getBrand().getId())).thenReturn(brand);
		when(modelYearService.findByYear(vehicle.getYear())).thenReturn(Lists.newArrayList(modelYear));
		when(modelYearService.findByModelIdAndYear(
				vehicle.getModel().getId(), vehicle.getYear()))
		.thenThrow(new ObjectNotFoundException(ErroType.MODEL_YEAR_NOT_FOUND.toString()));
		
		Assertions.assertThatCode(() -> vehicleService.saveVehicle(
				vehicle, vehicle.getBrand().getId(), vehicle.getModel().getId()))
		.isInstanceOf(RuntimeException.class).hasMessageContainingAll("DRV-0007");
	}
	
	@Test
	public void saveVehicleButThrowExceptionInRequestPriceKbb() throws Exception {
		Brand brand = new Brand();
		brand.setId("1L");
		brand.setName("FIAT");
		
		Model model = new Model("2L", "ECO-SPORT 1.6", brand);
		ModelYear modelYear = new ModelYear("2", model, 2020, 1);
		
		Vehicle vehicle = new Vehicle(
				8L, 
				"XYZ-1234", 
				45000.00,
				2020,
				56000.00, 
				new Date(), 
				model, 
				brand);
		
		when(modelService.findById(vehicle.getModel().getId())).thenReturn(model);
		when(brandService.findById(vehicle.getBrand().getId())).thenReturn(brand);
		when(modelYearService.findByYear(vehicle.getYear())).thenReturn(Lists.newArrayList(modelYear));
		when(modelYearService.findByModelIdAndYear(
				vehicle.getModel().getId(), vehicle.getYear())).thenReturn(modelYear);
		when(requestUtils.getPriceKBB("1")).thenThrow(
				new RequestPriceKbbException(ErroType.ERROR_CONSULT_PRICE_KBB.toString()));
		
		Assertions.assertThatCode(() -> vehicleService.saveVehicle(
				vehicle, vehicle.getBrand().getId(), vehicle.getModel().getId()))
		.isInstanceOf(RuntimeException.class).hasMessageContainingAll("DRV-0001");
	}
}