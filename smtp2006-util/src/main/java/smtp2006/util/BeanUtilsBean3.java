/**
 * Copyright (c) 2014.
 */
package smtp2006.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean2;
import org.apache.commons.beanutils.expression.Resolver;

/**
 * @Email smtp2006@126.com
 * @Date 2014年8月12日 上午10:45:31
 * @Class BeanUtilsBean3.java
 */
public class BeanUtilsBean3 extends BeanUtilsBean2 {
    public void setProperty(Object bean, String name, Object value) throws IllegalAccessException,
            InvocationTargetException {
        try {
            checkNexted(bean, name);
        } catch (Exception e) {

        }
        super.setProperty(bean, name, value);
    }

    /**
     * check name是否是嵌套属性,如果是,并且嵌套对象=null, 构造嵌套对象
     * 
     * @param bean
     * @param name
     * @throws Exception
     */
    private void checkNexted(Object bean, String name) throws Exception {
        // Resolve any nested expression to get the actual target bean
        Object target = bean;
        Resolver resolver = getPropertyUtils().getResolver();
        while (resolver.hasNested(name)) {
            try {
                target = getPropertyUtils().getProperty(target, resolver.next(name));
                if (target == null) { // the value of a nested property is null
                    target = newInstanceNexted(bean, resolver.next(name));
                }
                name = resolver.remove(name);
                checkNexted(target, name);
            } catch (NoSuchMethodException e) {
                return; // Skip this property setter
            }
        }
    }

    private Object newInstanceNexted(Object bean, String name) throws Exception {
        Object nextedObject = getPropertyUtils().getPropertyType(bean, name).newInstance();
        getPropertyUtils().setProperty(bean, name, nextedObject);
        return nextedObject;
    }
}
