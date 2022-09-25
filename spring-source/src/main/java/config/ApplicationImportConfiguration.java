package config;

import com.hr.service.IocService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-02-17
 */
@Configuration
@Import(IocService.class)
public class ApplicationImportConfiguration {
}
