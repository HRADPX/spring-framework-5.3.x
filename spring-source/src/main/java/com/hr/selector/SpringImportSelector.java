package com.hr.selector;

import com.hr.service.ImportSelectorService;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-01-01
 */
public class SpringImportSelector implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[] {ImportSelectorService.class.getName()};
	}
}
