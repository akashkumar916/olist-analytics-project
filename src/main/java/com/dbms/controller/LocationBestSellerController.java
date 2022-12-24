package com.dbms.controller;

import com.dbms.common.LocationBestSellingProductMetricParameters;
import com.dbms.common.ProductPerformanceMetricParameters;
import com.dbms.common.ResponseConstants;
import com.dbms.dto.LocationBestSellerDTO;
import com.dbms.dto.ProductWeeklyPerformanceDTO;
import com.dbms.dto.response.GenericDataResponse;
import com.dbms.service.impl.LocationBestSellerProductServiceImpl;
import com.dbms.service.impl.ProductPerformanceServiceImpl;
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
@RequestMapping("/locationBestSeller")
@CrossOrigin(origins = "*")
public class LocationBestSellerController {

    private static final Logger logger = Logger.getLogger(LocationBestSellerController.class.getName());

    @Autowired
    private LocationBestSellerProductServiceImpl locationBestSellerProductService;

    @PostMapping
    public GenericDataResponse getLocationsBestSelling(@RequestBody LocationBestSellingProductMetricParameters locationBestSellingProductMetricParameters) throws Exception {
        if(locationBestSellingProductMetricParameters == null || !locationBestSellingProductMetricParameters.isValid()) {
            logger.log(Level.SEVERE, "Invalid params = " + locationBestSellingProductMetricParameters);
            return new GenericDataResponse<>(ResponseConstants.RESPONSE_FAIL, null);
        }
        List<LocationBestSellerDTO> locationBestSellerDTOS = locationBestSellerProductService.getProductPerformance(locationBestSellingProductMetricParameters);
        return new GenericDataResponse<>(ResponseConstants.RESPONSE_OK, locationBestSellerDTOS);
    }

}
