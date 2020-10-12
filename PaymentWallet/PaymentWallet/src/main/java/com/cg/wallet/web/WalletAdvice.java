package com.cg.wallet.web;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import com.cg.wallet.dto.ErrorResponse;
import com.cg.wallet.exception.WalletAccountDoNotExistsException;
import com.cg.wallet.exception.WalletAccountExistsException;

/****************************************************************************************************
* @author      :  Parteek goel
* Description  :  It is a Controller Advice class that is used to handle the exception
* Version      :  1.0
* Created Date :  7-OCT-2020
*******************************************************************************************************/


@RestControllerAdvice
public class WalletAdvice {

	@ExceptionHandler(WalletAccountExistsException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public ErrorResponse handleAccountException(WalletAccountExistsException ex) {
		return new ErrorResponse(HttpStatus.CONFLICT.toString(), ex.getMessage(), LocalDateTime.now().toString());
	}
	@ExceptionHandler(WalletAccountDoNotExistsException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public ErrorResponse handleAccountException(WalletAccountDoNotExistsException ex) {
		return new ErrorResponse(HttpStatus.CONFLICT.toString(), ex.getMessage(), LocalDateTime.now().toString());
	}
	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorResponse handleBankException(HttpClientErrorException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.toString(), ex.getResponseBodyAsString(), LocalDateTime.now().toString());
	}
	
	
}
