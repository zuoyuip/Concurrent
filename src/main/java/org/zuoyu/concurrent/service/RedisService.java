package org.zuoyu.concurrent.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * spring redis 工具类
 *
 * @author Z
 **/
@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Component
public class RedisService {

	public final RedisTemplate redisTemplate;

	public RedisService(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 缓存基本的对象，Integer、String、实体类等
	 *
	 * @param key 缓存的键值
	 * @param value 缓存的值
	 */
	public <T> void setCacheObject(final String key, final T value) {
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * 缓存基本的对象，Integer、String、实体类等
	 *
	 * @param key 缓存的键值
	 * @param value 缓存的值
	 * @param timeout 时间
	 * @param timeUnit 时间颗粒度
	 */
	public <T> void setCacheObject(final String key, final T value, final Long timeout,
			final TimeUnit timeUnit) {
		redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
	}


	/**
	 * 判断 key是否存在
	 *
	 * @param key 键
	 * @return true 存在 false不存在
	 */
	public Boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 获得缓存的基本对象。
	 *
	 * @param key 缓存键值
	 * @return 缓存键值对应的数据
	 */
	public <T> T getCacheObject(final String key) {
		ValueOperations<String, T> operation = redisTemplate.opsForValue();
		return operation.get(key);
	}

	/**
	 * 获得缓存的基本对象。
	 *
	 * @param keys 缓存键值
	 * @return 缓存键值对应的数据
	 */
	public <T> List<T> getCacheObject(final Collection<String> keys) {
		ValueOperations<String, T> operation = redisTemplate.opsForValue();
		return operation.multiGet(keys);
	}


	/**
	 * 缓存List数据
	 *
	 * @param key 缓存的键值
	 * @param dataList 待缓存的List数据
	 * @return 缓存的对象
	 */
	public <T> long setCacheList(final String key, final List<T> dataList) {
		Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
		return count == null ? 0 : count;
	}

	/**
	 * 获得缓存的list对象
	 *
	 * @param key 缓存的键值
	 * @return 缓存键值对应的数据
	 */
	public <T> List<T> getCacheList(final String key) {
		return redisTemplate.opsForList().range(key, 0, -1);
	}

	/**
	 * 缓存Set
	 *
	 * @param key 缓存键值
	 * @param dataSet 缓存的数据
	 * @return 缓存数据的对象
	 */
	public <T> BoundSetOperations<String, T> setCacheSet(final String key,
			@NonNull final Set<T> dataSet) {
		BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
		for (T t : dataSet) {
			setOperation.add(t);
		}
		return setOperation;
	}

	/**
	 * 获得缓存的set
	 *
	 * @param key
	 * @return
	 */
	public <T> Set<T> getCacheSet(final String key) {
		return redisTemplate.opsForSet().members(key);
	}

	/**
	 * 缓存Map
	 *
	 * @param key
	 * @param dataMap
	 */
	public <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
		if (dataMap != null) {
			redisTemplate.opsForHash().putAll(key, dataMap);
		}
	}

	/**
	 * 获得缓存的Map
	 *
	 * @param key
	 * @return
	 */
	public <T> Map<String, T> getCacheMap(final String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * 往Hash中存入数据
	 *
	 * @param key Redis键
	 * @param hKey Hash键
	 * @param value 值
	 */
	public <T> void setCacheMapValue(final String key, final String hKey, final T value) {
		redisTemplate.opsForHash().put(key, hKey, value);
	}

	/**
	 * 获取Hash中的数据
	 *
	 * @param key Redis键
	 * @param hKey Hash键
	 * @return Hash中的对象
	 */
	public <T> T getCacheMapValue(final String key, final String hKey) {
		HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
		return opsForHash.get(key, hKey);
	}

	/**
	 * 获取多个Hash中的数据
	 *
	 * @param key Redis键
	 * @param hKeys Hash键集合
	 * @return Hash对象集合
	 */
	public <T> List<T> getMultiCacheMapValue(final String key,
			final Collection<String> hKeys) {
		return redisTemplate.opsForHash().multiGet(key, hKeys);
	}

	/**
	 * 删除Hash中的某条数据
	 *
	 * @param key Redis键
	 * @param hKey Hash键
	 * @return 是否成功
	 */
	public boolean deleteCacheMapValue(final String key, final String hKey) {
		return redisTemplate.opsForHash().delete(key, hKey) > 0;
	}

	/**
	 * 获得缓存的基本对象列表
	 *
	 * @param pattern 字符串前缀
	 * @return 对象列表
	 */
	public Collection<String> keys(final String pattern) {
		return redisTemplate.keys(pattern);
	}

	/**
	 * 获得哈希键
	 *
	 * @param pattern 字符串前缀
	 * @return 对象列表
	 */
	public Collection<String> hashKeys(final String pattern) {
		return redisTemplate.opsForHash().keys(pattern);
	}

}
