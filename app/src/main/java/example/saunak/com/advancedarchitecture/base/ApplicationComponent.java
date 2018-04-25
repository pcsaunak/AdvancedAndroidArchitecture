package example.saunak.com.advancedarchitecture.base;

import javax.inject.Singleton;

import dagger.Component;
import example.saunak.com.advancedarchitecture.data.RepoServiceModule;
import example.saunak.com.advancedarchitecture.networking.ServiceModule;

//Singleton meaning that its life cycle is technically same as that of the application.

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        ServiceModule.class,
        RepoServiceModule.class,
})

public interface ApplicationComponent {
    void inject(MyApplication myApplication);
}
