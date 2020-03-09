# MusicAlarm

Alarm that goes off and plays music at specific user set time. This alarm can be cancelled after the alarm has gone off or
before it goes off. This alarm supports 12 and 24 hour time frame. The user can select a time 24 hours in the future.

## How it works

User clicks open time picker button and the a TimePickerFragment is created. Calendar class reads user selection for time when ok is clicked in TimePickerFragment. The TimePickerFragment uses Calendar class to get an instance of the time.  PendingIntent uses a broadcast to trigger a Broadcast Receiver which then creates a NotificationHelper. If the android api version is oreo or above then a notification channel is created. Otherwise a notification channel isn’t necessary. The notification channel is created using the Notification manager. After the user is notified with a notification the service is then started. This is given a foreground service priority as to not be killed by the system. The service then creates an intent that can access the app. This intent is wrapped in a notification. The user can cancel the alarm by selecting the alarm via the notification.  If the cancel Alarm button is selected the service will stop the notification channel and stop the media player and stop the service. The user can also press cancel which will cancel the alarm.
