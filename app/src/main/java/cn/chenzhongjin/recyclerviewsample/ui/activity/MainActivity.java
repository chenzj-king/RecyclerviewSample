package cn.chenzhongjin.recyclerviewsample.ui.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.chenzhongjin.recyclerviewsample.R;
import cn.chenzhongjin.recyclerviewsample.entity.City;
import cn.chenzhongjin.recyclerviewsample.ui.activity.adapter.MainAdapter;
import cn.chenzhongjin.recyclerviewsample.utils.CharacterParser;
import cn.chenzhongjin.recyclerviewsample.utils.CityComparator;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    private List<City> mCityList;
    private MainAdapter mAdapter;
    private CityComparator mCityComparator;

    private MyHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.init("recyclerviewSample").methodCount(1).methodOffset(0).logLevel(LogLevel.FULL).hideThreadInfo();
        ButterKnife.bind(this);

        mCityComparator = new CityComparator();

        mHandler = new MyHandler(this);

        mCityList = new ArrayList<>();
        mAdapter = new MainAdapter(mCityList);
        mRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));
        mRecyclerview.setAdapter(mAdapter);

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        });
    }

    private void initData() {
        Resources res = getResources();
        String[] citys = res.getStringArray(R.array.citys);

        mCityList.clear();
        for (String cityStr : citys) {
            City city = new City();
            city.setTitleName(CharacterParser.getInstance().getSelling(cityStr));
            city.setCityNameStr(cityStr);
            city.setTitle(false);

            mCityList.add(city);
        }
        Collections.sort(mCityList, mCityComparator);

        char firstStr = 0;
        List<City> insertCityList = new ArrayList<>();

        for (City city : mCityList) {
            if (city.getTitleName().charAt(0) != firstStr) {

                firstStr = city.getTitleName().charAt(0);

                City titleCity = new City();
                titleCity.setTitle(true);
                titleCity.setTitleName(city.getTitleName());
                titleCity.setCityNameStr("");
                insertCityList.add(titleCity);
            }
            insertCityList.add(city);
        }
        mAdapter.update(insertCityList);
    }


    protected static class MyHandler extends Handler {

        private final WeakReference<MainActivity> mActivity;

        public MyHandler(MainActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = mActivity.get();
            if (activity != null) {
                Logger.t(TAG).i("handleMessage");
            }
        }
    }
}
