package com.mundo.processor;

import com.mundo.datafetcher.WildCardDataFetcher;
import com.mundo.datafetcher.DataFetcher;
import com.mundo.urlgenerate.WildCardUrlGenerator;
import com.mundo.urlgenerate.UrlGenerator;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author chubby
 * @date 2020/3/22
 */
public class CommonSitePageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    private UrlGenerator urlGenerator = new WildCardUrlGenerator();

    private DataFetcher dataFetcher = new WildCardDataFetcher();

    @Override
    public void process(Page page) {
        if (!urlGenerator.noMoreUrl()) {
            page.addTargetRequest(urlGenerator.generateUrl("https://www.xicidaili.com/nn"));
        }
        dataFetcher.fetchData(page);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
