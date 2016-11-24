package com.superexcitingboat.runningdate.bean;

import com.amap.api.location.AMapLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xushuzhan on 2016/11/16.
 * 用于记录一条轨迹，包括起点、终点、轨迹中间点、距离、耗时、平均速度、时间的been文件
 */

public class PathRecord {
        private AMapLocation mStartPoint;//起点
        private AMapLocation mEndPoint; //终点
        private List<AMapLocation> mPathLinePoints = new ArrayList<>();//路径集合
        private String mDistance;//距离
        private String mDuration;//持续时间
        private String mAverageSpeed;//平均速度
        private String mDate;//时间
        private int mId = 0;

        public PathRecord() {

        }

        public int getId() {
            return mId;
        }

        public void setId(int id) {
            this.mId = id;
        }

        public AMapLocation getStartPoint() {
            return mStartPoint;
        }

        public void setStartPoint(AMapLocation startpoint) {
            this.mStartPoint = startpoint;
        }

        public AMapLocation getEndpoint() {
            return mEndPoint;
        }

        public void setEndpoint(AMapLocation endpoint) {
            this.mEndPoint = endpoint;
        }

        public List<AMapLocation> getPathline() {
            return mPathLinePoints;
        }

        public void setPathLine(List<AMapLocation> pathline) {
            this.mPathLinePoints = pathline;
        }

        public String getDistance() {
            return mDistance;
        }

        public void setDistance(String distance) {
            this.mDistance = distance;
        }

        public String getDuration() {
            return mDuration;
        }

        public void setDuration(String duration) {
            this.mDuration = duration;
        }

        public String getAverageSpeed() {
            return mAverageSpeed;
        }

        public void setAverageSpeed(String averagespeed) {
            this.mAverageSpeed = averagespeed;
        }

        public String getDate() {
            return mDate;
        }

        public void setDate(String date) {
            this.mDate = date;
        }

        public void addpoint(AMapLocation point) {
            mPathLinePoints.add(point);
        }

        @Override
        public String toString() {
            StringBuilder record = new StringBuilder();
            record.append("recordSize:" + getPathline().size() + ", ");
            record.append("distance:" + getDistance() + "m, ");
            record.append("duration:" + getDuration() + "s");
            return record.toString();
        }
}
