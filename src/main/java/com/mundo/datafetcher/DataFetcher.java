package com.mundo.datafetcher;

import us.codecraft.webmagic.Page;

/**
 * @author chubby
 * @date 2020/3/23
 */
public interface DataFetcher {

    /**
     * 数据提取，将提取的数据放置到page中
     * @param page
     */
    void fetchData(Page page);

}
