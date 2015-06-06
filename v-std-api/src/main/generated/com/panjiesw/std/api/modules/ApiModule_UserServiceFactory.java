package com.panjiesw.std.api.modules;

import com.panjiesw.std.service.user.UserService;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApiModule_UserServiceFactory implements Factory<UserService> {
  private final ApiModule module;

  public ApiModule_UserServiceFactory(ApiModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public UserService get() {  
    UserService provided = module.userService();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<UserService> create(ApiModule module) {  
    return new ApiModule_UserServiceFactory(module);
  }
}

