package com.mundo.autoconfig;

import com.mundo.model.Fetcher;

import java.util.Map;


/**
 * @author chubby
 * @date 2020/3/24
 */
public interface RowMapper {

    /**
     * 定义数据之间的映射关系
     * @param rows
     * @return
     */
    Fetcher mapRow(Map<String, String> rows);

}
