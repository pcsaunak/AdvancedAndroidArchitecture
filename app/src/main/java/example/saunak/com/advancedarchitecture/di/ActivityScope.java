package example.saunak.com.advancedarchitecture.di;


/*Changing the "class" to "@interface" means that this is an annotation provider
* Putting "@Scope" tells dagger that this will provide scope annotation
* Retention policy means this annotation will be retained in the final APK*/

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
