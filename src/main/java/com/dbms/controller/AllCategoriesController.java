package com.dbms.controller;

import com.dbms.common.CommonConfig;
import com.dbms.common.ResponseConstants;
import com.dbms.dto.AllCategoriesDTO;
import com.dbms.dto.response.GenericDataResponse;
import com.dbms.service.impl.AllCategoriesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/*
 * @author ayushkumar
 * @created on 06/12/22
 */

@RestController
@RequestMapping("/allCategories")
@CrossOrigin(origins = "*")
public class AllCategoriesController {

    private static final Logger logger = Logger.getLogger(AllCategoriesController.class.getName());

    @Autowired
    private AllCategoriesServiceImpl allCategoriesService;

    @GetMapping
    public GenericDataResponse getAllCategories() throws Exception {
        List<AllCategoriesDTO> allCategories = allCategoriesService.getAllCategories();
        return new GenericDataResponse<>(ResponseConstants.RESPONSE_OK, allCategories);
    }
}