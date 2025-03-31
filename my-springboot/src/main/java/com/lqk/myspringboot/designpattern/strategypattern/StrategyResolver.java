package com.lqk.myspringboot.designpattern.strategypattern;

import jakarta.annotation.PostConstruct;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author liqiankun
 * @date 2024/12/30 16:12
 * @description 策略解析器
 **/
@Component
public class StrategyResolver {
    @Autowired
    private Set<StrategyProcessor> processorSet;

    private Map<String, StrategyProcessor> processorMap;
    // 初始化加载策略
    @PostConstruct
    public void init() {
        if (CollectionUtils.isNotEmpty(processorSet)) {
            processorMap = new HashMap<>(processorSet.size());
            for (StrategyProcessor processor : processorSet) {
                processorMap.put(processor.getProcessorKey(), processor);
            }
        }
    }
    // 根据key获取对应策略
    public StrategyProcessor getProcessor(String key) {
        return processorMap.get(key);
    }
}
