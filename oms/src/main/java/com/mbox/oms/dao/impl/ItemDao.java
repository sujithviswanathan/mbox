package com.mbox.oms.dao.impl;

import com.mbox.oms.bean.Item;
import com.mbox.oms.dao.IItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class ItemDao implements IItemDao{

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    protected static final String ITEM_BY_ID_QUERY="SELECT ID,ITEM_NAME,ITEM_PRICE FROM ITEMS WHERE ID=:itemId";

    @Override
    public Item getById(int itemId) {
        SqlParameterSource paramerSource = new MapSqlParameterSource("itemId",itemId);
        Item item  = jdbcTemplate.queryForObject(ITEM_BY_ID_QUERY,paramerSource,
                new BeanPropertyRowMapper<>(Item.class));

        return item;
    }

}
