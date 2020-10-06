package utils;

import org.apache.ibatis.cache.Cache;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * @author 黄俭豪
 * @date 2020/10/6 15:08
 */
//自定义redis缓存的实现
public class RedisCache implements Cache {
    @Override
    public String getId() {
        return null;
    }

    //缓存放入值
    @Override
    public void putObject(Object o, Object o1) {

    }

    //缓存中获取数据
    @Override
    public Object getObject(Object o) {
        return null;
    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
