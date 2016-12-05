package com.kga.q.q1;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Q1ClassVisitor extends ClassVisitor {
	public Q1ClassVisitor() {
		super(Opcodes.ASM4);
	}
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		System.out.println("" + desc + " " + name);
		return null;
	}
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		System.out.println("" + name + desc);
		return null;
	}
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		System.out.println(name + " extends " + superName + " {");
	}
	

	public void visitEnd() {
		System.out.println("}");
	}
}