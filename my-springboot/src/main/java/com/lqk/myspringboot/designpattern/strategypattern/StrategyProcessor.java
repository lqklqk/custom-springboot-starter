package com.lqk.myspringboot.designpattern.strategypattern;

import java.util.List;

/**
 * @author liqiankun
 * @date 2024/12/30 16:10
 * @description 策略接口
 **/
public interface StrategyProcessor {
    /**
     * 获取业务处理器的key，该key一般由业务中特殊字段组成，比如：任务类型+税种
     *
     * @return 业务处理器的key
     */
    String getProcessorKey();

    /**
     * 保存数据
     *
     * @param obj 数据
     */
    void relStrategy(Object obj);
}
