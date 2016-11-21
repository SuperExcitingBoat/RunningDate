package com.superexcitingboat.runningdate.utils.Counter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import com.superexcitingboat.runningdate.BuildConfig;

import java.util.Timer;
import java.util.TimerTask;

public class StepDetector implements SensorEventListener {
    private final String TAG = "StepDetector";
    //存放三轴数据
    private final int valueNum = 5;
    //用于存放计算阈值的波峰波谷差值
    private float[] tempValue = new float[valueNum];
    private int tempCount = 0;
    //是否上升的标志位
    private boolean isDirectionUp = false;
    //持续上升次数
    private int continueUpCount = 0;
    //上一点的持续上升的次数，为了记录波峰的上升次数
    private int continueUpFormerCount = 0;
    //上一点的状态，上升还是下降
    private boolean lastDirectionUp = false;
    //波峰值
    private float peakOfWave = 0;
    //波谷值
    private float valleyOfWave = 0;
    //此次波峰的时间
    private long timeOfThisPeak = 0;
    //上次波峰的时间
    private long timeOfLastPeak = 0;
    //当前的时间
    private long timeOfNow = 0;
    //当前传感器的值
    private float gravityNew = 0;
    //上次传感器的值
    private float gravityOld = 0;
    //动态阈值需要动态的数据，这个值用于这些动态数据的阈值
    private final float initialValue = 1.7F;
    //初始阈值
    private float threadValue = 2;

    //初始范围
//    private static final float MIN_VALUE = 11f;
//    private static final float MAX_VALUE = 19.6f;
    public static float MIN_VALUE = 11f;
    public static float MAX_VALUE = 19.6f;

    /**
     * 0-准备计时   1-计时中   2-正常计步中
     */
    private int countTimeState = 0;
    private int currentStep = 0;
    private int tmpStep = 0;
    private int lastStep = -1;
    //用x、y、z轴三个维度算出的平均值
    private float average = 0;
    private Timer timer;
    // 倒计时3.5秒，3.5秒内不会显示计步，用于屏蔽细微波动
    private long duration = 3500;
    private TimeCount time;
    private OnSensorChangeListener onSensorChangeListener;

    public interface OnSensorChangeListener {
        void onChange(int step);
    }

    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    public OnSensorChangeListener getOnSensorChangeListener() {
        return onSensorChangeListener;
    }

    public void setOnSensorChangeListener(
            OnSensorChangeListener onSensorChangeListener) {
        this.onSensorChangeListener = onSensorChangeListener;
    }

    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        synchronized (this) {
            if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                calcStep(event);
            }
        }
    }

    synchronized private void calcStep(SensorEvent event) {
        average = (float) Math.sqrt(Math.pow(event.values[0], 2) + Math.pow(event.values[1], 2) + Math.pow(event.values[2], 2));
        detectorNewStep(average);
    }

    /*
     * 检测步子，并开始计步
	 * 1.传入sensor中的数据
	 * 2.如果检测到了波峰，并且符合时间差以及阈值的条件，则判定为1步
	 * 3.符合时间差条件，波峰波谷差值大于initialValue，则将该差值纳入阈值的计算中
	 * */
    public void detectorNewStep(float values) {
        if (gravityOld == 0) {
            gravityOld = values;
        } else {
            if (detectorPeak(values, gravityOld)) {
                timeOfLastPeak = timeOfThisPeak;
                timeOfNow = System.currentTimeMillis();

                if (timeOfNow - timeOfLastPeak >= 200 && (timeOfNow - timeOfLastPeak) <= 2000 && (peakOfWave - valleyOfWave >= threadValue)) {
                    timeOfThisPeak = timeOfNow;
                    preStep();//通知Listener
                }else if (timeOfNow - timeOfLastPeak >= 200 && (peakOfWave - valleyOfWave >= initialValue)) {
                    timeOfThisPeak = timeOfNow;
                    threadValue = peakValleyThread(peakOfWave - valleyOfWave);
                }
            }
            gravityOld = values;
        }
    }

    private void preStep() {
        if (countTimeState == 0) {
            // 开启计时器
            time = new TimeCount(duration, 700);
            time.start();
            countTimeState = 1;
            if (BuildConfig.DEBUG) {
                Log.v(TAG, "开启计时器");
            }
        } else if (countTimeState == 1) {
            tmpStep++;
            if (BuildConfig.DEBUG) {
                Log.v(TAG, "计步中 TEMP_STEP:" + tmpStep);
            }
        } else if (countTimeState == 2) {
            currentStep++;
            if (onSensorChangeListener != null) {
                onSensorChangeListener.onChange(currentStep);
            }
        }
    }

    /*
     * 检测波峰
     * 以下四个条件判断为波峰：
     * 1.目前点为下降的趋势：isDirectionUp为false
     * 2.之前的点为上升的趋势：lastStatus为true
     * 3.到波峰为止，持续上升大于等于2次
     * 4.波峰值大于1.2g,小于2g
     * 记录波谷值
     * 1.观察波形图，可以发现在出现步子的地方，波谷的下一个就是波峰，有比较明显的特征以及差值
     * 2.所以要记录每次的波谷值，为了和下次的波峰做对比
     * */
    public boolean detectorPeak(float newValue, float oldValue) {
        lastDirectionUp = isDirectionUp;
        if (newValue >= oldValue) {//增大
            isDirectionUp = true;
            continueUpCount++;
        } else {//减小，重置上升
            continueUpFormerCount = continueUpCount;
            continueUpCount = 0;
            isDirectionUp = false;
        }

//        if (BuildConfig.DEBUG) {
//            Log.v(TAG, "oldValue:" + oldValue);
//        }

        if (!isDirectionUp && lastDirectionUp && (continueUpFormerCount >= 2 && (oldValue >= MIN_VALUE && oldValue < MAX_VALUE))) {
            peakOfWave = oldValue;
            return true;
        } else if (!lastDirectionUp && isDirectionUp) {
            valleyOfWave = oldValue;
            return false;
        } else {
            return false;
        }
    }

    /*
     * 阈值的计算
     * 1.通过波峰波谷的差值计算阈值
     * 2.记录4个值，存入tempValue[]数组中
     * 3.在将数组传入函数averageValue中计算阈值
     * */
    public float peakValleyThread(float value) {
        float tempThread = threadValue;
        if (tempCount < valueNum) {
            tempValue[tempCount] = value;
            tempCount++;
        } else {
            tempThread = averageValue(tempValue, valueNum);
            System.arraycopy(tempValue, 1, tempValue, 0, valueNum - 1);
            tempValue[valueNum - 1] = value;
        }
        return tempThread;

    }

    /*
     * 梯度化阈值
     * 1.计算数组的均值
     * 2.通过均值将阈值梯度化在一个范围里
     * */
    public float averageValue(float value[], int n) {
        float ave = 0;
        for (int i = 0; i < n; i++) {
            ave += value[i];
        }
        ave = ave / valueNum;
        if (ave >= 8) {
            if (BuildConfig.DEBUG) {
                Log.v(TAG, "超过8");
            }
            ave = (float) 4.3;
        } else if (ave >= 7 && ave < 8) {
            if (BuildConfig.DEBUG) {
                Log.v(TAG, "7-8");
            }
            ave = (float) 3.3;
        } else if (ave >= 4 && ave < 7) {
            if (BuildConfig.DEBUG) {
                Log.v(TAG, "4-7");
            }
            ave = (float) 2.3;
        } else if (ave >= 3 && ave < 4) {
            if (BuildConfig.DEBUG) {
                Log.v(TAG, "3-4");
            }
            ave = (float) 2.0;
        } else {
            if (BuildConfig.DEBUG) {
                Log.v(TAG, "else");
            }
            ave = (float) 1.7;
        }
        return ave;
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            // 如果计时器正常结束，则开始计步
            time.cancel();
            currentStep += tmpStep;
            lastStep = -1;
//            CountTimeState = 2;
            if (BuildConfig.DEBUG) {
                Log.v(TAG, "计时正常结束");
            }

            timer = new Timer(true);
            TimerTask task = new TimerTask() {
                public void run() {
                    if (lastStep == currentStep) {
                        timer.cancel();
                        countTimeState = 0;
                        lastStep = -1;
                        tmpStep = 0;
                        if (BuildConfig.DEBUG) {
                            Log.v(TAG, "停止计步：" + currentStep);
                        }
                    } else {
                        lastStep = currentStep;
                    }
                }
            };
            timer.schedule(task, 0, 2000);
            countTimeState = 2;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (lastStep == tmpStep) {
                if (BuildConfig.DEBUG) {
                    Log.v(TAG, "onTick 计时停止");
                }
                time.cancel();
                countTimeState = 0;
                lastStep = -1;
                tmpStep = 0;
            } else {
                lastStep = tmpStep;
            }
        }

    }

    public int getCurrentStep() {
        return currentStep;
    }
}
