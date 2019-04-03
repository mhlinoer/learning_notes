package linoer.tools;

import redis.clients.jedis.Jedis;

public class RedisTool {
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 尝试获取分布式锁
     * @param jedis     redis client
     * @param lockKey   锁
     * @param requestId 请求id, 保证一个锁由同一个人解锁，可靠性
     * @param expireTime    超时时间
     * @return          是否获取成功
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime){
        // SET_IF_NOT_EXIST保证了只有一个客户端持有锁，互斥性
        // SET_WITH_EXPIRE_TIME保证不会死锁
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if(LOCK_SUCCESS.equals(result)){
            return true;
        }
        return false;
    }

    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {
        String scripts = "if redis.call('get', KEYS[1])";
//        String result
        return false;
    }
}
