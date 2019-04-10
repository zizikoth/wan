package com.memo.iframe.tools.rxbus;

import android.util.Log;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-02-22 16:36
 */
final class RxBusUtils {

    private RxBusUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断对象是否相等
     *
     * @param o1 对象1
     * @param o2 对象2
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    static boolean equals(Object o1, Object o2) {
        return o1 == o2 || (o1 != null && o1.equals(o2));
    }

    /**
     * Require the objects are not null.
     *
     * @param objects The object.
     * @throws NullPointerException if any object is null in objects
     */
    static void requireNonNull(final Object... objects) {
        if (objects == null) {
            throw new NullPointerException();
        }
        for (Object object : objects) {
            if (object == null) {
                throw new NullPointerException();
            }
        }
    }

    static <T> Class<T> getTypeClassFromParadigm(final RxBus.Callback<T> callback) {
        if (callback == null) {
            return null;
        }
        Type[] genericInterfaces = callback.getClass().getGenericInterfaces();
        Type type;
        if (genericInterfaces.length == 1) {
            type = genericInterfaces[0];
        } else {
            type = callback.getClass().getGenericSuperclass();
        }
        if (type == null) {
            return null;
        }
        type = ((ParameterizedType) type).getActualTypeArguments()[0];
        while (type instanceof ParameterizedType) {
            type = ((ParameterizedType) type).getRawType();
        }
        String className = type.toString();
        if (className.startsWith("class ")) {
            className = className.substring(6);
        } else if (className.startsWith("interface ")) {
            className = className.substring(10);
        }
        try {
            //noinspection unchecked
            return (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    static Class getClassFromObject(final Object obj) {
        if (obj == null) {
            return null;
        }
        Class objClass = obj.getClass();
        if (objClass.isAnonymousClass() || objClass.isSynthetic()) {
            Type[] genericInterfaces = objClass.getGenericInterfaces();
            String className;
            if (genericInterfaces.length == 1) {
                Type type = genericInterfaces[0];
                while (type instanceof ParameterizedType) {
                    type = ((ParameterizedType) type).getRawType();
                }
                className = type.toString();
            } else {// abstract class or lambda
                Type type = objClass.getGenericSuperclass();
                while (type instanceof ParameterizedType) {
                    type = ((ParameterizedType) type).getRawType();
                }
                if (type == null) {
                    return null;
                }
                className = type.toString();
            }

            if (className.startsWith("class ")) {
                className = className.substring(6);
            } else if (className.startsWith("interface ")) {
                className = className.substring(10);
            }
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return objClass;
    }

    static void logW(String msg) {
        Log.w("RxBus", msg);
    }

    static void logE(String msg) {
        Log.e("RxBus", msg);
    }
}
