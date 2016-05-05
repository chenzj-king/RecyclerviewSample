package cn.chenzhongjin.recyclerviewsample.utils;

import java.util.Collection;
import java.util.Map;

public class CollectionUtils {

    public static boolean isNotNull(Collection<?> collection) {
        if (collection != null && collection.size() > 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(Map<?, ?> collection) {
        if (collection != null && collection.size() > 0) {
            return true;
        }
        return false;
    }
}
