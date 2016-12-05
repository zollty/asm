package com.kga.q.q1;

import java.io.IOException;

import org.objectweb.asm.ClassReader;

public class Q1 {
	private int f1;
	private int f2;
	public int getF1() {
		return f1;
	}
	public void setF1(int f1) {
		this.f1 = f1;
	}

	public int getF2() {
		return f2;
	}

	public void setF2(int f2) {
		this.f2 = f2;
	}

	public static void main(String[] args) {
		ClassReader cr = null;
		try {
			cr = new ClassReader(Q1.class.getName());
			Q1ClassVisitor qv=new Q1ClassVisitor();
			cr.accept(qv, 0);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
