package v8base.support;


public class Message {
    private int type;
    private Object msg;
    private Object result;
    private Object attr1;
    private Object attr2;
    private Object attr3;
    private Object attr4;
    private Object wait_lock;
    private boolean isSetResult;
    
    
    
    public Message(int type) {
        this(type, null);
    }

    public Message(int type, Object msg) {
        this.type = type;
        this.msg = msg;
        this.result = null;
        this.wait_lock = new Object();
        this.isSetResult = false;
    }
    
    public void waitResult() {
        waitResult(0);
    }
    
    public void waitResult(long timeout) {
        if(isSetResult) {
            return;
        }
        synchronized (wait_lock) {
            try {
                if(result != null || isSetResult){
                    //已经有值了
                    return ;
                }
                wait_lock.wait(timeout);
            } catch (InterruptedException e) {}
        }
    }
    
    public Object getMsg(){
        return this.msg;
    }

    public int getType(){
        return this.type;
    }
    
    public void setResult(Object obj){
        synchronized (wait_lock) {
            this.isSetResult = true;
            this.result = obj;
            wait_lock.notifyAll();
        }
    }
    
    public Object getResult(){
        return this.result;
    }
 
    public Object getLock(){
        return this.wait_lock;
    }

    public Object getAttr1() {
        return attr1;
    }

    public void setAttr1(Object attr1) {
        this.attr1 = attr1;
    }

    public Object getAttr2() {
        return attr2;
    }

    public void setAttr2(Object attr2) {
        this.attr2 = attr2;
    }
    
    public Object getAttr3() {
        return attr3;
    }

    public void setAttr3(Object attr3) {
        this.attr3 = attr3;
    }
    
    public void setAttr4(Object attr4) {
        this.attr4 = attr4;
    }

    public Object getAttr4() {
        return attr4;
    }
    
    public boolean isSetResult(){
        return isSetResult;
    }
    
    public static final int  QUIT            = -1;
    public static final int  HTTP_RES        = 1;
    public static final int  TIME_OUT        = 2;
    public static final int  TIME_INTERVAL   = 3;
    public static final int  TIME_INTERVAL_R = 4;
    public static final int  EXEC_HOT_JS     = 5;
    public static final int  EXEC_CALL       = 6;
    
    public static Message createQuit(){
        return new Message(QUIT);
    }

    @Override
    public String toString() {
        return "Message [type=" + type + ", msg=" + msg + ", result=" + result + ", attr1=" + attr1 + ", attr2=" + attr2 +  "]";
    }
    
    
    
}
