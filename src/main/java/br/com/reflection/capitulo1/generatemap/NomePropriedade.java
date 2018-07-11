package br.com.reflection.capitulo1.generatemap;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface NomePropriedade {
String value();
}