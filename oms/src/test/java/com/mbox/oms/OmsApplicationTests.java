package com.mbox.oms;

import com.mbox.oms.bean.Item;
import com.mbox.oms.bean.Order;
import com.mbox.oms.bean.OrderItem;
import com.mbox.oms.controller.OmsRestController;
import com.mbox.oms.dao.IItemDao;
import com.mbox.oms.dao.IOrderDao;
import com.mbox.oms.dao.IOrderItemsDao;
import com.mbox.oms.service.IMboxOmsService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OmsApplicationTests {




	private static final String PORT_SCAN_URL = "/tools/port-scan";
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	IOrderDao orderDao;
	@Autowired
	IOrderItemsDao orderItemsDao;
	@Autowired
	IMboxOmsService omsService;
	@Autowired
	IItemDao itemDao;
	@Rule
	public ExpectedException expectedException= ExpectedException.none();
	@Test
	public void contextLoads() {

		Assert.assertTrue(jdbcTemplate !=null);
	}
	@Test
	public void testOrderNotFound() {
		expectedException.expect(EmptyResultDataAccessException.class);
		orderDao.getOrderById(1000);
	}
	@Test
	public void testOrderById(){
		Order order =orderDao.getOrderById(2000);
		Assert.assertTrue(order !=null && (order.getId()==2000)&& "2017-08-21".equals(order.getPlacementDate())) ;
	}
	@Test
	public void testOrderItemsByOrderId(){
		List<OrderItem> orderItems=orderItemsDao.getByOrderId(2000);
		Assert.assertTrue(!orderItems.isEmpty() && orderItems.size()==2);
	}
	@Test
	public void getOrderbyOrderId(){
		Order order= omsService.findOrderById(2000);
		Assert.assertTrue(order!=null && !order.getOrderItemsList().isEmpty());
		Item item=order.getOrderItemsList().get(0).getItem();
		Assert.assertTrue(item.getItemName().equals("iPhone6"));
	}
	@Test
	public void getTotalCost(){
		Assert.assertTrue(omsService.getTotalCostForOrder(2000)==535);
	}
	@Test
	public void findItemById(){
		Item item =itemDao.getById(5000);
		Assert.assertTrue(item!=null && "Hp -Laptop".equals(item.getItemName()));
	}


}

