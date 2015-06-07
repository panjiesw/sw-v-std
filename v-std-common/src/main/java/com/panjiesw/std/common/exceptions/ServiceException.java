package com.panjiesw.std.common.exceptions;

/**
 * @author PanjieSW.
 */
public class ServiceException extends RuntimeException {
  private static final long serialVersionUID = -5675442329554946743L;

  public ServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
