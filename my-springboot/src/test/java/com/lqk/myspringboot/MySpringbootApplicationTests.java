package com.lqk.myspringboot;

import com.lqk.myspringboot.designpattern.strategypattern.StrategyProcessor;
import com.lqk.myspringboot.designpattern.strategypattern.StrategyResolver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MySpringbootApplicationTests {
    @Autowired
    private StrategyResolver resolver;

    @Test
    void strategyTest() {
        String key = "001-strategy";
        StrategyProcessor processor = resolver.getProcessor(key);
        processor.relStrategy(new Object());
    }

}
