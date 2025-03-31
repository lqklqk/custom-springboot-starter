package com.lqk.myspringboot.designpattern.strategypattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author liqiankun
 * @date 2024/12/30 16:20
 * @description
 **/
@Slf4j
@Component
public class Custom001Strategy extends AbstractCustomStrategy{
    @Override
    public String getProcessorKey() {
        return "001-strategy";
    }

    @Override
    public void relStrategy(Object obj) {
        log.info("Custom001Strategy 业务代码执行。。。。");
    }
}
