package linoer.about_redis_lock;

public class RedisTool {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    public static boolean tryGetDistributeLock(Jedis jedis, String lockKey, String requestId, int expireTime){
        return false;
    }

}
