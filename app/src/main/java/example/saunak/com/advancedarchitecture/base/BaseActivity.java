package example.saunak.com.advancedarchitecture.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.Router;

import java.util.UUID;

import javax.inject.Inject;

import example.saunak.com.advancedarchitecture.R;
import example.saunak.com.advancedarchitecture.di.Injector;
import example.saunak.com.advancedarchitecture.di.ScreenInjector;
import example.saunak.com.advancedarchitecture.ui.ScreenNavigator;

public abstract class BaseActivity extends AppCompatActivity {

    /*How to retain a component across component change -- Using bundle
    * Generally when the activity will die , a new activity will be created on configuration change.
    * This will create multiple objects of the activity.
    * To stop this we are going to give an id to the activity. And use that to retrieve that.
    *
    * Unique key for every activity instance
    *
    * Here we are not making use of Fragments but we are making use of conductors.
    * FragmentManager's Equivalent is Router
    * */


    private static String INSTANCE_ID_KEY = "instance_id";

    @Inject ScreenInjector screenInjector;
    @Inject ScreenNavigator screenNavigator;

    private String instanceId;

    /*IN order to initialise router the conductor needs reference to view group that will act as
    a container.
    Fragment we give the view group id
    Conductor needs the actual view reference*/
    private Router router;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if(savedInstanceState != null){
            instanceId = savedInstanceState.getString(INSTANCE_ID_KEY);
        }else {
            instanceId = UUID.randomUUID().toString();
        }
        Injector.inject(this);
        setContentView(layoutRes()); // Which is implemented by the BaseActivity SubClass
        ViewGroup screenContainer = findViewById(R.id.screen_container);

        /*The only problem now is that our activity must provide a screen_container.
        * If it does not have it, BaseActivity will fail. In order to achieve this we are putting up a logic.*/

        if( screenContainer == null){
            throw new NullPointerException("Activity must have a view with id:: screen_container");
        }
        router = Conductor.attachRouter(this,screenContainer,savedInstanceState);
        /*SavedInstanceState is there for restoring the value/ state of the conductor*/
        screenNavigator.initWithRouter(router,initialScreen());
        monitorBackStack();
        super.onCreate(savedInstanceState);
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INSTANCE_ID_KEY,instanceId);
    }

    @Override
    public void onBackPressed() {
        if(!screenNavigator.pop()){
            super.onBackPressed();
        }

    }

    @LayoutRes
    protected abstract int layoutRes();

    protected abstract Controller initialScreen();

    public String getInstanceId() {
        return instanceId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        screenNavigator.clear();
        if(isFinishing()){
            Injector.clearComponent(this);
        }
    }

    public ScreenInjector getScreenInjector() {
        return screenInjector;
    }

    private void monitorBackStack() {

        /*FragmentManager does not give us the below information */
        router.addChangeListener(new ControllerChangeHandler.ControllerChangeListener() {
            @Override
            public void onChangeStarted(
                    @Nullable Controller to,
                    @Nullable Controller from,
                    boolean isPush,
                    @NonNull ViewGroup container,
                    @NonNull ControllerChangeHandler handler) {

            }

            @Override
            public void onChangeCompleted(
                    @Nullable Controller to,
                    @Nullable Controller from,
                    boolean isPush,
                    @NonNull ViewGroup container,
                    @NonNull ControllerChangeHandler handler) {
                    if(!isPush && from != null){
                        /*User tapped backButton -- means a pop function needs to be done.
                        * So isPush == false
                        * From controller -- is the controller that is being popped */
                        Injector.clearComponent(from);
                    }
            }
        });
    }
}
