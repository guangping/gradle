package io.lance.gradle.common.shiro.session;

import io.lance.gradle.common.cache.redis.RedisCache;
import io.lance.gradle.common.core.util.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lance.
 * @time: 2018-04-27 17:35
 * @desc:
 */
public class RedisSessionDAO extends AbstractSessionDAO {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private RedisCache redisCache;

    private static final String KEY_PREFIX = "shiro_redis_session:";


    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            logger.error("session id is null");
            return null;
        }
        return this.redisCache.getObj(getKey(sessionId), Session.class);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        this.saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            logger.error("session or session id is null");
            return;
        }
        this.redisCache.del(getKey(session));
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<Session>();
       /* Set<byte[]> keys = redisManager.keys(KEY_PREFIX + "*");
        if(keys != null && keys.size()>0){
            for(byte[] key : keys){
                Session s = (Session)SerializerUtil.deserialize(redisManager.get(SerializerUtil.deserialize(key)));
                sessions.add(s);
            }
        }*/
        return sessions;
    }

    private void saveSession(Session session) throws UnknownSessionException {
        if (session == null || session.getId() == null) {
            logger.error("session or session id is null");
            return;
        }
        //设置过期时间
        int expireTime = 1800000;
        session.setTimeout(expireTime);
        this.redisCache.set(getKey(session), JsonUtils.toJSONString(session), expireTime);
    }

    private String getKey(Session session) {
        return this.getKey(session.getId());
    }

    private String getKey(Serializable sessionId) {
        StringBuffer buffer = new StringBuffer(30);
        buffer.append(KEY_PREFIX);
        buffer.append(sessionId);
        return buffer.toString();
    }
}
