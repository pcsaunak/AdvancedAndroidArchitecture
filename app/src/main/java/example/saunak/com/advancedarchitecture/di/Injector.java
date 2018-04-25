package example.saunak.com.advancedarchitecture.di;

import android.app.Activity;

import com.bluelinelabs.conductor.Controller;

import example.saunak.com.advancedarchitecture.base.BaseActivity;

public class Injector {
    // It will have some static methods for injecting our activities and screens
    private Injector(){

    }

    public static void inject (Activity activity){
        ActivityInjector.get(activity).inject(activity);
    }

    public static void clearComponent(Activity activity) {
        ActivityInjector.get(activity).clear(activity);
    }

    public static void inject(Controller controller){
        ScreenInjector.get(controller.getActivity()).inject(controller);
    }

    public static void clearComponent(Controller controller){
        ScreenInjector.get(controller.getActivity()).clear(controller);
    }
}
