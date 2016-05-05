package cn.chenzhongjin.recyclerviewsample.utils;

import java.util.Comparator;

import cn.chenzhongjin.recyclerviewsample.entity.City;

/**
 * @文件名: PinyinComparator
 * @功能描述: 按照拼音排序
 * @Create by chenzj on 2015-12-15 下午5:45:49
 * @email: chenzj@sq580.com
 * @修改记录:
 */
public class CityComparator implements Comparator<City> {

    public int compare(City object1, City object2) {
        if (object1.getTitleName().equals("@") || object2.getTitleName().equals("#")) {
            return -1;
        } else if (object1.getTitleName().equals("#") || object2.getTitleName().equals("@")) {
            return 1;
        } else {
            return object1.getTitleName().compareTo(object2.getTitleName());
        }
    }

}
