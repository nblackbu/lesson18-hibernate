package ru.javacourse;

import ru.javacourse.examples.CacheEvictExample;
import ru.javacourse.examples.CacheModeExample;
import ru.javacourse.examples.JoinFetchExample;
import ru.javacourse.examples.JoinFetchPaginationExample;
import ru.javacourse.examples.L1CacheExample;
import ru.javacourse.examples.L2CacheExample;
import ru.javacourse.examples.LazyCollectionExtraExample;
import ru.javacourse.examples.LazyInitializationExceptionExample;
import ru.javacourse.examples.LazyLoadingExample;
import ru.javacourse.examples.NPlusOneExample;
import ru.javacourse.examples.QueryCacheExample;
import ru.javacourse.examples.StatisticsExample;
import ru.javacourse.examples.cachemode.GetExample;
import ru.javacourse.examples.cachemode.IgnoreExample;
import ru.javacourse.examples.cachemode.NormalExample;
import ru.javacourse.examples.cachemode.PutExample;
import ru.javacourse.examples.cachemode.RefreshExample;
import ru.javacourse.examples.strategies.NonstrictReadWriteStrategyExample;
import ru.javacourse.examples.strategies.ReadOnlyStrategyExample;
import ru.javacourse.examples.strategies.ReadWriteStrategyExample;

public class Main {

    public static void main(String[] args) {
        try {
//            LazyLoadingExample.run();
//            LazyInitializationExceptionExample.run();
//            NPlusOneExample.run();
//            JoinFetchExample.run();
//            JoinFetchPaginationExample.run();
//            LazyCollectionExtraExample.run();
//            L1CacheExample.run();
//            L2CacheExample.run();
//            QueryCacheExample.run();
//            CacheEvictExample.run();
//            StatisticsExample.run();
//            ReadOnlyStrategyExample.run();
//            ReadWriteStrategyExample.run();
//            NonstrictReadWriteStrategyExample.run();
//            NormalExample.run();
//            GetExample.run();
            PutExample.run();
//            IgnoreExample.run();
//            RefreshExample.run();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
