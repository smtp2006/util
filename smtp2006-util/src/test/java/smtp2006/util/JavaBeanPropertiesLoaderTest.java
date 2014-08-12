/**
 * Copyright (c) 2014.
 */
package smtp2006.util;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

/**
 * @format Eclipse format:http://maven.apache.org/developers/maven-eclipse-codestyle.xml
 * @Email hua.wanghuawh@alibaba-inc.com
 * @Date 2014年8月12日 上午10:23:21
 * @Class JavaBeanPropertiesLoaderTest.java
 */
public class JavaBeanPropertiesLoaderTest {
    @Test
    public void test1() throws Exception {
        Properties properties = new Properties();
        properties.put("id", "1");
        properties.put("name", "TestBean");
        properties.put("config.id", "1.1");
        properties.put("config.id1", "1.1");
        properties.put("config.app.id", "1.1.1");
        properties.put("config.app.name", "AppName");
        TestBean target = JavaBeanPropertiesLoader.load(TestBean.class, properties);
        Assert.assertNotNull(target);
        Assert.assertEquals(target.getId(), "1");
        Assert.assertEquals(target.getName(), "TestBean");
        Assert.assertNotNull(target.getConfig());
        Assert.assertEquals(target.getConfig().getId(), "1.1");
        Assert.assertNotNull(target.getConfig().getApp());
        Assert.assertEquals(target.getConfig().getApp().getId(), "1.1.1");
        Assert.assertEquals(target.getConfig().getApp().getName(), "AppName");
        System.err.println(target);
    }

    @Test
    public void test2() throws Exception {
        TestBean target = JavaBeanPropertiesLoader.load(TestBean.class, "TestBean.properties");
        Assert.assertNotNull(target);
        Assert.assertEquals(target.getId(), "1");
        Assert.assertEquals(target.getName(), "TestBean");
        Assert.assertNotNull(target.getConfig());
        Assert.assertEquals(target.getConfig().getId(), "1.1");
        Assert.assertNotNull(target.getConfig().getApp());
        Assert.assertEquals(target.getConfig().getApp().getId(), "1.1.1");
        Assert.assertEquals(target.getConfig().getApp().getName(), "AppName");
        System.err.println(target);
    }

    public static class TestBean {
        private String id;
        private String name;
        private ConfigBean config;

        /**
         * @return the id
         */
        public final String getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public final void setId(String id) {
            this.id = id;
        }

        /**
         * @return the name
         */
        public final String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public final void setName(String name) {
            this.name = name;
        }

        /**
         * @return the config
         */
        public final ConfigBean getConfig() {
            return config;
        }

        /**
         * @param config the config to set
         */
        public final void setConfig(ConfigBean config) {
            this.config = config;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "TestBean [id=" + id + ", name=" + name + ", config=" + config + "]";
        }
    }
    public static class ConfigBean {
        private String id;
        private AppBean app;

        /**
         * @return the id
         */
        public final String getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public final void setId(String id) {
            this.id = id;
        }

        /**
         * @return the app
         */
        public final AppBean getApp() {
            return app;
        }

        /**
         * @param app the app to set
         */
        public final void setApp(AppBean app) {
            this.app = app;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "ConfigBean [id=" + id + ", app=" + app + "]";
        }
    }
    public static class AppBean {
        private String id;
        private String name;

        /**
         * @return the id
         */
        public final String getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public final void setId(String id) {
            this.id = id;
        }

        /**
         * @return the name
         */
        public final String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public final void setName(String name) {
            this.name = name;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "AppBean [id=" + id + ", name=" + name + "]";
        }
    }
}
