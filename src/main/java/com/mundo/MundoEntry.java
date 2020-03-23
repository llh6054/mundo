package com.mundo;

import com.mundo.processor.CommonSitePageProcessor;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

/**
 * @author chubby
 * @date 2020/3/20
 */
public class MundoEntry {

    public static void main(String[] args) {
        Spider.create(new CommonSitePageProcessor()).addPipeline(new ConsolePipeline()).addUrl("https://www.xicidaili.com/nn").start();
    }

}
