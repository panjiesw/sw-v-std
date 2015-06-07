package com.panjiesw.std.common.exceptions;

/**
 * @author PanjieSW.
 */
public class NotFoundException extends RuntimeException {
  private static final long serialVersionUID = 4952572899839032524L;
  private String field;

  public NotFoundException(String field) {
    this(field, "Not Found");
  }

  public NotFoundException(String field, String message) {
    super(message);
    this.field = field;
  }

  public String field() {
    return field;
  }
}
