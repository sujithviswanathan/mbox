package com.mbox.oms.dao.impl;

import com.mbox.oms.bean.Order;
import com.mbox.oms.dao.IOrderDao;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDao implements IOrderDao {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    protected static final String ORDER_BY_ID_QUERY="SELECT ID,CUSTOMER_ID ,PLACEMENT_DATE FROM CUST_ORDER WHERE ID=:orderId";
    protected static final String ALL_ORDER_QUERY ="SELECT ID,CUSTOMER_ID ,PLACEMENT_DATE FROM CUST_ORDER";

    @Override
    public Order getOrderById(int orderId) {

        SqlParameterSource paramerSource = new MapSqlParameterSource("orderId",orderId);
        Order order = jdbcTemplate.queryForObject(ORDER_BY_ID_QUERY,paramerSource,new BeanPropertyRowMapper<>(Order.class));
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return jdbcTemplate.query(ALL_ORDER_QUERY,new ResultSetExtractor<List<Order>>() {
            @Override
            public List<Order> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                List<Order>orderList=new ArrayList<>();
                while(resultSet.next()) {
                    Order order = new Order();
                    order.setId(resultSet.getInt("ID"));
                    order.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                    order.setPlacementDate(resultSet.getString("PLACEMENT_DATE"));
                    orderList.add(order);
                }
                return orderList;
            }
        });

    }
}
