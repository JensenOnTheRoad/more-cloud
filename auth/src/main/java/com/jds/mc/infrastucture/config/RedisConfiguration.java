package com.jds.mc.infrastucture.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jds.mc.infrastucture.utils.GsonLocalDate;
import com.jds.mc.infrastucture.utils.GsonLocalDateTime;
import com.jds.mc.infrastucture.utils.GsonLong;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.util.ReflectionUtils;

@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@RequiredArgsConstructor
public class RedisConfiguration {

  private final ApplicationContext applicationContext;

  @Value("${custom.cache-prefix}")
  private String cachePrefix;

  // region Gson
  private static final Gson gson =
      new GsonBuilder()
          .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTime())
          .registerTypeAdapter(LocalDate.class, new GsonLocalDate())
          .registerTypeAdapter(Long.class, new GsonLong())
          .create();

  private record GsonRedisSerializer<T>(Type type, Gson gson) implements RedisSerializer<T> {
    @Override
    public byte[] serialize(Object o) throws SerializationException {
      return gson.toJson(o).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
      return gson.fromJson(new String(bytes, StandardCharsets.UTF_8), type);
    }
  }

  // endregion

  // region configuration
  @Bean
  public RedisTemplate<String, Serializable> redisTemplate(
      LettuceConnectionFactory lettuceConnectionFactory) {
    RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();

    redisTemplate.setConnectionFactory(lettuceConnectionFactory);

    // RedisSerializer.string() is equivalent to new StringRedisSerializer()
    redisTemplate.setKeySerializer(RedisSerializer.string());
    redisTemplate.setHashKeySerializer(RedisSerializer.string());

    //   default: GenericJackson2JsonRedisSerializer,
    //  RedisSerializer.json() is equivalent to new  GenericJackson2JsonRedisSerializer()
    redisTemplate.setValueSerializer(RedisSerializer.json());
    redisTemplate.setHashValueSerializer(RedisSerializer.json());

    // 使配置生效
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  @Bean(name = "cacheManager")
  @Primary
  public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
    Duration expiredDuration = Duration.ofSeconds((long) 12 * 60 * 60);

    GsonRedisSerializer<Object> gsonRedisSerializer = new GsonRedisSerializer<>(Object.class, gson);
    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

    RedisCacheConfiguration cacheConfiguration =
        RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(expiredDuration)
            .disableCachingNullValues()
            .computePrefixWith(cacheName -> cachePrefix.concat(":").concat(cacheName).concat(":"))
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(gsonRedisSerializer));

    // 通过 Config 对象即可对缓存进行自定义配置
    return RedisCacheManager.builder(redisConnectionFactory)
        .cacheDefaults(cacheConfiguration)
        .withCacheConfiguration("custom1", cacheConfiguration)
        .withCacheConfiguration("custom2", cacheConfiguration)
        .withInitialCacheConfigurations(buildInitCaches())
        .build();
  }

  /** Build init caches config */
  private Map<String, RedisCacheConfiguration> buildInitCaches() {

    HashMap<String, RedisCacheConfiguration> cacheConfigMap = new HashMap<>();

    Arrays.stream(applicationContext.getBeanNamesForType(Object.class))
        .map(applicationContext::getType)
        .filter(Objects::nonNull)
        .forEach(
            clazz ->
                ReflectionUtils.doWithMethods(
                    clazz,
                    method -> {
                      Cacheable cacheable = AnnotationUtils.findAnnotation(method, Cacheable.class);

                      if (Objects.nonNull(cacheable)) {
                        for (String cache : cacheable.cacheNames()) {

                          GsonRedisSerializer<Object> serializer =
                              new GsonRedisSerializer<>(method.getGenericReturnType(), gson);
                          RedisSerializationContext.SerializationPair<Object> serializationPair =
                              RedisSerializationContext.SerializationPair.fromSerializer(
                                  serializer);

                          cacheConfigMap.put(
                              cache,
                              RedisCacheConfiguration.defaultCacheConfig()
                                  .serializeValuesWith(serializationPair));
                        }
                      }
                    }));

    return cacheConfigMap;
  }
  // endregion
}
