package com.superexcitingboat.runningdate.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
import com.superexcitingboat.runningdate.utils.StaticUtils;
import com.superexcitingboat.runningdate.utils.TimeRecorder;

import java.text.DecimalFormat;
import java.util.List;

public class LocationActivity extends AppCompatActivity implements LocationSource, AMapLocationListener, TimeRecorder.OnTimePlusOneSecondListener {

    public static final String TAG = "TAGLocationActivity";
    public static final int RESULT_MAP_ACTIVITY = 1;
    private AMap mAMap;
    private MapView mMapView;
    private LocationSource.OnLocationChangedListener mListener;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    public Polyline polyline;
    private PathRecord mPathRecord;
    private TextView mRunningDuration;
    private TextView mRunningDistance;
    private Button endRunning;
    private AlertDialog exitDialog;
    public LatLng oldLatlng;
    private int mDuration;
    private float mDistance;

    public boolean isFirstRecord = true;

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

        endRunning = (Button) findViewById(R.id.bt_location_now);
        endRunning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
                Log.d(TAG, "onClick: 一共跑步的时间：" + mDuration);
                Log.d(TAG, "onClick: 跑步距离：" + mDistance);
                confirmExit();
            }
        });

    }


    private void init() {
        if (mAMap == null) {
            mAMap = mMapView.getMap();
            setUpMap();
        }
        mDuration = 0;
        TimeRecorder.getInstance().addOnTimePlusOneSecondListener(this);//添加时间通知回调
        TimeRecorder.getInstance().switchStatus();
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
            LatLng newLatlng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
            mAMap.moveCamera(CameraUpdateFactory.changeLatLng(newLatlng));

            //判断是否为第一次定位经纬度
            if (isFirstRecord) {
                oldLatlng = newLatlng;
                isFirstRecord = false;

                //开始定位的时间，之后会放在开始跑步的方法里
                mPathRecord = new PathRecord();
            }
            if (oldLatlng != newLatlng && aMapLocation.getAccuracy() <= 5) {
                PolylineOptions polylineOptions = new PolylineOptions().add(oldLatlng, newLatlng).width(10).color(Color.argb(255, 1, 1, 1));
                oldLatlng = newLatlng;
                mAMap.addPolyline(polylineOptions);

            }
            Log.d(TAG, "onLocationChanged: 精度" + aMapLocation.getAccuracy());
            Log.d(TAG, "onLocationChanged: " + aMapLocation.getAddress());
            mPathRecord.addpoint(aMapLocation);
            mRunningDuration.setText(StaticUtils.secondToTime(mDuration));
            DecimalFormat df = new DecimalFormat("0.0");
            mDistance = getDistance(mPathRecord.getPathline());
            mRunningDistance.setText(df.format(mDistance) + " km");
            SharedPreferenceUtils.putString(getApplicationContext(), "duration", StaticUtils.secondToTime(mDuration));
        }

    }


    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        // Toast.makeText(this, "给listener赋值", Toast.LENGTH_SHORT).show();
        mListener = onLocationChangedListener;
        startLocation();


    }

    @Override
    public void deactivate() {

    }


    private void startLocation() {
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


    private float getDistance(List<AMapLocation> list) {
        float distance = 0;
        if (list == null || list.size() == 0) {
            return distance;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            AMapLocation firstPoint = list.get(i);
            AMapLocation secondPoint = list.get(i + 1);
            LatLng firstLatLng = new LatLng(firstPoint.getLatitude(),
                    firstPoint.getLongitude());
            LatLng secondLatLng = new LatLng(secondPoint.getLatitude(),
                    secondPoint.getLongitude());
            double betweenDis = AMapUtils.calculateLineDistance(firstLatLng,
                    secondLatLng);
            distance = (float) (distance + betweenDis);
        }
        return distance;
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
        confirmExit();
    }

    private void confirmExit() {
        if (exitDialog == null) {
            exitDialog = new AlertDialog.Builder(this)
                    .setTitle(R.string.sure_to_exit)
                    .setIcon(R.drawable.personal_icon)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setResult(RESULT_MAP_ACTIVITY, new Intent().putExtra("distance", mDistance)); //时间记录器自带时间统计，不需要传回。
                            TimeRecorder.getInstance().addOnTimePlusOneSecondListener(LocationActivity.this);//注销时间通知回调
                            TimeRecorder.getInstance().switchStatus();
                            finish();
                        }
                    }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).create();
        }
        exitDialog.show();
    }


    //时间通知回调
    @Override
    public void onTimePlusOneSecond(int count) {
        if (mRunningDuration != null) {
            mRunningDuration.post(new Runnable() {
                @Override
                public void run() {
                    mRunningDuration.setText(StaticUtils.secondToTime(++mDuration));
                }
            });
        }
    }
}

