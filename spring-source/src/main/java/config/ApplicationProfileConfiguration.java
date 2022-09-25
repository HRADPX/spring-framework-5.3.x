package config;

import com.hr.profile.ProfileBean;
import com.hr.profile.ProfileConditionBean;
import org.springframework.context.annotation.*;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-26
 *
 *
 */
@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationProfileConfiguration {

	@Bean
	public ProfileConditionBean testProfileConditionBean(ProfileBean profileBean) {
		return new ProfileConditionBean(profileBean.getId(), profileBean.getName());
	}

	@Bean
	@Profile("development")
	public ProfileConditionBean testProfileConditionBean() {
		return new ProfileConditionBean(1, "test");
	}

	@Bean
	@Profile("production")
	public ProfileConditionBean prodProfileConditionBean() {
		return new ProfileConditionBean(1, "production");
	}

	@Bean
	public ProfileBean profileBean() {
		return new ProfileBean(2, "profileBean");
	}
}

