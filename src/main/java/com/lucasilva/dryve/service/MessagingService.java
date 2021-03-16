package com.lucasilva.dryve.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lucasilva.dryve.config.MessagingConfig;
import com.lucasilva.dryve.model.Vehicle;
import com.lucasilva.dryve.model.VehicleMessagingStatus;
import com.lucasilva.dryve.utils.LogUtils;

@Service
public class MessagingService {
	
	@Autowired
	private RabbitTemplate template; 
	
	@Value("${vehicle.messaging.status}")
	private String status;
	
	@Value("${vehicle.messaging.message}")
	private String message;

	@Autowired
	private LogUtils log;
	
	public void sendVehicleMessage(Vehicle vehicle) {
		VehicleMessagingStatus vehicleMessaging = new VehicleMessagingStatus(vehicle, status, message + vehicle.getId());
		log.logInfo("MessagingService", "sendVehicleMessage", "PROCESS", this.getClass().getName());
		template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, vehicleMessaging);
		log.logInfo("MessagingService", "sendVehicleMessage", "COMPLETED", this.getClass().getName());
	}
}