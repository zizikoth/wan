package com.memo.iframe.tools.rxbus;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * @author zhou
 * @title RxBus
 * @date 2019-01-28 10:20
 * @describe
 */
public final class RxBus {

    private final FlowableProcessor<Object> mBus;

    private final Consumer<Throwable> mOnError = new Consumer<Throwable>() {
        @Override
        public void accept(Throwable throwable) {
            RxBusUtils.logE(throwable.toString());
        }
    };

    private RxBus() {
        mBus = PublishProcessor.create().toSerialized();
    }

    public static RxBus get() {
        return Holder.BUS;
    }

    public void post(final Object event) {
        post(event, "", false);
    }

    public void post(final Object event, final String tag) {
        post(event, tag, false);
    }

    public void postSticky(final Object event) {
        post(event, "", true);
    }

    public void postSticky(final Object event, final String tag) {
        post(event, tag, true);
    }

    private void post(final Object event,
                      final String tag,
                      final boolean isSticky) {
        RxBusUtils.requireNonNull(event, tag);

        TagMessage msgEvent = new TagMessage(event, tag);
        if (isSticky) {
            CacheUtils.getInstance().addStickyEvent(msgEvent);
        }
        mBus.onNext(msgEvent);
    }

    public <T> void subscribe(final Object subscriber,
                              final Callback<T> callback) {
        subscribe(subscriber, "", false, null, callback);
    }

    public <T> void subscribe(final Object subscriber,
                              final String tag,
                              final Callback<T> callback) {
        subscribe(subscriber, tag, false, null, callback);
    }

    public <T> void subscribe(final Object subscriber,
                              final Scheduler scheduler,
                              final Callback<T> callback) {
        subscribe(subscriber, "", false, scheduler, callback);
    }

    public <T> void subscribe(final Object subscriber,
                              final String tag,
                              final Scheduler scheduler,
                              final Callback<T> callback) {
        subscribe(subscriber, tag, false, scheduler, callback);
    }

    public <T> void subscribeSticky(final Object subscriber,
                                    final Callback<T> callback) {
        subscribe(subscriber, "", true, null, callback);
    }

    public <T> void subscribeSticky(final Object subscriber,
                                    final String tag,
                                    final Callback<T> callback) {
        subscribe(subscriber, tag, true, null, callback);
    }

    public <T> void subscribeSticky(final Object subscriber,
                                    final Scheduler scheduler,
                                    final Callback<T> callback) {
        subscribe(subscriber, "", true, scheduler, callback);
    }

    public <T> void subscribeSticky(final Object subscriber,
                                    final String tag,
                                    final Scheduler scheduler,
                                    final Callback<T> callback) {
        subscribe(subscriber, tag, true, scheduler, callback);
    }

    private <T> void subscribe(final Object subscriber,
                               final String tag,
                               final boolean isSticky,
                               final Scheduler scheduler,
                               final Callback<T> callback) {
        RxBusUtils.requireNonNull(subscriber, tag, callback);

        final Class<T> typeClass = RxBusUtils.getTypeClassFromParadigm(callback);

        final Consumer<T> onNext = new Consumer<T>() {
            @Override
            public void accept(T t) {
                callback.onEvent(t);
            }
        };

        if (isSticky) {
            final TagMessage stickyEvent = CacheUtils.getInstance().findStickyEvent(typeClass, tag);
            if (stickyEvent != null) {
                Flowable<T> stickyFlowable = Flowable.create(new FlowableOnSubscribe<T>() {
                    @Override
                    public void subscribe(FlowableEmitter<T> emitter) {
                        emitter.onNext(typeClass.cast(stickyEvent.mEvent));
                    }
                }, BackpressureStrategy.LATEST);
                if (scheduler != null) {
                    stickyFlowable = stickyFlowable.observeOn(scheduler);
                }
                Disposable stickyDisposable = FlowableUtils.subscribe(stickyFlowable, onNext, mOnError);
                CacheUtils.getInstance().addDisposable(subscriber, stickyDisposable);
            } else {
                RxBusUtils.logW("sticky event is empty.");
            }
        }
        Disposable disposable = FlowableUtils.subscribe(
                toFlowable(typeClass, tag, scheduler), onNext, mOnError
        );
        CacheUtils.getInstance().addDisposable(subscriber, disposable);
    }

    private <T> Flowable<T> toFlowable(final Class<T> eventType,
                                       final String tag,
                                       final Scheduler scheduler) {
        Flowable<T> flowable = mBus.ofType(TagMessage.class)
                .filter(new Predicate<TagMessage>() {
                    @Override
                    public boolean test(TagMessage tagMessage) {
                        return tagMessage.isSameType(eventType, tag);
                    }
                }).map(new Function<TagMessage, T>() {
                    @Override
                    public T apply(TagMessage tagMessage) {
                        return (T) tagMessage.mEvent;
                    }
                })
                .cast(eventType);
        if (scheduler != null) {
            return flowable.observeOn(scheduler);
        }
        return flowable;
    }

    public void unregister(final Object subscriber) {
        CacheUtils.getInstance().removeDisposables(subscriber);
    }

    private static class Holder {
        private static final RxBus BUS = new RxBus();
    }

    public interface Callback<T> {
        /**
         * 事件分发
         *
         * @param t 传递实例
         */
        void onEvent(T t);
    }
}