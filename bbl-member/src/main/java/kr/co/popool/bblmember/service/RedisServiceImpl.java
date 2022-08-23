package kr.co.popool.bblmember.service;

import kr.co.popool.bblcommon.error.exception.BadRequestException;
import kr.co.popool.bblmember.domain.dto.RedisDto;
import kr.co.popool.bblmember.domain.entity.MemberEntity;
import kr.co.popool.bblmember.infra.interceptor.MemberThreadLocal;
import kr.co.popool.bblmember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
    public void createData(String key, String value, long expired){
       redisTemplate.opsForValue().set(key, value, expired, TimeUnit.MILLISECONDS);
    }

    @Override
    public void deleteData(String key){
        redisTemplate.delete(key);
    }
}
