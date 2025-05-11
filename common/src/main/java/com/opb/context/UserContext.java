package com.opb.context;

import org.springframework.stereotype.Component;

@Component
public class UserContext {

    ThreadLocal<Long> tl = new ThreadLocal<>();

    //设置id
    public void setUserId(Long id) {
        tl.set(id);
    }

    //获取id
    public Long getUserId(){
        return tl.get();
    }

    //清除id
    public void clearUserId(){
        tl.remove();
    }
}
