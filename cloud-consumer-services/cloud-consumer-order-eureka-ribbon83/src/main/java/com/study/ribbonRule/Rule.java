package com.study.ribbonRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> Title: Rule </p>
 * <p> Description: </p>
 * 官方强调此配置类不建议放在@ComponentScan能扫描到的包下，
 * 如主启动类@SpringBootApplication包含该注解，
 * 所以需单独放一个包
 *
 * @author lijialin
 * @since 2022-03-07
 */
@Configuration
public class Rule {
    @Bean
    public IRule getRule(){
        return new RandomRule();
    }
}
