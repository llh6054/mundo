package com.mundo.urlgenerate;

/**
 * @author chubby
 * @date 2020/3/22
 */
public interface UrlGenerator {

    /**
     * 生成url
     * @param webSite
     * @return
     */
    String generateUrl(String webSite);

    /**
     * 是否到达最后一个需要爬取的url
     * @return
     */
    Boolean noMoreUrl();
}
