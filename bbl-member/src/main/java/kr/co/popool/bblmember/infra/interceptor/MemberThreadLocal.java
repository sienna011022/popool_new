package kr.co.popool.bblmember.infra.interceptor;


import kr.co.popool.bblmember.domain.entity.MemberEntity;

public class MemberThreadLocal  {

    private static final ThreadLocal<MemberEntity> memberThreadLocal;

    static {
        memberThreadLocal = new ThreadLocal<>();
    }

    public static void set(MemberEntity memberEntity){
        memberThreadLocal.set(memberEntity);
    }

    public static void remove(){
        memberThreadLocal.remove();
    }

    public static MemberEntity get(){
        return memberThreadLocal.get();
    }
}
