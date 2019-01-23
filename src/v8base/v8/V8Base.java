package v8base.v8;

import java.util.concurrent.atomic.AtomicInteger;

import com.eclipsesource.v8.V8;

import lotus.utils.Utils;
import v8base.support.Message;
import v8base.support.MessageQueue;

public class V8Base implements Runnable{
    public static final int  STATE_NONE         =   0;
    public static final int  STATE_RUNGING      =   1;
    public static final int  STATE_STOPING      =   2;
    public static final int  STATE_STOPED       =   3;
    
    protected String                  path                 =   null;
    protected String                  mainFunction         =   null;
    protected int                     state                =   STATE_NONE;
    protected MessageQueue            messageQueue         =   null;
    
    protected V8                      v8                   =  null;
    protected AtomicInteger           nuclear              = new AtomicInteger(0);//内存泄露控制 :)
    
    /**
     * mainFunction 默认为 main
     * @param path
     */
    public V8Base(String path) {
        this(path, "main");
    }
    
    public V8Base(String path, String mainFunction) {
        this.path = path;
        this.mainFunction = mainFunction;
        this.messageQueue = new MessageQueue();
    }
    
    public void postMessage(Message msg) {
        this.messageQueue.push(msg);
    }
    
    
    @Override
    public void run() {
        
        v8 = V8.createV8Runtime();
        
        
        while(state == STATE_RUNGING) {
            
            Message m = messageQueue.getOne(1000);
            if(m != null) {
                int type = m.getType();
                switch(type) {
                    
                }
            }
            
        }
        
        while(nuclear.get() > 0) {//等待内存释放
            Utils.SLEEP(50);
        }
        
    }
    
    
    public String call(Object ... arguments) {
        
        return null;
    }
    
    public int state() {
        return state;
    }
    
    
    
}
