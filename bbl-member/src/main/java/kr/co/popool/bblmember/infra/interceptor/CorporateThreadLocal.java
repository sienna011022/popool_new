package kr.co.popool.bblmember.infra.interceptor;

import kr.co.popool.bblmember.domain.entity.CorporateEntity;

public class CorporateThreadLocal {

    private static final ThreadLocal<CorporateEntity> corporateThreadLocal;

    static {
        corporateThreadLocal = new ThreadLocal<>();
    }

    public static void set(CorporateEntity memberEntity){
        corporateThreadLocal.set(memberEntity);
    }

    public static void remove(){
        corporateThreadLocal.remove();
    }

    public static CorporateEntity get(){
        return corporateThreadLocal.get();
    }
}
