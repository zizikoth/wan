package com.memo.iframe.tools.ext

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

/**
 * title:Fagmen的拓展
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 15:00
 */


/**
 * 在Activity中进行Fragment的增加 显示 隐藏 显示隐藏 替换 移除
 */
fun FragmentActivity.addFragment(layoutId: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .add(layoutId, fragment)
        .commitAllowingStateLoss()
}

fun FragmentActivity.showFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .show(fragment)
        .commitAllowingStateLoss()
}

fun FragmentActivity.hideFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .hide(fragment)
        .commitAllowingStateLoss()
}


fun FragmentActivity.showHideFragment(showFragment: Fragment, hideFragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .show(showFragment)
        .hide(hideFragment)
        .commitAllowingStateLoss()
}


fun FragmentActivity.replaceFragment(layoutId: Int, f: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(layoutId, f)
        .commitAllowingStateLoss()
}

fun FragmentActivity.removeFragment(f: Fragment) {
    supportFragmentManager.beginTransaction()
        .remove(f)
        .commitAllowingStateLoss()
}


/**
 * 在Fragment中进行Fragment的增加 显示 隐藏 显示隐藏 替换 移除
 */
fun Fragment.addFragment(layoutId: Int, fragment: Fragment) {
    childFragmentManager.beginTransaction()
        .add(layoutId, fragment)
        .commitAllowingStateLoss()
}

fun Fragment.hideFragment(fragment: Fragment) {
    childFragmentManager.beginTransaction()
        .hide(fragment)
        .commitAllowingStateLoss()
}

fun Fragment.showFragment(fragment: Fragment) {
    childFragmentManager.beginTransaction()
        .show(fragment)
        .commitAllowingStateLoss()
}

fun Fragment.showHideFragment(showFragment: Fragment, hideFragment: Fragment) {
    childFragmentManager.beginTransaction()
        .show(showFragment)
        .hide(hideFragment)
        .commitAllowingStateLoss()
}

fun Fragment.replaceFragment(layoutId: Int, f: Fragment) {
    childFragmentManager.beginTransaction()
        .replace(layoutId, f)
        .commitAllowingStateLoss()
}

fun Fragment.removeFragment(f: Fragment) {
    childFragmentManager.beginTransaction()
        .remove(f)
        .commitAllowingStateLoss()
}