package com.jds.mc.infrastucture.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Generated;

/**
 * @author senreysong
 */
@Generated
public class GsonLocalDateTime
    implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
  private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

  @Override
  public LocalDateTime deserialize(
      JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
      throws JsonParseException {
    return LocalDateTime.parse(jsonElement.getAsString(), FORMATTER);
  }

  @Override
  public JsonElement serialize(
      LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
    return new JsonPrimitive(localDateTime.format(FORMATTER));
  }
}
