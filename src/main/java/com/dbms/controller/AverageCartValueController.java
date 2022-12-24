package com.dbms.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbms.common.AverageCartValueMetricParameters;
import com.dbms.common.ResponseConstants;
import com.dbms.dto.AverageCartValueDTO;
import com.dbms.dto.response.GenericDataResponse;
import com.dbms.service.impl.AverageCartValueServiceImpl;

@RestController
@RequestMapping("/averageCartValue")
@CrossOrigin(origins = "*")
public class AverageCartValueController {

	private static final Logger logger = Logger.getLogger(AverageCartValueController.class.getName());

	@Autowired
	private AverageCartValueServiceImpl averageCartValueService;

	@PostMapping
	public GenericDataResponse getAvgOrderValueDistribution(@RequestBody AverageCartValueMetricParameters averageCartValueMetricParameters) {
		if(averageCartValueMetricParameters == null || !averageCartValueMetricParameters.isValid()) {
			logger.log(Level.SEVERE, "Invalid params = {}", averageCartValueMetricParameters);
			return new GenericDataResponse<>(ResponseConstants.RESPONSE_FAIL, null);
		}
		List<AverageCartValueDTO> averageCartValues = averageCartValueService.getAvgOrderValues(averageCartValueMetricParameters);
		return new GenericDataResponse<>(ResponseConstants.RESPONSE_OK, averageCartValues);
	}
}
