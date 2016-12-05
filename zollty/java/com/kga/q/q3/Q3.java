package com.kga.q.q3;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import com.kga.q.SampleClassLoader;
import com.kga.q.q1.Q1ClassVisitor;

public class Q3 {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String clasName=Q3Class.class.getName();
		printlnClass(null);
		ClassReader cr=new ClassReader(clasName);
		ClassWriter qw=new ClassWriter(0);
		SampleClassLoader sl=new SampleClassLoader();	
		System.out.println(	"原来的属性数量"+sl.loadClass(clasName).getDeclaredFields().length);
		Q3ClassVisitor q2ClassVisitor=new Q3ClassVisitor(Opcodes.ASM4, qw);
		System.out.println("===========================================");
		cr.accept(q2ClassVisitor, 0);
		System.out.println("==========================================");
		System.out.println(	"添加后的属性数量"+sl.loadClass(clasName,qw.toByteArray()).getDeclaredFields().length);
		printlnClass(qw.toByteArray());
	}
	private static void printlnClass(byte[] b){
		
		ClassReader cr = null;
		try {
			if(b==null){
				cr = new ClassReader(Q3Class.class.getName());
			}else{
				cr=new ClassReader(b);
			}
			Q1ClassVisitor qv=new Q1ClassVisitor();
			cr.accept(qv, 0);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
