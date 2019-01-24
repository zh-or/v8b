package v8base.support;

import v8base.v8.V8Base;

public abstract class V8BaseFactory {
    
    public V8Base create(String path, String mainFunction) {
        return new V8Base(path, mainFunction);
    }
    
    public V8BaseWrap findObjByName() {
        
        return null;
    }
}
