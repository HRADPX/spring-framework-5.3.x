package config;

import com.hr.editor.ExoticTypeEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-27
 */
@Configuration
@ComponentScan(basePackages = "com.hr.editor")
public class ApplicationEditorConfiguration {

	@Bean
	public static CustomEditorConfigurer customEditorConfigurer() {
		CustomEditorConfigurer configurer = new CustomEditorConfigurer();
//		Map<Class<?>, Class<? extends PropertyEditor>> map = new HashMap<>(2);
//		map.put(ExoticType.class, ExoticTypeEditor.class);
//		configurer.setCustomEditors(map);
		configurer.setPropertyEditorRegistrars(new PropertyEditorRegistrar[] {new ExoticTypeEditorRegistrar()});
		return configurer;
	}

}
