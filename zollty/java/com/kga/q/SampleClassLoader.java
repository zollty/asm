package com.kga.q;

public class SampleClassLoader extends ClassLoader {
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {

		return super.loadClass(name);
	}
	public Class<?> loadClass(String name,byte[] bytes) throws ClassNotFoundException {

		return super.defineClass(bytes, 0, bytes.length);
	}
}
