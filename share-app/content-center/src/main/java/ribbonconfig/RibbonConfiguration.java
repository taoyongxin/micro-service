package ribbonconfig;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;

/**
 * @author Tao
 * @version 1.0
 * @ClassName RibbonConfiguration
 * @Description TODO
 * @date 2020-09-25 11:32
 **/
//注释掉@Configuration注解下面不会产生随机的规则方法了
//@Configuration
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule(){
        //产生随机的规则
        return new RandomRule();
    }
}

