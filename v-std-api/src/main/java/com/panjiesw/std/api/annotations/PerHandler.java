package com.panjiesw.std.api.annotations;

import javax.inject.Scope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author PanjieSW.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerHandler {
}
