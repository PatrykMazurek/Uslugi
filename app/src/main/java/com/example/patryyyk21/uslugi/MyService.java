package com.example.patryyyk21.uslugi;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import java.util.logging.Handler;

public class MyService extends IntentService {

    public MyService()
    {
        super("Test Service");
    }

    @Override
    public int onStartCommand(Intent intent, int flag, int startId){
        return super.onStartCommand(intent, flag, startId);
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Toast.makeText(getApplicationContext(), "Tworzenie usługi", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Zakończenie usługi", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this){
            try{
                wait(3000);
            }catch (Exception ex){
                ex.printStackTrace();
            }
            String text = intent.getStringExtra("Message");
            ShowMessage(text);
        }
    }

    private void ShowMessage(String text){
        Intent intent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pending = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Usługa")
                .setAutoCancel(true)
                .setContentIntent(pending)
                .setContentText(text)
                .build();
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(12, notification);
    }
}
