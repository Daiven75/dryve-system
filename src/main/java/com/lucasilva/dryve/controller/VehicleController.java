package com.lucasilva.dryve.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucasilva.dryve.dto.VehicleDTO;
import com.lucasilva.dryve.dto.VehicleDetailsResponse;
import com.lucasilva.dryve.dto.VehiclePageResponse;
import com.lucasilva.dryve.model.Vehicle;
import com.lucasilva.dryve.service.MessagingService;
import com.lucasilva.dryve.service.VehicleService;
import com.lucasilva.dryve.utils.LogUtils;

@RestController
@RequestMapping("/dryve/vehicle")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private MessagingService messagingService;
	
	@Autowired
	private LogUtils log;
	
	@PostMapping
	public ResponseEntity<Vehicle> saveVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) {
		Vehicle vehicle = vehicleService.saveVehicle(
				new Vehicle(vehicleDTO), vehicleDTO.getBrandId(), vehicleDTO.getModelId());
		messagingService.sendVehicleMessage(vehicle);
		log.logRequisicao("POST", "/dryve/vehicle", vehicle.toString(), this.getClass().getName(), "201");
		return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<VehiclePageResponse>> getVehicleByPage(
			@RequestParam(value = "board", defaultValue = "") String board,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "dateRegistrer") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		
		Page<VehiclePageResponse> pageRequest = vehicleService.getVehicleByPage(
				board, page, linesPerPage, orderBy, direction);
		log.logRequisicao("GET", "/dryve/vehicle/page", pageRequest.toString(), this.getClass().getName(), "200");
		return ResponseEntity.ok().body(pageRequest);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VehicleDetailsResponse> getById(@PathVariable Long id) {
		VehicleDetailsResponse vehicleDetails = new VehicleDetailsResponse(vehicleService.getById(id));
		log.logRequisicao("GET", "/dryve/vehicle/" + id, vehicleDetails.toString(), this.getClass().getName(), "200");
		return ResponseEntity.ok(vehicleDetails);
	}
}