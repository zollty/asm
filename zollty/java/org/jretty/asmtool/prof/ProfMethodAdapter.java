/**
 * (C) 2011-2012 Alibaba Group Holding Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 2 as published by the Free Software Foundation.
 * 
 */
package org.jretty.asmtool.prof;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;

import org.jretty.asmtool.prof.support.MethodCache;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * ASM方法适配器
 * 
 * @author zollty
 * @since 2016-11-23
 */
public class ProfMethodAdapter extends MethodVisitor {
    /**
     * 方法ID
     */
    private int mMethodId = 0;

    /**
     * @param visitor
     * @param fileName
     * @param className
     * @param methodName
     */
    public ProfMethodAdapter(final int api, final MethodVisitor visitor, String fileName, String className,
            String methodName) {
        super(api, visitor);
        mMethodId = MethodCache.createMethodInfo(fileName, className, methodName);
        // 记录方法数
        WeaveFacade.instrumentMethodCount.getAndIncrement();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.objectweb.asm.MethodAdapter#visitCode()
     */
    public void visitCode() {
        this.visitLdcInsn(mMethodId);
        this.visitMethodInsn(INVOKESTATIC, Manager.getConfig().getInjectClass(),
                Manager.getConfig().getInjectStartMethod(), "(I)V", false);
        super.visitCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.objectweb.asm.MethodAdapter#visitLineNumber(int, org.objectweb.asm.Label)
     */
    public void visitLineNumber(final int line, final Label start) {
        MethodCache.UpdateLineNum(mMethodId, line);
        super.visitLineNumber(line, start);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.objectweb.asm.MethodAdapter#visitInsn(int)
     */
    public void visitInsn(int inst) {
        switch (inst) {
        case Opcodes.ARETURN:
        case Opcodes.DRETURN:
        case Opcodes.FRETURN:
        case Opcodes.IRETURN:
        case Opcodes.LRETURN:
        case Opcodes.RETURN:
        case Opcodes.ATHROW:
            this.visitLdcInsn(mMethodId);
            this.visitMethodInsn(INVOKESTATIC, Manager.getConfig().getInjectClass(),
                    Manager.getConfig().getInjectEndMethod(), "(I)V", false);
            break;
        default:
            break;
        }

        super.visitInsn(inst);
    }

}