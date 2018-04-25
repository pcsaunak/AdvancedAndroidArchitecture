package example.saunak.com.advancedarchitecture.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

import javax.inject.Inject;

import example.saunak.com.advancedarchitecture.di.ActivityScope;

@ActivityScope
public class DefaultScreenNavigator implements ScreenNavigator {

    private Router router;

    //Constructor injection
    @Inject
    DefaultScreenNavigator(){
    }

    @Override
    public void initWithRouter(Router router, Controller rootScreen) {
        this.router = router;
        // When we change orientation, the below condition will not be satisfied.
        // So no new controller will be initialised.
        if(!router.hasRootController()){
            router.setRoot(RouterTransaction.with(rootScreen));
        }
    }

    @Override
    public boolean pop() {
        // FALSE = if backstack has only one item, one controller is present false returned.
        return router!=null && router.handleBack();
    }

    @Override
    public void clear() {
        router = null;
    }
}
