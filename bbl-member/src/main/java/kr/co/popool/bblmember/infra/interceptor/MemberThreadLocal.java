package kr.co.popool.bblmember.infra.interceptor;


public class MemberThreadLocal  {

    private static final ThreadLocal<String> memberThreadLocal;

    static {
        memberThreadLocal = new ThreadLocal<>();
    }

    public static void set(String identity){
        memberThreadLocal.set(identity);
    }

    public static void remove(){
        memberThreadLocal.remove();
    }

    public static String get(){
        return memberThreadLocal.get();
    }
}
