package com.comxa.mysqlandroidproject.xposed;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Tutorial implements IXposedHookLoadPackage {

    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.android.systemui"))
            return;
         XposedBridge.log("SystemUI'in icindeyik");
        // log mesajı basıyoruz..
        // mesaj xposed installerdaki log kayıtları ekranına gidiyor..

        // metodu hookluyoruz...
        findAndHookMethod("com.android.systemui.statusbar.policy.Clock", lpparam.classLoader, "updateClock", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                // this will be called before the clock was updated by the original method
            }
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                // this will be called after the clock was updated by the original method
                TextView tv = (TextView) param.thisObject;
                tv.append(" :)");
                tv.setTextColor(Color.RED);
            }
        });
    }

    /*
    @Override
    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam resparam) throws Throwable {

        resparam.res.hookLayout("com.android.systemui", "layout", "navigation_bar", new XC_LayoutInflated() {
            @Override
            public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                View navbar = (View) liparam.view.findViewById(
                        liparam.res.getIdentifier("nav_buttons", "id", "com.android.systemui"));
                // navbar.setBackgroundColor(Color.argb(50, 125, 125, 255));
                navbar.setBackgroundColor(Color.CYAN);
            }
        });

    }
    */
}
