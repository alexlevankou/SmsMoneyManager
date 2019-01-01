package by.alexlevankou.smsmoneymanager.receiver;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Telephony;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsMessage;

import by.alexlevankou.smsmoneymanager.CustomApplication;
import by.alexlevankou.smsmoneymanager.model.Operation;
import by.alexlevankou.smsmoneymanager.parser.PriorSmsParser;
import by.alexlevankou.smsmoneymanager.service.SmsService;

public class SmsReceiver extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVED)) {
            if (isSmsPermissionGranted(context)) {
                String smsBody = "";
                for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                    smsBody += smsMessage.getMessageBody();
                }
                PriorSmsParser parser = new PriorSmsParser();
                if(parser.isValid(smsBody)) {

                    final Operation operation = parser.parse(smsBody);
                    final PendingResult result = goAsync();
                    Thread thread = new Thread() {
                        public void run() {
                            // Do processing
                            CustomApplication.getInstance().getRepository().addOperation(operation);
                            result.finish();
                        }
                    };
                    thread.start();
                    // нужно выполнять в другом потоке и либо передавать в сервис, либо дожидаться окончания в ресивере
                }

                //Intent serviceIntent = new Intent(context, SmsService.class);
                //context.startService(serviceIntent);
            }
        }
    }

    private boolean isSmsPermissionGranted(Context context) {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED;
    }
}