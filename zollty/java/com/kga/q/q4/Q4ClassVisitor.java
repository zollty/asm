package com.kga.q.q4;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Q4ClassVisitor extends ClassVisitor {
	public Q4ClassVisitor(int api) {
		super(api);
	}

	public Q4ClassVisitor(int api, ClassVisitor cv) {
		super(api, cv);
	}
	
//	@Override
//手写需要对java字节码有相当的了解
//	public void visitEnd() {
//		//这里添加一个public void method(){};
//		MethodVisitor mv=cv.visitMethod(Opcodes.ACC_PUBLIC, "method", "()V",null,null);
//		if (mv != null) {
//			mv.visitCode();//开始访问方法内部的代码
//			mv.visitInsn(Opcodes.RETURN);//
//			mv.visitMaxs(1, 1);//最大栈，和最大变量
//			mv.visitEnd();
//		}
//		super.visitEnd();
//	}
	//通过javap -v命令查看信息。然后转换为相应的代码 
//	public void visitEnd() {
//		//这里添加一个public void method(){};
//		MethodVisitor mv=cv.visitMethod(Opcodes.ACC_PUBLIC, "method", "()V",null,null);
//		if (mv != null) {
//			mv.visitCode();//开始访问方法内部的代码
//			mv.visitInsn(Opcodes.RETURN);//
//			mv.visitMaxs(0, 1);//最大栈，和最大变量
//			mv.visitEnd();
//		}
//		super.visitEnd();
//	}
//通过插件生成代码。简单方便
	@Override
	public void visitEnd() {
		//这里添加一个public void method(){};
		MethodVisitor mv=cv.visitMethod(Opcodes.ACC_PUBLIC, "method", "()V",null,null);
		if (mv != null) {
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitInsn(Opcodes.RETURN);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLocalVariable("this", "Lcom/kga/q/q4/Q4Class;", null, l0, l1, 0);
			mv.visitMaxs(0, 1);
			mv.visitEnd();
		}
		super.visitEnd();
	}

}
