package example.saunak.com.advancedarchitecture.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

public interface ScreenNavigator {

    void initWithRouter(Router router, Controller rootScreen);

    boolean pop();
    //clean up any references when an activity is destroyed.
    void clear();
}
