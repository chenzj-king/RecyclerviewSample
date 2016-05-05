package cn.chenzhongjin.recyclerviewsample.ui.activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.List;

import cn.chenzhongjin.recyclerviewsample.R;
import cn.chenzhongjin.recyclerviewsample.entity.City;
import cn.chenzhongjin.recyclerviewsample.utils.CollectionUtils;

/**
 * @author chenzj
 * @Title: MainAdapter
 * @Description: 类的描述 -
 * @date
 * @email admin@chenzhongjin.cn
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    public static final int TITLE = 0x01;
    public static final int CITY = 0x02;

    private List<City> mData;

    public MainAdapter(List<City> data) {
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == TITLE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_title, parent, false);
        } else if (viewType == CITY) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        }
        return new ViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        City itemData = getItemData(position);

        final ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        switch (getItemViewType(position)) {

            case TITLE:
                //如果到了一个段就添加一个头部
                if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                    StaggeredGridLayoutManager.LayoutParams sglp = (StaggeredGridLayoutManager.LayoutParams) lp;
                    sglp.setFullSpan(true);
                    holder.itemView.setLayoutParams(sglp);
                }
                holder.mCityTitleTv.setText(("" + itemData.getTitleName().charAt(0)).toUpperCase());
                break;
            case CITY:
                holder.mCityNameTv.setText(String.format("%s", itemData.getCityNameStr()));
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {

        City city = mData.get(position);

        if (city.isTitle()) {
            return TITLE;
        } else {
            return CITY;
        }
    }

    @Override
    public int getItemCount() {
        return null != mData ? mData.size() : 0;
    }

    public City getItemData(int position) {
        try {
            return mData.get(position);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<City> getData() {
        return mData;
    }

    public void update(List<City> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mCityNameTv;
        TextView mCityTitleTv;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);

            if (viewType == TITLE) {
                mCityTitleTv = (TextView) itemView.findViewById(R.id.city_title_tv);
            } else {
                mCityNameTv = (TextView) itemView.findViewById(R.id.city_name);
            }
        }
    }

    public void addAll(List<City> timeslotBeanList) {
        if (CollectionUtils.isNotNull(timeslotBeanList)) {
            int startIndex = mData.size();
            mData.addAll(startIndex, timeslotBeanList);
            notifyItemRangeInserted(startIndex, timeslotBeanList.size());
        } else {
            Logger.i("timeslotBeanList addall is null or size = 0");
        }
    }
}  

