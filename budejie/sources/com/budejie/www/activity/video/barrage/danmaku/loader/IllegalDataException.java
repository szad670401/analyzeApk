package com.budejie.www.activity.video.barrage.danmaku.loader;

public class IllegalDataException extends Exception {
    private static final long serialVersionUID = 10441759254L;

    public IllegalDataException(String str, Throwable th) {
        super(str, th);
    }

    public IllegalDataException(String str) {
        super(str);
    }

    public IllegalDataException(Throwable th) {
        super(th);
    }
}
