package com.sqz.demo.imports;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{"com.sqz.demo.pojo.Dog"};
	}
}
