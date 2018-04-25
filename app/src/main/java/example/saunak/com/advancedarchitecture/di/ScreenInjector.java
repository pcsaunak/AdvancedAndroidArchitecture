package example.saunak.com.advancedarchitecture.di;

import android.app.Activity;

import com.bluelinelabs.conductor.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjector;
import example.saunak.com.advancedarchitecture.base.BaseActivity;
import example.saunak.com.advancedarchitecture.base.BaseController;

// Multiple times
// This means if a component injects the dependency more than once than use the one that has already been created.
@ActivityScope
public class ScreenInjector {
    private final Map<Class<? extends Controller>,Provider<AndroidInjector.Factory<? extends Controller>>> screenInjectors;
    private final Map<String,AndroidInjector<Controller>> cache = new HashMap<>();

    @Inject
    ScreenInjector (Map<Class<? extends Controller>,
            Provider<AndroidInjector.Factory<? extends Controller>>> screenInjectors){
                this.screenInjectors = screenInjectors;
    }

    void inject(Controller controller){
        if(!(controller instanceof BaseController)){
            throw new IllegalStateException("Controller must extend BaseController");
        }
        String instanceId = controller.getInstanceId();
        if (cache.containsKey(instanceId)){
            cache.get(instanceId).inject(controller);
            return;
        }

        AndroidInjector.Factory<Controller> injectorFactory =
                (AndroidInjector.Factory<Controller>) screenInjectors.get(controller.getClass()).get();
        AndroidInjector<Controller> injector = injectorFactory.create(controller);
        cache.put(instanceId,injector);
        injector.inject(controller);
    }

    void clear(Controller controller){
        cache.remove(controller.getInstanceId());
    }

    static ScreenInjector get(Activity activity){
        if(!(activity instanceof BaseActivity)){
            throw new IllegalArgumentException("Activity is not instance of BaseActivity");
        }

        return ((BaseActivity)activity).getScreenInjector();
    }
}
