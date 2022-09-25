package config;

import com.hr.conditional.ConditionalService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-05-02
 */
@Configuration
public class ApplicationConditionalConfiguration {

	@Bean
	public ConditionalService conditionalService() {
		return new ConditionalService();
	}
}
