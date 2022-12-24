package com.dbms.controller;

import com.dbms.common.NewOrdersParameters;
import com.dbms.common.ProductPerformanceMetricParameters;
import com.dbms.common.ResponseConstants;
import com.dbms.dto.NewOrdersDTO;
import com.dbms.dto.ProductWeeklyPerformanceDTO;
import com.dbms.dto.response.GenericDataResponse;
import com.dbms.service.impl.NewOrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author ayushkumar
 * @created on 06/12/22
 */

@RestController
@RequestMapping("/newOrders")
@CrossOrigin(origins = "*")
public class NewOrdersController {

    private static final Logger logger = Logger.getLogger(ProductPerformanceController.class.getName());

    @Autowired
    private NewOrdersServiceImpl newOrdersService;

    @PostMapping
    public GenericDataResponse getNewOrdersTrend(@RequestBody NewOrdersParameters newOrdersParameters) throws Exception {
        if(newOrdersParameters == null || !newOrdersParameters.isValid()) {
            logger.log(Level.SEVERE, "Invalid params = {}", newOrdersParameters);
            return new GenericDataResponse<>(ResponseConstants.RESPONSE_FAIL, null);
        }
        List<NewOrdersDTO> numNewOrders = newOrdersService.getNumNewOrders(newOrdersParameters);
        return new GenericDataResponse<>(ResponseConstants.RESPONSE_OK, numNewOrders);
    }

}
