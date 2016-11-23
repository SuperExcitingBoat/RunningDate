package com.superexcitingboat.runningdate.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.model.Polyline;
import com.amap.api.maps2d.model.PolylineOptions;
import com.superexcitingboat.runningdate.R;
import com.superexcitingboat.runningdate.bean.PathRecord;
import com.superexcitingboat.runningdate.utils.SharedPreferenceUtils;
import com.superexcitingboat.runningdate.utils.TimeUtil;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LocationActivity extends AppCompatActivity implements LocationSource, AMapLocationListener {
    private AMap mAMap;
    private MapView mMapView;
    private LocationSource.OnLocationChangedListener mListener;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    public Polyline polyline;
    private PathRecord mPathRecord;
    private long mStartTime;
    private long mEndTime;
    private TextView mRunningDuration;
    private TextView mRunningDistance;

    public static final String TAG = "TAGLocationActivity";
    Button endRunning;

    public boolean isFirstRecord = true;
    public LatLng oldLatlng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mMapView = (MapView) findViewById(R.id.map_location);
        mMapView.onCreate(savedInstanceState);
        init();//初始化地图对象
        initView();

    }

    private void initView() {
        mRunningDistance = (TextView) findViewById(R.id.tv_location_distance);
        mRunningDuration = (TextView) findViewById(R.id.tv_location_duration);

        endRunning= (Button) findViewById(R.id.bt_location_now);
        endRunning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
                mEndTime = System.currentTimeMillis();
                Log.d(TAG, "onClick: 一共跑步的时间：" + getDuration());
                Log.d(TAG, "onClick: 跑步距离：" + getDistance(mPathRecord.getPathline()));

                finish();
            }
        });

    }

    private void init() {
        if (mAMap == null) {
            mAMap = mMapView.getMap();
            setUpMap();
        }

    }

    /**
     * 设置一些map的属性
     */
    private void setUpMap() {
        //自定义定位的小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker));//设置图标
        myLocationStyle.strokeColor(Color.BLACK);//圆形边框颜色
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));//圆形的填充颜色
        myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细

        mAMap.setMyLocationStyle(myLocationStyle);

        mAMap.setLocationSource(this);
        mAMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
        mAMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        mAMap.moveCamera(CameraUpdateFactory.zoomTo(19));  //设置缩放级别
        mAMap.getUiSettings().setZoomControlsEnabled(false);
    }

    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {

            mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点

            //当前的经纬度
            LatLng newLatlng = new LatLng(aMapLocation.getLatitude(),
                    aMapLocation.getLongitude());
            mAMap.moveCamera(CameraUpdateFactory.changeLatLng(newLatlng));

            //判断是否为第一次定位经纬度
            if (isFirstRecord) {
                oldLatlng = newLatlng;
                isFirstRecord = false;

                //开始定位的时间，之后会放在开始跑步的方法里
                mStartTime = System.currentTimeMillis();
                mPathRecord = new PathRecord();
                mPathRecord.setDate(getcueDate(mStartTime));
                Log.d(TAG, "onLocationChanged: " + mStartTime + ">>>>>" + getcueDate(mStartTime));
            }
            if (oldLatlng != newLatlng && aMapLocation.getAccuracy() <= 5) {
                PolylineOptions polylineOptions = new PolylineOptions().add(oldLatlng, newLatlng).width(10).color(Color.argb(255, 1, 1, 1));
                oldLatlng = newLatlng;
                mAMap.addPolyline(polylineOptions);

            }
            Log.d(TAG, "onLocationChanged: 精度" + aMapLocation.getAccuracy());
            Log.d(TAG, "onLocationChanged: " + aMapLocation.getAddress());
            mPathRecord.addpoint(aMapLocation);
            mEndTime = System.currentTimeMillis();
            mRunningDuration.setText(TimeUtil.secToTime((int)getDuration()));
            DecimalFormat df = new DecimalFormat("0.0");
            mRunningDistance.setText(df.format( getDistance(mPathRecord.getPathline())/1000f )+" km");
            SharedPreferenceUtils.putString(getApplicationContext(),"duration",TimeUtil.secToTime((int)getDuration()));
        }

    }


    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
       // Toast.makeText(this, "给listener赋值", Toast.LENGTH_SHORT).show();
        mListener = onLocationChangedListener;
        startlocation();


    }

    @Override
    public void deactivate() {

    }


    private void startlocation() {
        if (mLocationClient == null) {
            mLocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            // 设置定位监听
            mLocationClient.setLocationListener(this);
            // 设置为高精度定位模式(高精度模式)
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

            mLocationOption.setInterval(2000);
            // 设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，

            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mLocationClient.startLocation();

        }
    }


    private float getDuration() {
        return (mEndTime - mStartTime) / 1000f;
    }

    private String getAverage(float distance) {
        return String.valueOf(distance / (float) (mEndTime - mStartTime));
    }

    private float getDistance(List<AMapLocation> list) {
        float distance = 0;
        if (list == null || list.size() == 0) {
            return distance;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            AMapLocation firstpoint = list.get(i);
            AMapLocation secondpoint = list.get(i + 1);
            LatLng firstLatLng = new LatLng(firstpoint.getLatitude(),
                    firstpoint.getLongitude());
            LatLng secondLatLng = new LatLng(secondpoint.getLatitude(),
                    secondpoint.getLongitude());
            double betweenDis = AMapUtils.calculateLineDistance(firstLatLng,
                    secondLatLng);
            distance = (float) (distance + betweenDis);
        }
        return distance;
    }

    //讲时间戳转化为时间
    private String getcueDate(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss ");
        Date curDate = new Date(time);
        return formatter.format(curDate);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁

        deactivate();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

