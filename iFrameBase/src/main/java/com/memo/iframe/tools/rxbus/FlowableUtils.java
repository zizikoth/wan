package com.memo.iframe.tools.rxbus;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.operators.flowable.FlowableInternalHelper;
import org.reactivestreams.Subscription;

/**
 * @author zhou
 * @title RxBus 背压工具
 * @date 2019-01-28 10:22
 * @describe
 */
public final class FlowableUtils {

    public static <T> Disposable subscribe(Flowable<T> flowable,
                                           Consumer<? super T> onNext,
                                           Consumer<? super Throwable> onError) {
        return subscribe(flowable,
                onNext, onError,
                Functions.EMPTY_ACTION,
                FlowableInternalHelper.RequestMax.INSTANCE
        );
    }

    private static <T> Disposable subscribe(Flowable<T> flowable,
                                            Consumer<? super T> onNext,
                                            Consumer<? super Throwable> onError,
                                            Action onComplete,
                                            Consumer<? super Subscription> onSubscribe) {
        ObjectHelper.requireNonNull(flowable, "flowable is null");
        ObjectHelper.requireNonNull(onNext, "onNext is null");
        ObjectHelper.requireNonNull(onError, "onError is null");
        ObjectHelper.requireNonNull(onComplete, "onComplete is null");
        ObjectHelper.requireNonNull(onSubscribe, "onSubscribe is null");

        LambdaSubscriber<T> ls = new LambdaSubscriber<T>(onNext, onError, onComplete, onSubscribe);
        flowable.subscribe(ls);
        return ls;
    }
}
