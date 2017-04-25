package com.solteam.dictionary.orm;

import com.solteam.dictionary.domain.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author Burak Baldirlioglu
 * @since 4/17/2017 9:11 AM
 */
@Repository
public class DictionaryRedisRepository {


    //    private final StringRedisTemplate redisTemplate;
    private final RedisTemplate<String, Dictionary> redisTemplate;

//    private final ValueOperations<String, String> valueOps;

    private static final String KEY = "dictionary";

    private HashOperations<String, String, Dictionary> hashOps;

//    private RedisList<String> users;

//    private final RedisAtomicLong userIdCounter;
//    private final RedisAtomicLong wordIdCounter;

    @Autowired
    public DictionaryRedisRepository(RedisTemplate<String, Dictionary> redisTemplate) {
        this.redisTemplate = redisTemplate;
//        valueOps = redisTemplate.opsForValue();

//        users = new DefaultRedisList<>("users", redisTemplate);
//
//        userIdCounter = new RedisAtomicLong("global:uid", redisTemplate.getConnectionFactory());
//        wordIdCounter = new RedisAtomicLong("global:wid", redisTemplate.getConnectionFactory());
    }

    @PostConstruct
    private void init() {
        hashOps = redisTemplate.opsForHash();
    }

    public void saveDictionary(Dictionary dictionary) {
        hashOps.put(KEY, dictionary.getWord(), dictionary);
    }

    public void updateDictionary(Dictionary dictionary) {
        hashOps.put(KEY, dictionary.getWord(), dictionary);
    }

    public Dictionary findDictionary(String id) {
        return hashOps.get(KEY, id);
    }

    public Map<String, Dictionary> findAllDictionaries() {
        return hashOps.entries(KEY);
    }

    public void deleteDictionary(String id) {
        hashOps.delete(KEY, id);
    }

    /*public String addUser(String name, String password) {
        String uid = String.valueOf(userIdCounter.incrementAndGet());

        // save user as hash
        // uid -> user
        BoundHashOperations<String, String, String> userOps = redisTemplate.boundHashOps("uid:" + uid);
        userOps.put("name", name);
        userOps.put("pass", password);
        valueOps.set(KeyUtils.user(name), uid);

        users.addFirst(name);
        return addAuth(name);
    }

    public String addAuth(String name) {
        String uid = findUid(name);
        // add random auth key relation
        String auth = UUID.randomUUID().toString();
        valueOps.set(KeyUtils.auth(uid), auth);
        valueOps.set(KeyUtils.authKey(auth), uid);
        return auth;
    }

    public String findUid(String name) {
        return valueOps.get(KeyUtils.user(name));
    }

    public List<Dictionary> getDictionaries(String wid) {
        return Collections.singletonList(convertPost(wid, post(wid)));
    }

    private RedisMap<String, String> post(String pid) {
        return new DefaultRedisMap<String, String>(KeyUtils.post(pid), redisTemplate);
    }

    private RedisSet<String> words(String uid) {
        return new DefaultRedisSet<String>(KeyUtils.following(uid), redisTemplate);
    }

    private Dictionary convertPost(String wid, Map hash) {
        Dictionary dictionary = null;

        *//*Post post = postMapper.fromHash(hash);
        WebPost wPost = new WebPost(post);
        wPost.setPid(wid);
        wPost.setName(findName(post.getUid()));
        wPost.setReplyTo(findName(post.getReplyUid()));
        wPost.setContent(replaceReplies(post.getContent()));*//*
        return dictionary;
    }*/
}
