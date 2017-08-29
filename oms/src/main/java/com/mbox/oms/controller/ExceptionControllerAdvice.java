package com.mbox.oms.controller;


import com.mbox.oms.bean.ApiError;
import com.mbox.oms.util.IMboxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;


@ControllerAdvice
public class ExceptionControllerAdvice {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	@Autowired
	private Environment env;
	/**
	   * Exception handler for EusSystemException
	   * @param e
	   * @return
	   */
	  @ExceptionHandler(Exception.class)
	  public ResponseEntity<ApiError> handleSystemException(Exception e)
	  {
		  LOGGER.error("Internal error {}", e.getMessage());
		  ApiError error = new ApiError();
		  error.setCust_ErrCode(IMboxUtil.SYSTEM_ERROR_CD);
		  error.addErrorDescription("Internal Service Failure :"+e.getMessage());
		  return new ResponseEntity<ApiError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<ApiError> handleSQlException(SQLException e)
	{
		LOGGER.error("Db error {}", e.getMessage());
		ApiError error = new ApiError();
		error.setCust_ErrCode(IMboxUtil.DB_ERROR_CD);
		error.addErrorDescription("Back End Failure :"+e.getMessage());
		return new ResponseEntity<ApiError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ApiError> handleResourceNotFoundException(EmptyResultDataAccessException e)
	{
		LOGGER.error("Record Not Found {}", e.getMessage());
		ApiError error = new ApiError();
		error.setCust_ErrCode(IMboxUtil.RESOURCE_NOT_FOUND_ERR_CD);
		error.addErrorDescription("Requested Info Not Found in the System");
		return new ResponseEntity<ApiError>(error,HttpStatus.NOT_FOUND);
	}


}
