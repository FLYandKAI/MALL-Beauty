package images.cache;

import images.utils.ApplicationContextUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.DigestUtils;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * @author 黄俭豪
 * @date 2020/10/6 15:55
 */
//自定义redis缓存实现
public class MybatisRedisCache implements Cache {

    //当前放入缓存的mapper的namespace
    private final String id;

    //必须存在构造方法
    public MybatisRedisCache(String id) {
        System.out.println("==============================================所以这里执行到了没有");
        System.out.println("id: "+id);
        this.id = id;
    }

    //返回cache唯一标识
    @Override
    public String getId() {
        return this.id;
    }

    //缓存放入值
    @Override
    public void putObject(Object key, Object value) {
        System.out.println("====================key："+key.toString());
        System.out.println("====================value："+value);

    //    使用redishash类型作为缓存存储模型 key hashkey value
        getRedisTemplate().opsForHash().put(id.toString(), getKeyToMD5(key.toString()), value);
    }

    //缓存中获取数据
    @Override
    public Object getObject(Object key) {
        System.out.println("====================key："+key.toString());
        //根据key从redis的hash类型中获取数据
        return getRedisTemplate().opsForHash().get(id.toString(), getKeyToMD5(key.toString()));
    }

    //注意：这个方法为mybatis保留方法 默认没有实现
    @Override
    public Object removeObject(Object key) {
        System.out.println("========================根据指定key删除缓存");
        return null;
    }

    @Override
    public void clear() {
        System.out.println("============================清空缓存");

    //    清空id（这里的id就是mapper的namespace）
        getRedisTemplate().delete(id.toString());
    }

    @Override
    public int getSize() {
        return getRedisTemplate().opsForHash().size(id.toString()).intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }

//    封装redisTemplate
    private static RedisTemplate getRedisTemplate(){
        //    通过application工具类获取redisTemplate
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        //    更换序列化方式为String
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

//    封装一个对key进行mp5处理方法
    private String getKeyToMD5(String key){
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
