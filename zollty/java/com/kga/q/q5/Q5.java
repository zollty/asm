package com.kga.q.q5;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Q5 implements Opcodes {
	/**
	 * <pre>
	 * package com.kga.q.q5;
	 * 
	 * public class Kga {
	 * 	public static String name = &quot;kga&quot;;
	 * }
	 * </pre>
	 * 
	 * @return
	 * @throws Exception
	 */
	public static byte[] kga() throws Exception {
		ClassWriter cw = new ClassWriter(0);
		FieldVisitor fv;
		MethodVisitor mv;
		AnnotationVisitor av0;
		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "com/kga/q/q5/Kga", null, "java/lang/Object", null);
		{
			fv = cw.visitField(ACC_PUBLIC + ACC_STATIC, "name", "Ljava/lang/String;", null, null);
			fv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(4, l0);
			mv.visitLdcInsn("kga");
			mv.visitFieldInsn(PUTSTATIC, "com/kga/q/q5/Kga", "name", "Ljava/lang/String;");
			mv.visitInsn(RETURN);
			mv.visitMaxs(1, 0);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(3, l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
			mv.visitInsn(RETURN);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLocalVariable("this", "Lcom/kga/q/q5/Kga;", null, l0, l1, 0);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}
		cw.visitEnd();
		return cw.toByteArray();
	}

	/**
	 * <pre>
	 * package com.kga.q.q5;
	 * 
	 * public class Hello {
	 * 	public void say() {
	 * 		System.out.println(&quot;hello &quot; + Kga.name);
	 * 		System.out.println(1/1)
	 * 	}
	 * }
	 * </pre>
	 * 
	 * @return
	 */
	public static byte[] Hello() {

		ClassWriter cw = new ClassWriter(0);
		FieldVisitor fv;
		MethodVisitor mv;
		AnnotationVisitor av0;

		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "com/kga/q/q5/Hello", null, "java/lang/Object", null);

		cw.visitSource("Hello.java", null);
		{
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(3, l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
			mv.visitInsn(RETURN);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLocalVariable("this", "Lcom/kga/q/q5/Hello;", null, l0, l1, 0);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC, "say", "()V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(5, l0);
			mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
			mv.visitInsn(DUP);
			mv.visitLdcInsn("hello ");
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "(Ljava/lang/String;)V");
			mv.visitFieldInsn(GETSTATIC, "com/kga/q/q5/Kga", "name", "Ljava/lang/String;");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLineNumber(6, l1);
			mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitInsn(ICONST_1);
			mv.visitInsn(ICONST_1);
			mv.visitInsn(IDIV);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V");
			Label l2 = new Label();
			mv.visitLabel(l2);
			mv.visitLineNumber(7, l2);
			mv.visitInsn(RETURN);
			Label l3 = new Label();
			mv.visitLabel(l3);
			mv.visitLocalVariable("this", "Lcom/kga/q/q5/Hello;", null, l0, l3, 0);
			mv.visitMaxs(4, 1);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}
}
