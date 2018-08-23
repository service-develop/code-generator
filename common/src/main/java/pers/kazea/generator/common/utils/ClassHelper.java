package pers.kazea.generator.common.utils;

import org.apache.commons.lang3.StringUtils;

public class ClassHelper {

    /**
     * 获取类名
     *
     * @param classPath
     * @return
     */
    public static String getSuperClassName(String classPath) {
        if (StringUtils.isEmpty(classPath)) {
            return null;
        }
        return classPath.substring(classPath.lastIndexOf(StringPool.DOT) + 1);
    }

}
