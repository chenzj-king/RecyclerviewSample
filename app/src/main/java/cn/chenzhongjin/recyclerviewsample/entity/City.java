package cn.chenzhongjin.recyclerviewsample.entity;

/**
 * @author chenzj
 * @Title: City
 * @Description: 类的描述 -
 * @date
 * @email admin@chenzhongjin.cn
 */
public class City {

    private boolean isTitle;
    private String titleName;
    private String cityNameStr;

    public boolean isTitle() {
        return isTitle;
    }

    public void setTitle(boolean title) {
        isTitle = title;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getCityNameStr() {
        return cityNameStr;
    }

    public void setCityNameStr(String cityNameStr) {
        this.cityNameStr = cityNameStr;
    }

    @Override
    public String toString() {
        return "City{" +
                "isTitle=" + isTitle +
                ", titleName='" + titleName + '\'' +
                ", cityNameStr='" + cityNameStr + '\'' +
                '}';
    }
}

