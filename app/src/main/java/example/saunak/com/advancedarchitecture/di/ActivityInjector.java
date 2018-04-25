package example.saunak.com.advancedarchitecture.di;

import android.app.Activity;
import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjector;
import example.saunak.com.advancedarchitecture.base.BaseActivity;
import example.saunak.com.advancedarchitecture.base.MyApplication;

public class ActivityInjector {

    /*This is responsible for injecting the Map that has been created in Activity Binding Module*/

    private final Map<Class<? extends Activity>,Provider<AndroidInjector.Factory<? extends Activity>>> activityInjectors;
    // cache is using the instance id
    private final Map<String,AndroidInjector<? extends Activity>> cache = new HashMap<>();
    @Inject
    ActivityInjector(Map<Class<? extends Activity>,Provider<AndroidInjector.Factory<? extends Activity>>> activityInjectors){
        this.activityInjectors = activityInjectors;
    }


    /*Dispatching Android Injector class is what we are trying to implement.
    * We have a small change, if the class is present in the the cache than we will try to restore that and not
    * create a new one.*/


    void inject(Activity activity){
        if( !(activity instanceof BaseActivity)){
            throw new IllegalArgumentException("Activity must extend BaseActivity");
        }
        String instanceId = ((BaseActivity) activity).getInstanceId();
        if (cache.containsKey(instanceId)){
            ((AndroidInjector<Activity>)cache.get(instanceId)).inject(activity);
            return;
        }
        AndroidInjector.Factory<Activity> injectorFactory =
                (AndroidInjector.Factory<Activity>) activityInjectors.get(activity.getClass()).get();
        AndroidInjector<Activity> injector = injectorFactory.create(activity);
        cache.put(instanceId,injector);
    }

    void clear(Activity activity){
        if(!(activity instanceof BaseActivity)){
            throw new IllegalArgumentException("Activity must extend BaseActivity");
        }
        cache.remove(((BaseActivity) activity).getInstanceId());
    }

    /*How to retrieve the injector from outside the class */

    static ActivityInjector get(Context context){
        return ((MyApplication)context.getApplicationContext()).getActivityInjector();
    }
}
