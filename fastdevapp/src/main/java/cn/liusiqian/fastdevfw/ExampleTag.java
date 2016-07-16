package cn.liusiqian.fastdevfw;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Créé par liusiqian 16/7/16.
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.TYPE})
public @interface ExampleTag
{
}
