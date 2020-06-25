package com.gmailat.pm.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
public class NotEnoughMoneyExceptionx extends RuntimeException{
}
