package com.mbox.oms;

import com.mbox.oms.bean.Order;
import com.mbox.oms.controller.ExceptionControllerAdvice;
import com.mbox.oms.controller.OmsRestController;
import com.mbox.oms.dao.IOrderDao;
import com.mbox.oms.service.IMboxOmsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = {OmsRestController.class, ExceptionControllerAdvice.class},secure = false)
public class OmsRestControllerTests {
    private static final String ORDER_RESOURCE_URL="http://localhost:8080/orders/2000?";
    private static final String ORDER_RESOURCE_NOT_FOUND_URL="http://localhost:8080/orders/8000?";
    @MockBean
    IMboxOmsService omsService;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private IOrderDao mockDao;
    @Test
    public void getOrderResource() throws Exception {
        Order order = new Order();
        order.setId(2000);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                ORDER_RESOURCE_URL).accept(
                MediaType.APPLICATION_JSON);
        Mockito.when(omsService.findOrderById(2000)).thenReturn(order);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        Assert.assertTrue(result.getResponse().getStatus()==200);
    }


}
