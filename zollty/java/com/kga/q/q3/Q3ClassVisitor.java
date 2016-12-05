package com.kga.q.q3;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class Q3ClassVisitor extends ClassVisitor {
	public Q3ClassVisitor(int api) {
		super(api);
	}

	public Q3ClassVisitor(int api, ClassVisitor cv) {
		super(api, cv);
	}
	@Override
	public void visitEnd() {
		//这里添加一个private int f1;
		FieldVisitor fv = cv.visitField(Opcodes.ACC_PRIVATE,"f1",Type.INT_TYPE.toString(), null, null);
		if (fv != null) {
			fv.visitEnd();
		}
		super.visitEnd();
	}

}
