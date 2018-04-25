package example.saunak.com.advancedarchitecture.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class User {
    public abstract long id();

    public abstract String login(); // github user ID

    // To get the type adapter generated for us , we need to get the static class
    // Auto value generates a class, the implementation of the class that we just defined.

    public static JsonAdapter<User> jsonAdapter(Moshi moshi){
        return new AutoValue_User.MoshiJsonAdapter(moshi);
    }
}
