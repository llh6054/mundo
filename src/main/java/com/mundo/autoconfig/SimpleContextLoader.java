package com.mundo.autoconfig;

import com.mundo.model.Fetcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chubby
 * @date 2020/3/23
 */
public class SimpleContextLoader implements ContextLoader {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Map<String, Fetcher> contextMap = new HashMap<>(100);

    private RowMapper rowMapper = new DefaultRowMapper();

    /**
     * 配置默认存放路径
     */
    private static final String CONFIG = "config";

    @Override
    public void loadContext() {
        URL url = Thread.currentThread().getContextClassLoader().getResource(CONFIG);
        File file = null;
        try {
            file = new File(url.toURI());
            File[] configs = file.listFiles();
            Map<String, String> kv = new HashMap<>(20);
            for (int i = 0; i < configs.length; i++) {
                BufferedReader br = new BufferedReader(new FileReader(configs[i]));
                String row;
                while ((row = br.readLine()) != null) {
                    if (row.startsWith("#")) {
                        continue;
                    } else {
                        generateKv(row, kv);
                    }
                }
                Fetcher fetcher = (Fetcher)rowMapper.mapRow(kv);
                contextMap.put(fetcher.getWebsite(), fetcher);
            }
        }
        catch (URISyntaxException | IOException e) {
            logger.error("加载配置出错， url: {}", url);
        }
    }

    private void generateKv(String row, Map<String, String> destination) {
        String[] kv = row.split("=");
        destination.put(kv[0], kv[1]);
    }

    public Map<String, Fetcher> context() {
        return contextMap;
    }

}
