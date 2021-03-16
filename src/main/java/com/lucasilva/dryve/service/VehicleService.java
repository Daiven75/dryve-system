package com.lucasilva.dryve.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucasilva.dryve.dto.VehiclePageResponse;
import com.lucasilva.dryve.enums.ErroType;
import com.lucasilva.dryve.model.ModelYear;
import com.lucasilva.dryve.model.Vehicle;
import com.lucasilva.dryve.repository.VehicleRepository;
import com.lucasilva.dryve.service.exceptions.BoardAlreadyExistsException;
import com.lucasilva.dryve.service.exceptions.ObjectNotFoundException;
import com.lucasilva.dryve.service.exceptions.YearNotFoundException;
import com.lucasilva.dryve.utils.RequestUtils;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private ModelService modelService;
	
	@Autowired
	private ModelYearService modelYearService;
	
	@Autowired
	private RequestUtils requestUtils;
	
	@Transactional
	public Vehicle saveVehicle(Vehicle vehicle, String brandId, String modelId) {
		Vehicle board = vehicleRepository.findByBoard(vehicle.getBoard());
		if(board != null) {
			throw new BoardAlreadyExistsException(ErroType.BOARD_ALREADY_EXISTS.toString());
		}
		vehicle.setBrand(brandService.findById(brandId));
		vehicle.setModel(modelService.findById(modelId));
		List<ModelYear> listModelYear = modelYearService.findByYear(vehicle.getYear());
		if(listModelYear.isEmpty()) {
			throw new YearNotFoundException(ErroType.YEAR_NOT_FOUND.toString());
		}
		
		Integer kbbId = modelYearService.findByModelIdAndYear(modelId, vehicle.getYear()).getKbbId();
		vehicle.setPriceKBB(requestUtils.getPriceKBB(kbbId.toString()).getPrice());
		
		return vehicleRepository.save(vehicle);
	}

	public Page<VehiclePageResponse> getVehicleByPage(
			String board, 
			Integer page, 
			Integer linesPerPage, 
			String orderBy,
			String direction) {

		PageRequest pageRequest = PageRequest.of(
				page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Vehicle> listVehicle = vehicleRepository.findByBoardContaining(board, pageRequest).getContent();
		List<VehiclePageResponse> listVehicleResponse = new ArrayList<>();
		listVehicle.stream().forEach(v -> listVehicleResponse.add(new VehiclePageResponse(v)));
		
		Page<VehiclePageResponse> pageable = new PageImpl<VehiclePageResponse>(
				listVehicleResponse, 
				pageRequest, 
				listVehicleResponse.size());
		return pageable;
	}
	
	public Vehicle getById(Long id) {
		Optional<Vehicle> vehicle = vehicleRepository.findById(id);
		return vehicle.orElseThrow(
				() -> new ObjectNotFoundException(ErroType.VEHICLE_NOT_FOUND.toString()));
	}
}