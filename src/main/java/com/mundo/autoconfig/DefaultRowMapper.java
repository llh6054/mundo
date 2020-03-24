package com.mundo.autoconfig;

import com.mundo.model.Fetcher;

import java.util.Map;

/**
 * @author chubby
 * @date 2020/3/24
 */
public class DefaultRowMapper implements RowMapper {

    @Override
    public Fetcher mapRow(Map<String, String> rows) {
        Fetcher fetcher = new Fetcher();
        fetcher.setWebsite(rows.get(KeyConst.SITE_NAME));
        fetcher.setIpXpath(rows.get(KeyConst.SITE_IP_XPATH));
        fetcher.setPortXpath(rows.get(KeyConst.SITE_PROT_XPATH));
        fetcher.setStartUrls(rows.get(KeyConst.SITE_START_URLS));
        return fetcher;
    }

}
