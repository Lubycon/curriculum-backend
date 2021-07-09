package com.lubycon.curriculum.subscribe.exception;

import com.lubycon.curriculum.base.error.ErrorCode;
import com.lubycon.curriculum.base.error.exception.EntityNotFoundException;

public class EmailNotFoundException extends EntityNotFoundException {

  public EmailNotFoundException() {
    super(ErrorCode.EMAIL_NOT_FOUND.getMessage());
  }

}
