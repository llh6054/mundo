package com.mundo.datafetcher;

import com.mundo.model.Proxy;
import com.mundo.util.AbstractIpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chubby
 * @date 2020/3/23
 */
public class WildCardDataFetcher implements DataFetcher {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void fetchData(Page page) {
        List<Proxy> proxies = new ArrayList<>();
        List<String> ips = page.getHtml().xpath("//*[@id=\"ip_list\"]/tbody/tr/td[2]/text()").all();
        List<String> ports = page.getHtml().xpath("//*[@id=\"ip_list\"]/tbody/tr/td[3]/text()").all();

        // 默认ip和port的个数是相等的 不相等则此规则不适用
        for (int i = 0; i < ips.size(); i++) {
            if (AbstractIpUtil.addIp(ips.get(i))) {
                Proxy proxy = new Proxy();
                proxy.setIp(ips.get(i));
                proxy.setPort(ports.get(i));
                proxies.add(proxy);

                if (logger.isInfoEnabled()) {
                    logger.info("add ip : {}", ips.get(i));
                }
            }
        }
        page.putField("proxies", proxies);
    }

}
