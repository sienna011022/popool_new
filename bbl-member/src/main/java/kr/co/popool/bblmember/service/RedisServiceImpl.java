package kr.co.popool.bblmember.service;

import kr.co.popool.bblmember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService{

    private final MemberRepository memberRepository;
    private final RedisTemplate<String, String> redisTemplate;

    //TODO : redis service 기능
}
