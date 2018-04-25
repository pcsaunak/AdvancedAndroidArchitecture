package example.saunak.com.advancedarchitecture.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.threeten.bp.ZonedDateTime;


/*ZonedDateTime is compatible with API 26, however if we target lower APIs than javatime library is incompatable.*/

@AutoValue
public abstract class Repo {
    public abstract long id();

    public abstract String name();

    public abstract String description();

    public abstract User owner();

    @Json(name = "stargazers_count")
    public abstract long starGazersCount();

    @Json(name = "forks_count")
    public abstract long forksCount();

    @Json(name = "contributors_url")
    public abstract String contributorsUrl();

    @Json(name = "created_at")
    public abstract ZonedDateTime createdDate();

    @Json(name = "updated_at")
    public abstract ZonedDateTime updatedDate(); // By default Moshi does not know how to serialise & deserialise date time

    public static JsonAdapter<Repo> jsonAdapter(Moshi moshi){
        return new AutoValue_Repo.MoshiJsonAdapter(moshi);
    }
}

