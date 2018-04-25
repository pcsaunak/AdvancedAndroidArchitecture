package example.saunak.com.advancedarchitecture.model;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import org.threeten.bp.ZonedDateTime;

import javax.annotation.Nullable;

public class ZoneDateTimeAdapter {
    @FromJson
    ZonedDateTime from(String json){
        return ZonedDateTime.parse(json);
    }

    @ToJson
    String toJson(@Nullable ZonedDateTime dateTime){
        return dateTime != null ? dateTime.toString():null;
    }
}
