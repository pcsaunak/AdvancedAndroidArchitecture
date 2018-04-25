package example.saunak.com.advancedarchitecture.base;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

//Means its a provider of dependency
@Module
public class ApplicationModule {
    // It will have a constructor that takes in an instance of android application

    private final Application application;

    ApplicationModule(Application application) {
        this.application = application;
    }

    // Now any class can get the application context.
    @Provides
    Context providesApplicationContext(){
        return application;
    }
    //To use a module we need a component.
    // A component in dagger is the object that actually injects classes.
}
