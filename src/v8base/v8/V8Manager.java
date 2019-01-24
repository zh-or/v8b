package v8base.v8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import v8base.support.V8BaseException;
import v8base.support.V8BaseWrap;
import v8base.support.V8BaseFactory;

public class V8Manager {
    private HashMap<String, V8BaseWrap>           execs   =   null;// 
    private V8BaseFactory                         factory =   null;
    
    public V8Manager() {
        execs = new HashMap<>();
    }
    
    public void load(String path) throws Exception {
        this.load(path, null);
    }
    
    public void load(String path, String mainFunction) throws Exception{
        File file = new File(path);
        if(!file.exists()) {
            throw new FileNotFoundException(path);
        }
        V8BaseWrap obj = execs.get(path);
        if(obj != null) {
            throw new V8BaseException(path + " 已加载");
        }
        V8Base v8 = new V8Base(path, mainFunction);
        Thread thread = new Thread(v8);
        obj = new V8BaseWrap(v8, path, thread);
        obj.run();
        execs.put(path, obj);
    }
    
    public void free(String path) throws Exception{
        V8BaseWrap obj = execs.get(path);
        if(obj == null) {
            throw new V8BaseException(path + " 未加载");
        }
        obj.stop();
    }
    
    public V8BaseWrap findObjectByPath(String path) {
        return execs.get(path);
    }
    
}
