package by.alexlevankou.smsmoneymanager.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SmsService extends Service {

    public SmsService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO: parse sms
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
