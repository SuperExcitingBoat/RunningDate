package com.superexcitingboat.runningdate.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.superexcitingboat.runningdate.utils.Counter.StepRecorder;

public class StepCountService extends Service implements StepRecorder.OnStepChangeListener {

    private NotificationManager notificationManager;
    private Notification notification;


    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        StepRecorder.getInstance().addOnStepChangeListener(this);
//        initNotification();
    }

/*    private void initNotification() {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
        remoteViews.setTextViewText(R.id.notification_user, CurrentUser.getCurrentUser().getName());
        if (CurrentUser.getCurrentUser().getHead() == null) {
            remoteViews.setImageViewResource(R.id.notification_head, R.drawable.default_head);
        } else {
            try {
                remoteViews.setImageViewUri(R.id.notification_head, Uri.parse(CurrentUser.getCurrentUser().getHead()));
            } catch (RuntimeException e) {
                System.out.println("总有刁民想害朕");
                Log.e("StepCountService", "initNotification: ", e);
            }
        }
        notification = new NotificationCompat.Builder(this)
                .setContent(remoteViews)
                .setSmallIcon(R.drawable.walk)
                .setTicker("开始计步")
                .setWhen(System.currentTimeMillis())
                .build();
        notification.flags = Notification.FLAG_NO_CLEAR;
        startForeground(R.string.app_name, notification);
//        notificationManager.notify(R.string.app_name, notification);
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(StepCountService.this, "走了" + StepRecorder.getInstance().getCount() + "步", Toast.LENGTH_SHORT).show();
        StepRecorder.getInstance().removeOnStepChangeListener(this);
//        notificationManager.cancel(R.string.app_name);

    }

    @Override
    public void onStepChange(int step) {
/*        if (notificationManager != null) {
            notification.contentView.setTextViewText(R.id.notification_count, String.valueOf(step));
            notificationManager.notify(R.string.app_name, notification);
        }*/
    }
}
