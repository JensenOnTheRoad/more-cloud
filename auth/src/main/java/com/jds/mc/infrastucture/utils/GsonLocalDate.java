package com.jds.mc.infrastucture.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.Generated;

/**
 * @author senreysong
 */
@Generated
public class GsonLocalDate implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
  private static final String PATTERN = "yyyy-MM-dd";
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

  @Override
  public LocalDate deserialize(
      JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
      throws JsonParseException {
    return LocalDate.parse(jsonElement.getAsString(), FORMATTER);
  }

  @Override
  public JsonElement serialize(
      LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {
    return new JsonPrimitive(localDate.format(FORMATTER));
  }
}
