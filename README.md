# Threads
App Threads in Android Studio 3.6.3

Note: It is not recommended to run a Thread in the Main UI

Some strategies:

1-Alternative create auxiliary class that extends from thread
2-class MyRunnable implements Runnable
3-Using a handler it is possible to send messages to a thread, from there you can execute snippets of executable codes.
 private Handler handler = new Handler(); 
