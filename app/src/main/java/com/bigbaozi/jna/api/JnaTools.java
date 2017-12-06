package com.bigbaozi.jna.api;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.List;

/**
 * Name: JnaTools
 * Author: dabaozi
 * Email:
 * Comment: //TODO
 * Date: 2017-11-09 14:04
 */
public interface JnaTools extends Library {

        public static final String JNA_LIBRARY_NAME = "wer";
        public static final JnaTools INSTANCE = (JnaTools) Native.loadLibrary(JnaTools.JNA_LIBRARY_NAME, JnaTools.class);
        public static class _Person extends Structure {
        public int age;
        public byte[] name = new byte[11];
        public byte[] type = new byte[20];
        public _Person() {
            super();
        }
        protected List<String> getFieldOrder() {
            return Arrays.asList("age", "name", "type");
        }
        public _Person(int age, byte name[], byte type[]) {
            super();
            this.age = age;
            if ((name.length != this.name.length))
                throw new IllegalArgumentException("Wrong array size !");
            this.name = name;
            if ((type.length != this.type.length))
                throw new IllegalArgumentException("Wrong array size !");
            this.type = type;
        }
        public static class ByReference extends _Person implements Structure.ByReference {

        };
        public static class ByValue extends _Person implements Structure.ByValue {

        };
    };
    int add(int x, int y);

    int getAge(IntBuffer age);

    int getName(ByteBuffer name, ByteBuffer s);
    int getStructInfo(JnaTools._Person Person);
}
