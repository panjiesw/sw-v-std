package com.panjiesw.std.api.modules;

import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.validation.Validator;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApiModule_ValidatorFactory implements Factory<Validator> {
  private final ApiModule module;

  public ApiModule_ValidatorFactory(ApiModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public Validator get() {  
    Validator provided = module.validator();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<Validator> create(ApiModule module) {  
    return new ApiModule_ValidatorFactory(module);
  }
}

