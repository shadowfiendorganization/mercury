package com.atlandes.auth.shiro.util;

import com.alibaba.fastjson.JSON;

import java.io.*;

/**
 * Created by XD.Wang on 2017/5/28.
 * 序列化工具类
 */
@SuppressWarnings("unchecked")
public class SerializeUtils {

    private static final Class<?> CLAZZ = SerializeUtils.class;

    public static byte[] serialize(Object value) {
        if (value == null) {
            throw new NullPointerException("Can't serialize null");
        }
        byte[] rv = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(value);
            os.close();
            bos.close();
            rv = bos.toByteArray();
        } catch (Exception e) {
            ShiroLogUtils.fmtError(CLAZZ, e, "serialize error %s", JSON.toJSONString(value));
        } finally {
            close(os);
            close(bos);
        }
        return rv;
    }

    public static Object deserialize(byte[] in) {
        return deserialize(in, Object.class);
    }

    public static <T> T deserialize(byte[] in, Class<T>... requiredType) {
        Object rv = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        try {
            if (in != null) {
                bis = new ByteArrayInputStream(in);
                is = new ObjectInputStream(bis);
                rv = is.readObject();
            }
        } catch (Exception e) {
            ShiroLogUtils.fmtError(CLAZZ, e, "serialize error %s", in);
        } finally {
            close(is);
            close(bis);
        }
        return (T) rv;
    }

    private static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                ShiroLogUtils.fmtError(CLAZZ, "close stream error");
            }
        }
    }

}
