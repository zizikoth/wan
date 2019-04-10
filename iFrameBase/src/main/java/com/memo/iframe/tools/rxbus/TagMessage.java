package com.memo.iframe.tools.rxbus;

/**
 * title:RxBus 基础Message
 * describe:
 *
 * @author zhou
 * @date 2019-02-22 16:38
 */
final class TagMessage {

    Object mEvent;
    private String mTag;

    TagMessage(Object event, String tag) {
        mEvent = event;
        mTag = tag;
    }

    boolean isSameType(final Class eventType, final String tag) {
        return RxBusUtils.equals(getEventType(), eventType)
                && RxBusUtils.equals(this.mTag, tag);
    }

    Class getEventType() {
        return RxBusUtils.getClassFromObject(mEvent);
    }

    @Override
    public String toString() {
        return "event: " + mEvent + ", tag: " + mTag;
    }
}
