package v8base.support;

import v8base.v8.V8Base;

public class V8BaseWrap {
    public V8Base v8;
    public String path;
    public Thread thread;
    public boolean isRun  = false;
    
    public V8BaseWrap(V8Base v8, String path, Thread thread) {
        this.v8 = v8;
        this.path = path;
        this.thread = thread;
    }
    
    public int state() {
        return v8.state();
    }
    
    public void run() {
        this.thread.start();
        isRun = true;
    }
    
    public void stop() {
        v8.postMessage(new Message(Message.QUIT));
        isRun = false;
    }
    
}
