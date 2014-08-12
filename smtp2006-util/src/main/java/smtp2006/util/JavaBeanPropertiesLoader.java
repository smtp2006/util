/**
 * Copyright (c) 2014.
 */
package smtp2006.util;

import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通过Properties配置文件来加载JavaBean.<br>
 * 
 * <pre>
 * <ul>
 *  <li>1. 普通属性通过调用set方法即可<li>
 *  <li>2. 对于嵌套属性, 在BeanUtilsBean的基础上扩展, 当嵌套属性=null时, 提前初始化<li>
 * </ul>
 * </pre>
 * 
 * @Email smtp2006@126.com
 * @Date 2014年8月12日 上午10:02:42
 * @Class JavaBeanPropertiesLoader.java
 */
public abstract class JavaBeanPropertiesLoader {
    static final Logger LOGGER = LoggerFactory.getLogger(JavaBeanPropertiesLoader.class);
    static final BeanUtilsBean3 proxy = new BeanUtilsBean3();

    /**
     * 通过Properties配置文件生成JavaBean
     * 
     * @param klass 生成JavaBean的类型
     * @param properties JavaBean的配置
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static <T> T load(Class klass, String properties) throws Exception {
        LOGGER.info("//New Instance[{}] with {}", klass.getName(), properties);
        Properties props = new Properties();
        props.load(JavaBeanPropertiesLoader.class.getClassLoader().getResourceAsStream(properties));
        return load(klass, props);
    }

    /**
     * 通过Properties配置文件生成JavaBean
     * 
     * @param klass 生成JavaBean的类型
     * @param properties JavaBean的配置
     * @return
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T> T load(Class klass, Properties properties) throws Exception {
        LOGGER.info("//New Instance[{}] with {}", klass.getName(), properties);
        // 构造对象
        Object target = klass.newInstance();

        // 迭代Properties配置

        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            LOGGER.info("//New Instance[{}] set {} with '{}'", klass.getName(), entry.getKey()
                    .toString(), entry.getValue());
            try {
                proxy.setProperty(target, entry.getKey().toString(), entry.getValue());
            } catch (Exception e) {
                LOGGER.info("//New Instance[{}] set {} failure '{}'", klass.getName(), entry
                        .getKey().toString(), entry.getValue());
            }

        }
        return (T) target;
    }
}
