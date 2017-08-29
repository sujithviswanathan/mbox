package com.mbox.oms.dao.impl;

import com.mbox.oms.bean.Order;
import com.mbox.oms.bean.OrderItem;
import com.mbox.oms.dao.IOrderItemsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class OrderItemDao implements IOrderItemsDao{
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    protected static final String ORDER_ITEM_BY_ORDER_ID_QUERY="SELECT ID,ORDER_ID,ITEM_ID,ITEM_COUNT FROM ORDER_ITEM WHERE ORDER_ID=:orderId";
    @Override
    public List<OrderItem> getByOrderId(int orderId) {

        SqlParameterSource paramerSource = new MapSqlParameterSource("orderId",orderId);
        List<OrderItem> orderItems  = jdbcTemplate.query(ORDER_ITEM_BY_ORDER_ID_QUERY,paramerSource,
                new BeanPropertyRowMapper(OrderItem.class));

        return orderItems;
    }
}
