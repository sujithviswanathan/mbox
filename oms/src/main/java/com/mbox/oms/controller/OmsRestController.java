package com.mbox.oms.controller;

import com.mbox.oms.bean.ApiError;
import com.mbox.oms.bean.Order;
import com.mbox.oms.service.IMboxOmsService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class OmsRestController {
    @Autowired
    IMboxOmsService omsService;
    @RequestMapping(value = "/orders",method = RequestMethod.GET)
    public ResponseEntity<List<Order>> orders(){
       return new ResponseEntity<List<Order>>(omsService.lookupAllOrders(), HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success",response=Order.class),
            @ApiResponse(code = 404, message = "Requested Info Not Found in the System",response=ApiError.class),
            @ApiResponse(code = 500, message = "Internal Service Failure",response=ApiError.class),

    })
    @RequestMapping(value = "/orders/{orderId}",method = RequestMethod.GET)
    public ResponseEntity<Order> order(@PathVariable int orderId){
        return new ResponseEntity<Order>(omsService.findOrderById(orderId), HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success",response=Double.class),
            @ApiResponse(code = 404, message = "Requested Info Not Found in the System",response=ApiError.class),
            @ApiResponse(code = 500, message = "Internal Service Failure",response=ApiError.class),

    })
    @RequestMapping(value = "/orders/{orderId}/cost",method = RequestMethod.GET)
    public ResponseEntity<Double> totalCost(@PathVariable int orderId){
        return new ResponseEntity<Double>(omsService.getTotalCostForOrder(orderId), HttpStatus.OK);
    }
}
