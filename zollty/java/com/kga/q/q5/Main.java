package com.kga.q.q5;

import java.io.FileOutputStream;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassWriter;

import com.kga.q.q4.Q4;

public class Main {
	private static java.lang.reflect.Method defineClass1;

	static {
		try {
			Class cl = Class.forName("java.lang.ClassLoader");
			defineClass1 = cl.getDeclaredMethod(

			"defineClass", new Class[] { String.class, byte[].class, int.class, int.class });

		} catch (Exception e) {
			throw new RuntimeException("cannot initialize");
		}
	}

	protected static ClassLoader getClassLoader(Class clasz) {
		ClassLoader loader = null;
		loader = clasz.getClassLoader();
		if (loader == null) {
			loader = Thread.currentThread().getContextClassLoader();
			if (loader == null)
				loader = ClassLoader.getSystemClassLoader();
		}

		return loader;
	}

	public static void main(String[] args) throws Exception {
		setAccessible(defineClass1, true);//修改权限
		ClassLoader loader = getClassLoader(Main.class);
		byte[] bytes = Q5.kga();
		defineClass1.invoke(loader, new Object[] { "com.kga.q.q5.Kga", bytes, 0, bytes.length });
		writeFile(bytes, "com.kga.q.q5.Kga");
		bytes = Q5.Hello();
		defineClass1.invoke(loader, new Object[] { "com.kga.q.q5.Hello", bytes, 0, bytes.length });
		Class clasz = Class.forName("com.kga.q.q5.Hello");
		Object obj = clasz.newInstance();
		Method m = obj.getClass().getDeclaredMethod("say", null);
		m.invoke(obj, null);
		setAccessible(defineClass1, false);//修改权限
	}

	static void setAccessible(final AccessibleObject ao, final boolean accessible) {
		if (System.getSecurityManager() == null)
			ao.setAccessible(accessible);
		else {
			AccessController.doPrivileged(new PrivilegedAction() {
				public Object run() {
					ao.setAccessible(accessible);
					return null;
				}
			});
		}
	}

	private static void writeFile(byte[] bytes, String name) throws Exception {
		FileOutputStream fos;
		fos = new FileOutputStream(name + ".class");
		fos.write(bytes);
		fos.close();
	}

}
