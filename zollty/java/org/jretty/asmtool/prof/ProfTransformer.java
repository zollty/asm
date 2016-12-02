/**
 * (C) 2011-2012 Alibaba Group Holding Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 2 as published by the Free Software Foundation.
 * 
 */
package org.jretty.asmtool.prof;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

/**
 * 自定义ClassFileTransformer,用于转换类字节码
 * 
 * @author zollty
 * @since 2016-11-23
 */
public class ProfTransformer implements ClassFileTransformer {

	/* (non-Javadoc)
	 * @see java.lang.instrument.ClassFileTransformer#transform(java.lang.ClassLoader, java.lang.String, java.lang.Class, java.security.ProtectionDomain, byte[])
	 */
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
	        ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		if (loader != null && Manager.getConfig().isNotNeedInjectClassLoader(loader.getClass().getName())) {
			return classfileBuffer;
		}
		if (!Manager.getConfig().isNeedInject(className)) {
			return classfileBuffer;
		}
		if (Manager.getConfig().isNotNeedInject(className)) {
			return classfileBuffer;
		}
		
		Manager.getConfig().debug(" ---- TProfiler Debug: ClassLoader:" + loader + " ---- class: " + className);

		// 记录注入类数
		WeaveFacade.instrumentClassCount.getAndIncrement();
		try {
			ClassReader reader = new ClassReader(classfileBuffer);
			ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			ClassVisitor adapter = new ProfClassAdapter(writer, className);
			reader.accept(adapter, 0);
			// 生成新类字节码
			return writer.toByteArray();
		} catch (Throwable e) {
			e.printStackTrace();
			// 返回旧类字节码
			return classfileBuffer;
		}
	}
}
