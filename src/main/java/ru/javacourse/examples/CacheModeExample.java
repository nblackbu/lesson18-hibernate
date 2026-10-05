package ru.javacourse.examples;

import ru.javacourse.examples.cachemode.GetExample;
import ru.javacourse.examples.cachemode.IgnoreExample;
import ru.javacourse.examples.cachemode.NormalExample;
import ru.javacourse.examples.cachemode.PutExample;
import ru.javacourse.examples.cachemode.RefreshExample;

public final class CacheModeExample {

    private CacheModeExample() {
    }

    public static void run() {
        NormalExample.run();
        GetExample.run();
        PutExample.run();
        IgnoreExample.run();
        RefreshExample.run();
    }
}
