package com.kga.q.q2;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class Q2ClassVisitor extends ClassVisitor {
	

	public Q2ClassVisitor(int api) {
		super(api);
	}

	public Q2ClassVisitor(int api, ClassVisitor cv) {
		super(api, cv);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		if(name.equals("toBeRemove")){
			System.out.println("" + name + desc+"被移除");
			return null;
		}
		return super.visitMethod(access, name, desc, signature, exceptions);
	
	}
	
}
