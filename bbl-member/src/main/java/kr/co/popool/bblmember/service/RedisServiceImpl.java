package kr.co.popool.bblmember.service;

import kr.co.popool.bblmember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService{

    private final MemberRepository memberRepository;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public String getValue(String key){
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(key);
    }

    @Override
    public void createData(String key, String value){
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(key, value);
    }

    @Override
    public void deleteData(String key){
        redisTemplate.delete(key);
    }
}
