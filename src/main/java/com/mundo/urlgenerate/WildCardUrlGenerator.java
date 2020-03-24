package com.mundo.urlgenerate;

/**
 * @author chubby
 * @date 2020/3/22
 */
public class WildCardUrlGenerator implements UrlGenerator {

    private static final Integer INCREMENT = 1;

    private Integer curPage = 1;

    private Integer maxPage = 1000;

    private Boolean noMoreUrl = false;

    public WildCardUrlGenerator() {
        new WildCardUrlGenerator(this.maxPage, this.curPage);
    }

    public WildCardUrlGenerator(Integer maxPage) {
        new WildCardUrlGenerator(maxPage, this.curPage);
    }

    public WildCardUrlGenerator(Integer maxPage, Integer curPage) {
        this.maxPage = maxPage;
        this.curPage = curPage;
    }

    @Override
    public String generateUrl(String webSite) {
        if (curPage >= maxPage) {
            noMoreUrl = true;
            return "";
        }
        curPage += INCREMENT;
        return webSite + "/" + curPage;
    }

    @Override
    public Boolean noMoreUrl() {
        return noMoreUrl;
    }

}
