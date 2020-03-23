package com.mundo.autoconfig;

import java.net.URL;

/**
 * @author chubby
 * @date 2020/3/23
 */
public class SimpleContextLoader implements ContextLoader {

    @Override
    public void loadContext() {
        URL url = Thread.currentThread().getContextClassLoader().getResource("config");
        System.out.println(url);
    }

}
