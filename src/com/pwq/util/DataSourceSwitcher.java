package com.pwq.util;

import org.springframework.util.Assert;

public class DataSourceSwitcher {
    @SuppressWarnings("rawtypes")
    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();

    @SuppressWarnings("unchecked")
    public static void setDataSource(DataSourceType dataSource) {
        Assert.notNull(dataSource, "dataSource cannot be null");
        contextHolder.set(dataSource);
    }

    public static void setMaster() {
        clearDataSource();
    }

    public static void setSlave() {
        setDataSource(DataSourceType.SLAVE);
    }

    public static DataSourceType getDataSource() {
        return contextHolder.get();
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }
}