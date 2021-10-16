package com.sqz.springmultidatasource.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
@Primary
public class DynamicDataSource  extends AbstractRoutingDataSource {

    @Autowired
    @Qualifier("readDataSource")
    private DataSource readDataSource;

    @Autowired
    @Qualifier("writeDataSource")
    private DataSource writeDataSource;

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDbType();
    }

    /**
     * 配置数据源信息
     */
    @Override
    public void afterPropertiesSet() {
        Map<Object, Object> map = new HashMap<>();
        map.put("readDataSource", readDataSource);
        map.put("writeDataSource", writeDataSource);
        setTargetDataSources(map);
        setDefaultTargetDataSource(readDataSource);
        super.afterPropertiesSet();
    }


}
