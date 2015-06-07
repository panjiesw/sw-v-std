package com.panjiesw.std.common.dto;

import io.vertx.core.json.JsonObject;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author PanjieSW.
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(of = {"id", "username", "email"})
@Accessors(fluent = true)
@Builder
public class User {
  Long id;
  @NotEmpty
  @Length(max = 64)
  String username;
  @NotEmpty
  @Email
  @Length(max = 64)
  String email;

  public JsonObject toJson() {
    return new JsonObject()
      .put("id", id)
      .put("username", username)
      .put("email", email);
  }

  public static User fromJson(JsonObject json) {
    return User.builder()
      .id(json.getLong("id"))
      .username(json.getString("username"))
      .email(json.getString("email"))
      .build();
  }
}
