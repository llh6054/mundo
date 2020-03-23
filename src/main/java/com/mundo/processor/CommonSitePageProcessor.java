package com.mundo.processor;

import com.mundo.urlgenerate.CommonUrlGenerator;
import com.mundo.urlgenerate.UrlGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author chubby
 * @date 2020/3/22
 */
public class CommonSitePageProcessor implements PageProcessor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    private UrlGenerator urlGenerator = new CommonUrlGenerator();

    @Override
    public void process(Page page) {
        if (!urlGenerator.noMoreUrl()) {
            page.addTargetRequest(urlGenerator.generateUrl("https://www.xicidaili.com/nn"));
        }
        page.addTargetRequest(urlGenerator.generateUrl("https://www.xicidaili.com/nn"));
        page.putField("ip", page.getHtml().xpath("//*[@id=\"ip_list\"]/tbody/tr/td[2]/text()").all());
        page.putField("port", page.getHtml().xpath("//*[@id=\"ip_list\"]/tbody/tr/td[3]/text()").all());
        if (((List)page.getResultItems().get("ip")).size() == ((List)page.getResultItems().get("port")).size()) {
            logger.info("ResultItem: {}", page.getResultItems());
        }

    }

    @Override
    public Site getSite() {
        return site;
    }
}
