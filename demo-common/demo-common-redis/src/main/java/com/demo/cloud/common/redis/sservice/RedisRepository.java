package com.demo.cloud.common.redis.sservice;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.CollectionUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * @author LiuYuQi
 * @version 1.0
 * @description: TODO
 * @date 2021/11/24 17:07
 */
@Slf4j
public class RedisRepository {
    /**
     * 默认编码
     */
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    /**
     * key序列化
     */
    private static final StringRedisSerializer STRING_SERIALIZER = new StringRedisSerializer();

    /**
     * value 序列化
     */
    private static final JdkSerializationRedisSerializer OBJECT_SERIALIZER = new JdkSerializationRedisSerializer();

    /**
     * Spring Redis Template
     */
    private RedisTemplate<String, Object> cusTomRedisTemplate;

    public RedisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.cusTomRedisTemplate = redisTemplate;
        this.cusTomRedisTemplate.setKeySerializer(STRING_SERIALIZER);
        this.cusTomRedisTemplate.setValueSerializer(OBJECT_SERIALIZER);
    }

    /**
     * 获取链接工厂
     */
    public RedisConnectionFactory getConnectionFactory() {
        return this.cusTomRedisTemplate.getConnectionFactory();
    }

    /**
     * 获取 RedisTemplate对象
     */
    public RedisTemplate<String, Object> getRedisTemplate() {
        return cusTomRedisTemplate;
    }

    /**
     * 清空DB
     *
     * @param node redis 节点
     */
    public void flushDB(RedisClusterNode node) {
        this.cusTomRedisTemplate.opsForCluster().flushDb(node);
    }


    /**
     * 添加到带有 过期时间的  缓存
     *
     * @param key   redis主键
     * @param value 值
     * @param time  过期时间(单位秒)
     */
    public void setExpire(final byte[] key, final byte[] value, final long time) {
        cusTomRedisTemplate.execute((RedisCallback<Long>) connection -> {
            connection.setEx(key, time, value);
            log.debug("[redisTemplate redis]放入 缓存  url:{} ========缓存时间为{}秒", key, time);
            return 1L;
        });
    }

    /**
     * 添加到带有 过期时间的  缓存
     *
     * @param key   redis主键
     * @param value 值
     * @param time  过期时间(单位秒)
     */
    public void setExpire(final String key, final Object value, final long time) {
        cusTomRedisTemplate.execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = getRedisSerializer();
            byte[] keys = serializer.serialize(key);
            byte[] values = OBJECT_SERIALIZER.serialize(value);
            connection.setEx(keys, time, values);
            return 1L;
        });
    }

    /**
     * 一次性添加数组到   过期时间的  缓存，不用多次连接，节省开销
     *
     * @param keys   redis主键数组
     * @param values 值数组
     * @param time   过期时间(单位秒)
     */
    public void setExpire(final String[] keys, final Object[] values, final long time) {
        cusTomRedisTemplate.execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = getRedisSerializer();
            for (int i = 0; i < keys.length; i++) {
                byte[] bKeys = serializer.serialize(keys[i]);
                byte[] bValues = OBJECT_SERIALIZER.serialize(values[i]);
                connection.setEx(bKeys, time, bValues);
            }
            return 1L;
        });
    }


    /**
     * 一次性添加数组到   过期时间的  缓存，不用多次连接，节省开销
     *
     * @param keys   the keys
     * @param values the values
     */
    public void set(final String[] keys, final Object[] values) {
        cusTomRedisTemplate.execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = getRedisSerializer();
            for (int i = 0; i < keys.length; i++) {
                byte[] bKeys = serializer.serialize(keys[i]);
                byte[] bValues = OBJECT_SERIALIZER.serialize(values[i]);
                connection.set(bKeys, bValues);
            }
            return 1L;
        });
    }


    /**
     * 添加到缓存
     *
     * @param key   the key
     * @param value the value
     */
    public void set(final String key, final Object value) {
        cusTomRedisTemplate.execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = getRedisSerializer();
            byte[] keys = serializer.serialize(key);
            byte[] values = OBJECT_SERIALIZER.serialize(value);
            connection.set(keys, values);
            log.info("[redisTemplate redis]放入 缓存  url:{}", key);
            return 1L;
        });
    }

    /**
     * 查询在这个时间段内即将过期的key
     *
     * @param key  the key
     * @param time the time
     * @return the list
     */
    public List<String> willExpire(final String key, final long time) {
        final List<String> keysList = new ArrayList<>();
        cusTomRedisTemplate.execute((RedisCallback<List<String>>) connection -> {
            Set<String> keys = cusTomRedisTemplate.keys(key + "*");
            for (String key1 : keys) {
                Long ttl = connection.ttl(key1.getBytes(DEFAULT_CHARSET));
                if (0 <= ttl && ttl <= 2 * time) {
                    keysList.add(key1);
                }
            }
            return keysList;
        });
        return keysList;
    }
    public Long willExpire(final String key) {
        Long execute = cusTomRedisTemplate.execute((RedisCallback<Long>) connection -> {
            return connection.ttl(key.getBytes(DEFAULT_CHARSET));

        });
        return 0L;
    }

    /**
     * 查询在以keyPatten的所有  key
     *
     * @param keyPatten the key patten
     * @return the set
     */
    public Set<String> keys(final String keyPatten) {
        return cusTomRedisTemplate.execute((RedisCallback<Set<String>>) connection -> cusTomRedisTemplate.keys(keyPatten + "*"));
    }

    public Long deletePre(final String keyPatten){
        Set<String> keys = keys(keyPatten);
        if(keys.isEmpty()){
            return 0L;
        }
       return del(keys);
    }

    /**
     * 根据key获取对象
     *
     * @param key the key
     * @return the byte [ ]
     */
    public byte[] get(final byte[] key) {
        byte[] R = cusTomRedisTemplate.execute((RedisCallback<byte[]>) connection -> connection.get(key));
        log.debug("[redisTemplate redis]取出 缓存  url:{} ", key);
        return R;
    }

    /**
     * 根据key获取对象
     *
     * @param key the key
     * @return the string
     */
    public Object get(final String key) {
        Object RStr = cusTomRedisTemplate.execute((RedisCallback<Object>) connection -> {
            RedisSerializer<String> serializer = getRedisSerializer();
            byte[] keys = serializer.serialize(key);
            byte[] values = connection.get(keys);
            return OBJECT_SERIALIZER.deserialize(values);
        });
        log.debug("[redisTemplate redis]取出 缓存  url:{} ", key);
        return RStr;
    }


    /**
     * 根据key获取对象
     *
     * @param keyPatten the key patten
     * @return the keys values
     */
    public Map<String, Object> getKeysValues(final String keyPatten) {
        return cusTomRedisTemplate.execute((RedisCallback<Map<String, Object>>) connection -> {
            RedisSerializer<String> serializer = getRedisSerializer();
            Map<String, Object> maps = new HashMap<>(16);
            Set<String> keys = cusTomRedisTemplate.keys(keyPatten + "*");
            if (!CollectionUtils.isEmpty(keys)) {
                for (String key : keys) {
                    byte[] bKeys = serializer.serialize(key);
                    byte[] bValues = connection.get(bKeys);
                    Object value = OBJECT_SERIALIZER.deserialize(bValues);
                    maps.put(key, value);
                }
            }
            return maps;
        });
    }

    /**
     * Ops for hash hash operations.
     *
     * @return the hash operations
     */
    public HashOperations<String, String, Object> opsForHash() {
        return cusTomRedisTemplate.opsForHash();
    }

    /**
     * 对HashMap操作
     *
     * @param key       the key
     * @param hashKey   the hash key
     * @param hashValue the hash value
     */
    public void putHashValue(String key, String hashKey, Object hashValue) {
        log.debug("[redisTemplate redis]  putHashValue()  key={},hashKey={},hashValue={} ", key, hashKey, hashValue);
        opsForHash().put(key, hashKey, hashValue);
    }

    /**
     * 获取单个field对应的值
     *
     * @param key     the key
     * @param hashKey the hash key
     * @return the hash values
     */
    public Object getHashValues(String key, String hashKey) {
        log.debug("[redisTemplate redis]  getHashValues()  key={},hashKey={}", key, hashKey);
        return opsForHash().get(key, hashKey);
    }

    /**
     * 根据key值删除
     *
     * @param key      the key
     * @param hashKeys the hash keys
     */
    public void delHashValues(String key, Object... hashKeys) {
        log.debug("[redisTemplate redis]  delHashValues()  key={}", key);
        opsForHash().delete(key, hashKeys);
    }

    /**
     * key只匹配map
     *
     * @param key the key
     * @return the hash value
     */
    public Map<String, Object> getHashValue(String key) {
        log.debug("[redisTemplate redis]  getHashValue()  key={}", key);
        return opsForHash().entries(key);
    }

    /**
     * 批量添加
     *
     * @param key the key
     * @param map the map
     */
    public void putHashValues(String key, Map<String, Object> map) {
        opsForHash().putAll(key, map);
    }

    /**
     * 集合数量
     *
     * @return the long
     */
    public long dbSize() {
        return cusTomRedisTemplate.execute(RedisServerCommands::dbSize);
    }

    /**
     * 清空redis存储的数据
     *
     * @return the string
     */
    public String flushDB() {
        return cusTomRedisTemplate.execute((RedisCallback<String>) connection -> {
            connection.flushDb();
            return "ok";
        });
    }

    /**
     * 判断某个主键是否存在
     *
     * @param key the key
     * @return the boolean
     */
    public boolean exists(final String key) {
        return cusTomRedisTemplate.execute((RedisCallback<Boolean>) connection -> connection.exists(key.getBytes(DEFAULT_CHARSET)));
    }


    /**
     * 删除key
     *
     * @param keys the keys
     * @return the long
     */
    public long del(final String... keys) {
        return cusTomRedisTemplate.execute((RedisCallback<Long>) connection -> {
            long R = 0;
            for (String key : keys) {
                R = connection.del(key.getBytes(DEFAULT_CHARSET));
            }
            return R;
        });
    }

    public long del(final Set<String> keys) {
        return cusTomRedisTemplate.delete(keys);
    }

    /**
     * 获取 RedisSerializer
     *
     * @return the redis serializer
     */
    protected RedisSerializer<String> getRedisSerializer() {
        return cusTomRedisTemplate.getStringSerializer();
    }

    /**
     * 对某个主键对应的值加一,value值必须是全数字的字符串
     *
     * @param key the key
     * @return the long
     */
    public long incr(final String key) {
        return cusTomRedisTemplate.execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> redisSerializer = getRedisSerializer();
            return connection.incr(redisSerializer.serialize(key));
        });
    }

    /**
     * redis List 引擎
     *
     * @return the list operations
     */
    public ListOperations<String, Object> opsForList() {
        return cusTomRedisTemplate.opsForList();
    }

    /**
     * redis List数据结构 : 将一个或多个值 value 插入到列表 key 的表头
     *
     * @param key   the key
     * @param value the value
     * @return the long
     */
    public Long leftPush(String key, Object value) {
        return opsForList().leftPush(key, value);
    }

    /**
     * redis List数据结构 : 移除并返回列表 key 的头元素
     *
     * @param key the key
     * @return the string
     */
    public Object leftPop(String key) {
        return opsForList().leftPop(key);
    }

    /**
     * redis List数据结构 :将一个或多个值 value 插入到列表 key 的表尾(最右边)。
     *
     * @param key   the key
     * @param value the value
     * @return the long
     */
    public Long in(String key, Object value) {
        return opsForList().rightPush(key, value);
    }

    /**
     * redis List数据结构 : 移除并返回列表 key 的末尾元素
     *
     * @param key the key
     * @return the string
     */
    public Object rightPop(String key) {
        return opsForList().rightPop(key);
    }


    /**
     * redis List数据结构 : 返回列表 key 的长度 ; 如果 key 不存在，则 key 被解释为一个空列表，返回 0 ; 如果 key 不是列表类型，返回一个错误。
     *
     * @param key the key
     * @return the long
     */
    public Long length(String key) {
        return opsForList().size(key);
    }


    /**
     * redis List数据结构 : 根据参数 i 的值，移除列表中与参数 value 相等的元素
     *
     * @param key   the key
     * @param i     the
     * @param value the value
     */
    public void remove(String key, long i, Object value) {
        opsForList().remove(key, i, value);
    }

    /**
     * redis List数据结构 : 将列表 key 下标为 index 的元素的值设置为 value
     *
     * @param key   the key
     * @param index the index
     * @param value the value
     */
    public void set(String key, long index, Object value) {
        opsForList().set(key, index, value);
    }

    /**
     * redis List数据结构 : 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 end 指定。
     *
     * @param key   the key
     * @param start the start
     * @param end   the end
     * @return the list
     */
    public List<Object> getList(String key, int start, int end) {
        return opsForList().range(key, start, end);
    }

    /**
     * redis List数据结构 : 批量存储
     *
     * @param key  the key
     * @param list the list
     * @return the long
     */
    public Long leftPushAll(String key, List<String> list) {
        return opsForList().leftPushAll(key, list);
    }

    /**
     * redis List数据结构 : 将值 value 插入到列表 key 当中，位于值 index 之前或之后,默认之后。
     *
     * @param key   the key
     * @param index the index
     * @param value the value
     */
    public void insert(String key, long index, Object value) {
        opsForList().set(key, index, value);
    }

    /**
     * 判断 key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public Boolean hasKey(String key) {
        try {
            return cusTomRedisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }
}
