package com.raqust.bluko.common.wrapper1;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public final class WhiteIntentWrapper {

    private Intent intent;
    private int type;

    private WhiteIntentWrapper(Intent intent, int type) {
        this.intent = intent;
        this.type = type;
    }

    /**
     * 判断本机上是否有能处理当前Intent的Activity
     */
    private boolean doesActivityExists(Context context) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list != null && list.size() > 0;
    }

    /**
     * 安全地启动一个Activity
     */
    private void startActivitySafely(Activity activityContext) {
        try {
            activityContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Android 7.0+ Doze 模式
    private static final int SYSTEM = 0x00;
    private static final int DOZE = 0x01;

    //华为 自启管理
    private static final int HUAWEI = 0x10;
    //华为 锁屏清理
    private static final int HUAWEI_GOD = 0x11;

    //小米 自启动管理
    private static final int XIAOMI = 0x20;
    //小米 神隐模式
    private static final int XIAOMI_GOD = 0x21;

    //三星
    private static final int SAMSUNG = 0x30;

    //魅族 自启动管理
    private static final int MEIZU = 0x40;
    //魅族 待机耗电管理
    private static final int MEIZU_GOD = 0x41;

    //Oppo 自启动管理
    private static final int OPPO_PM = 0x50;//权限管理
    private static final int OPPO_SM = 0x51;//自启动管理
    private static final int OPPO_GOD = 0x52;

    //Vivo 后台高耗电
    private static final int VIVO = 0x60;
    private static final int VIVO_GOD = 0x61;

    //酷派 自启动管理
    private static final int COOLPAD = 0x70;

    //金立 应用自启
    private static final int GIONEE = 0x80;

    //乐视 自启动管理
    private static final int LETV = 0x90;
    //乐视 应用保护
    private static final int LETV_GOD = 0x91;

    //联想 后台管理
    private static final int LENOVO = 0x100;
    //联想 后台耗电优化
    private static final int LENOVO_GOD = 0x101;

    //中兴 自启管理
    private static final int ZTE = 0x110;
    //中兴 锁屏加速受保护应用
    private static final int ZTE_GOD = 0x111;

    //索尼 自启管理
    private static final int SONY = 0x120;

    //LG 自启管理
    private static final int LG = 0x130;

    //HTC 自启管理
    private static final int HTC = 0x140;

    //Other 自启管理
    private static final int OTHER = 0x150;

    private static List<WhiteIntentWrapper> sIntentWrapperList;

    private static boolean doesActivityExists(Context context, Intent intent) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list != null && list.size() > 0;
    }

    private static List<WhiteIntentWrapper> getIntentWrapperList(Context context) {
        if (sIntentWrapperList == null) {
            sIntentWrapperList = new ArrayList<>();

//            if (System.currentTimeMillis() >= 1523008495000L) {
//                return sIntentWrapperList;
//            }

            Log.d("WhiteIntent", "Build.VERSION.SDK_INT == " + Build.VERSION.SDK_INT);
            Log.d("WhiteIntent", "Build.MANUFACTURER == " + Build.MANUFACTURER);
            Log.d("WhiteIntent", "Build.BRAND == " + Build.BRAND);

            //Android 7.0+ Doze 模式
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
                boolean ignoringBatteryOptimizations = pm.isIgnoringBatteryOptimizations(context.getPackageName());
                if (!ignoringBatteryOptimizations) {
                    Log.d("WhiteIntent", "在电池优化白名单中");
                    Intent dozeIntent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                    dozeIntent.setData(Uri.parse("package:" + context.getPackageName()));
                    if (doesActivityExists(context, dozeIntent)) {
                        Log.d("WhiteIntent", "可以跳转到电池优化白名单设置页面");
                        sIntentWrapperList.add(new WhiteIntentWrapper(dozeIntent, DOZE));
                    } else {
                        Log.e("WhiteIntent", "不可跳转到电池优化白名单设置页面");
                    }
                } else {
                    Log.d("WhiteIntent", "不在电池优化白名单中");
                }
            }

            if (RomUtils.INSTANCE.isEmui()) {
                //华为 自启管理
                Log.d("WhiteIntent", "华为手机");
                Intent huaweiIntent = new Intent();
                huaweiIntent.setAction("huawei.intent.action.HSM_BOOTAPP_MANAGER");
                huaweiIntent.putExtra("packageName", context.getPackageName());
                huaweiIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("WhiteIntent", "尝试通过Action=huawei.intent.action.HSM_BOOTAPP_MANAGER跳转自启动设置");
                if (doesActivityExists(context, huaweiIntent)) {
                    Log.d("WhiteIntent", "可通过Action=huawei.intent.action.HSM_BOOTAPP_MANAGER跳转自启动设置");
                    sIntentWrapperList.add(new WhiteIntentWrapper(huaweiIntent, HUAWEI));
                } else {
                    Log.e("WhiteIntent", "不可通过Action==huawei.intent.action.HSM_BOOTAPP_MANAGER跳转自启动设置");
                    huaweiIntent = new Intent();
                    huaweiIntent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity"));
                    huaweiIntent.putExtra("packageName", context.getPackageName());
                    huaweiIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Log.d("WhiteIntent", "尝试通过com.huawei.systemmanager.optimize.process.ProtectActivity跳转自启动设置");
                    if (doesActivityExists(context, huaweiIntent)) {
                        Log.d("WhiteIntent", "可通过com.huawei.systemmanager.optimize.process.ProtectActivity跳转自启动设置");
                        sIntentWrapperList.add(new WhiteIntentWrapper(huaweiIntent, HUAWEI));
                    } else {
                        Log.e("WhiteIntent", "不可通过com.huawei.systemmanager.optimize.process.ProtectActivity跳转自启动设置");
                        huaweiIntent = new Intent();
                        huaweiIntent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity"));
                        huaweiIntent.putExtra("packageName", context.getPackageName());
                        huaweiIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Log.d("WhiteIntent", "尝试通过com.huawei.permissionmanager.ui.MainActivity跳转自启动设置");
                        if (doesActivityExists(context, huaweiIntent)) {
                            Log.d("WhiteIntent", "可通过com.huawei.permissionmanager.ui.MainActivity跳转自启动设置");
                            sIntentWrapperList.add(new WhiteIntentWrapper(huaweiIntent, HUAWEI));
                        } else {
                            Log.e("WhiteIntent", "不可通过com.huawei.permissionmanager.ui.MainActivity跳转自启动设置");
                        }
                    }
                }
                //华为 锁屏清理
                Intent huaweiGodIntent = new Intent();
                huaweiGodIntent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity"));
                huaweiIntent.putExtra("packageName", context.getPackageName());
                huaweiIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("WhiteIntent", "尝试通过com.huawei.systemmanager.optimize.process.ProtectActivity跳转锁屏清理白名单设置页");
                if (doesActivityExists(context, huaweiGodIntent)) {
                    Log.d("WhiteIntent", "可通过com.huawei.systemmanager.optimize.process.ProtectActivity跳转锁屏清理白名单设置页");
                    sIntentWrapperList.add(new WhiteIntentWrapper(huaweiGodIntent, HUAWEI_GOD));
                } else {
                    Log.e("WhiteIntent", "不可通过com.huawei.systemmanager.optimize.process.ProtectActivity跳转锁屏清理白名单设置页");
                }
            } else if (RomUtils.INSTANCE.isMiui()) {
                //小米 自启动管理
                Log.d("WhiteIntent", "小米手机" + getMiuiVersion());
                Intent xiaomiIntent = new Intent();
                xiaomiIntent.setAction("miui.intent.action.OP_AUTO_START");
                xiaomiIntent.addCategory(Intent.CATEGORY_DEFAULT);
                xiaomiIntent.putExtra("packageName", context.getPackageName());
                xiaomiIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("WhiteIntent", "尝试通过Action=miui.intent.action.OP_AUTO_START跳转自启动设置页");
                if (doesActivityExists(context, xiaomiIntent)) {
                    Log.d("WhiteIntent", "可通过Action=miui.intent.action.OP_AUTO_START跳转自启动设置页");
                    sIntentWrapperList.add(new WhiteIntentWrapper(xiaomiIntent, XIAOMI));
                } else {
                    Log.e("WhiteIntent", "不可通过Action=miui.intent.action.OP_AUTO_START跳转自启动设置页");
                    xiaomiIntent = new Intent();
                    xiaomiIntent.setComponent(ComponentName.unflattenFromString("com.miui.securitycenter/com.miui.permcenter.autostart.AutoStartManagementActivity"));
                    xiaomiIntent.putExtra("packageName", context.getPackageName());
                    xiaomiIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Log.d("WhiteIntent", "尝试通过com.miui.permcenter.autostart.AutoStartManagementActivity跳转自启动设置页");
                    if (doesActivityExists(context, xiaomiIntent)) {
                        Log.d("WhiteIntent", "可通过com.miui.permcenter.autostart.AutoStartManagementActivity跳转自启动设置页");
                        sIntentWrapperList.add(new WhiteIntentWrapper(xiaomiIntent, XIAOMI));
                    } else {
                        Log.e("WhiteIntent", "不可通过com.miui.permcenter.autostart.AutoStartManagementActivity跳转自启动设置页");
                        xiaomiIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                        xiaomiIntent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity"));
                        xiaomiIntent.putExtra("extra_pkgname", context.getPackageName());
                        xiaomiIntent.putExtra("packageName", context.getPackageName());
                        xiaomiIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Log.d("WhiteIntent", "尝试通过com.miui.permcenter.permissions.AppPermissionsEditorActivity跳转自启动设置页");
                        if (doesActivityExists(context, xiaomiIntent)) {
                            Log.d("WhiteIntent", "可通过com.miui.permcenter.permissions.AppPermissionsEditorActivity跳转自启动设置页");
                            sIntentWrapperList.add(new WhiteIntentWrapper(xiaomiIntent, XIAOMI));
                        } else {
                            Log.e("WhiteIntent", "不可通过com.miui.permcenter.permissions.AppPermissionsEditorActivity跳转自启动设置页");
                            xiaomiIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                            xiaomiIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
                            xiaomiIntent.putExtra("extra_pkgname", context.getPackageName());
                            xiaomiIntent.putExtra("packageName", context.getPackageName());
                            xiaomiIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            Log.d("WhiteIntent", "尝试通过com.miui.permcenter.permissions.PermissionsEditorActivity跳转自启动设置页");
                            if (doesActivityExists(context, xiaomiIntent)) {
                                Log.d("WhiteIntent", "可通过com.miui.permcenter.permissions.PermissionsEditorActivity跳转自启动设置页");
                                sIntentWrapperList.add(new WhiteIntentWrapper(xiaomiIntent, XIAOMI));
                            } else {
                                Log.e("WhiteIntent", "不可通过com.miui.permcenter.permissions.PermissionsEditorActivity跳转自启动设置页");
                            }
                        }
                    }
                }
                //小米 神隐模式
                Intent xiaomiGodIntent = new Intent();
                xiaomiGodIntent.setComponent(new ComponentName("com.miui.powerkeeper", "com.miui.powerkeeper.ui.HiddenAppsConfigActivity"));
                xiaomiGodIntent.putExtra("package_name", context.getPackageName());
                xiaomiGodIntent.putExtra("package_label", getApplicationName(context));
                xiaomiGodIntent.putExtra("packageName", context.getPackageName());
                xiaomiGodIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("WhiteIntent", "尝试通过com.miui.powerkeeper.ui.HiddenAppsConfigActivity跳转神隐模式设置页");
                if (doesActivityExists(context, xiaomiGodIntent)) {
                    Log.d("WhiteIntent", "可通过com.miui.powerkeeper.ui.HiddenAppsConfigActivity跳转神隐模式设置页");
                    sIntentWrapperList.add(new WhiteIntentWrapper(xiaomiGodIntent, XIAOMI_GOD));
                } else {
                    Log.e("WhiteIntent", "不可通过com.miui.powerkeeper.ui.HiddenAppsConfigActivity跳转神隐模式设置页");
                }
            } else if (RomUtils.INSTANCE.isOppo()) {
                //Oppo 自启动管理
                Log.d("WhiteIntent", "Oppo手机");
                Intent oppoIntent = new Intent();
                oppoIntent.setComponent(ComponentName.unflattenFromString("com.coloros.safecenter/com.coloros.privacypermissionsentry.PermissionTopActivity"));
                oppoIntent.putExtra("packageName", context.getPackageName());
                oppoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("WhiteIntent", "尝试通过com.coloros.privacypermissionsentry.PermissionTopActivity跳转权限隐私设置");
                if (doesActivityExists(context, oppoIntent)) {
                    Log.d("WhiteIntent", "可通过com.coloros.privacypermissionsentry.PermissionTopActivity跳转权限隐私设置");
                    sIntentWrapperList.add(new WhiteIntentWrapper(oppoIntent, OPPO_PM));
                } else {
                    Log.e("WhiteIntent", "不可通过com.coloros.privacypermissionsentry.PermissionTopActivity跳转权限隐私设置");
                    oppoIntent = new Intent();
                    oppoIntent.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity"));
                    oppoIntent.putExtra("packageName", context.getPackageName());
                    oppoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Log.d("WhiteIntent", "尝试通过com.coloros.safecenter.permission.startup.StartupAppListActivity跳转自启动设置");
                    if (doesActivityExists(context, oppoIntent)) {
                        Log.d("WhiteIntent", "可通过com.coloros.safecenter.permission.startup.StartupAppListActivity跳转自启动设置");
                        sIntentWrapperList.add(new WhiteIntentWrapper(oppoIntent, OPPO_SM));
                    } else {
                        Log.e("WhiteIntent", "不可通过com.coloros.safecenter.permission.startup.StartupAppListActivity跳转自启动设置");
                        oppoIntent = new Intent();
                        oppoIntent.setComponent(new ComponentName("com.color.safecenter", "com.color.safecenter.permission.startup.StartupAppListActivity"));
                        oppoIntent.putExtra("packageName", context.getPackageName());
                        oppoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Log.d("WhiteIntent", "尝试通过com.color.safecenter.permission.startup.StartupAppListActivity跳转自启动设置");
                        if (doesActivityExists(context, oppoIntent)) {
                            Log.d("WhiteIntent", "可通过com.color.safecenter.permission.startup.StartupAppListActivity跳转自启动设置");
                            sIntentWrapperList.add(new WhiteIntentWrapper(oppoIntent, OPPO_SM));
                        } else {
                            Log.e("WhiteIntent", "不可通过com.color.safecenter.permission.startup.StartupAppListActivity跳转自启动设置");
                            oppoIntent = new Intent();
                            oppoIntent.setComponent(new ComponentName("com.color.safecenter", "com.color.safecenter.permission.PermissionManagerActivity"));
                            oppoIntent.putExtra("packageName", context.getPackageName());
                            oppoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            Log.d("WhiteIntent", "尝试通过com.color.safecenter.permission.PermissionManagerActivity跳转自启动设置");
                            if (doesActivityExists(context, oppoIntent)) {
                                Log.d("WhiteIntent", "可通过com.color.safecenter.permission.PermissionManagerActivity跳转自启动设置");
                                sIntentWrapperList.add(new WhiteIntentWrapper(oppoIntent, OPPO_PM));
                            } else {
                                Log.e("WhiteIntent", "不可通过com.color.safecenter.permission.PermissionManagerActivity跳转自启动设置");
                                oppoIntent = new Intent();
                                oppoIntent.setComponent(ComponentName.unflattenFromString("com.oppo.safe/.permission.startup.StartupAppListActivity"));
                                oppoIntent.putExtra("packageName", context.getPackageName());
                                oppoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                Log.d("WhiteIntent", "尝试通过com.oppo.safe.permission.startup.StartupAppListActivity跳转自启动设置");
                                if (doesActivityExists(context, oppoIntent)) {
                                    Log.d("WhiteIntent", "可通过com.oppo.safe.permission.startup.StartupAppListActivity跳转自启动设置");
                                    sIntentWrapperList.add(new WhiteIntentWrapper(oppoIntent, OPPO_SM));
                                } else {
                                    Log.e("WhiteIntent", "不可通过com.oppo.safe.permission.startup.StartupAppListActivity跳转自启动设置");
                                }
                            }
                        }
                    }
                }
                //OPPO 纯净后台
                Intent oppoGodIntent = new Intent();
                oppoGodIntent.setComponent(ComponentName.unflattenFromString("com.oppo.purebackground/.PurebackgroundTopActivity"));
                oppoGodIntent.putExtra("packageName", context.getPackageName());
                oppoGodIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("WhiteIntent", "尝试通过com.oppo.purebackground.PurebackgroundTopActivity跳转纯净后台设置页");
                if (doesActivityExists(context, oppoGodIntent)) {
                    Log.d("WhiteIntent", "可通过com.oppo.purebackground.PurebackgroundTopActivity跳转纯净后台设置页");
                    sIntentWrapperList.add(new WhiteIntentWrapper(oppoGodIntent, OPPO_GOD));
                } else {
                    Log.e("WhiteIntent", "不可通过com.oppo.purebackground.PurebackgroundTopActivity跳转纯净后台设置页");
                    oppoGodIntent = new Intent();
                    oppoGodIntent.setComponent(ComponentName.unflattenFromString("com.color.purebackground.PurebackgroundTopActivity"));
                    oppoGodIntent.putExtra("packageName", context.getPackageName());
                    oppoGodIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Log.d("WhiteIntent", "尝试通过com.color.purebackground.PurebackgroundTopActivity跳转纯净后台设置页");
                    if (doesActivityExists(context, oppoGodIntent)) {
                        Log.d("WhiteIntent", "可通过com.color.purebackground.PurebackgroundTopActivity跳转纯净后台设置页");
                        sIntentWrapperList.add(new WhiteIntentWrapper(oppoGodIntent, OPPO_GOD));
                    } else {
                        Log.e("WhiteIntent", "不可通过com.coloros.purebackground.PurebackgroundTopActivity跳转纯净后台设置页");
                        oppoGodIntent = new Intent();
                        oppoGodIntent.setComponent(ComponentName.unflattenFromString("com.coloros.purebackground.PurebackgroundTopActivity"));
                        oppoGodIntent.putExtra("packageName", context.getPackageName());
                        oppoGodIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Log.d("WhiteIntent", "尝试通过com.coloros.purebackground.PurebackgroundTopActivity跳转纯净后台设置页");
                        if (doesActivityExists(context, oppoGodIntent)) {
                            Log.d("WhiteIntent", "可通过com.coloros.purebackground.PurebackgroundTopActivity跳转纯净后台设置页");
                            sIntentWrapperList.add(new WhiteIntentWrapper(oppoGodIntent, OPPO_GOD));
                        } else {
                            Log.e("WhiteIntent", "不可通过com.coloros.purebackground.PurebackgroundTopActivity跳转纯净后台设置页");
                        }
                    }
                }
            } else if (RomUtils.INSTANCE.isLeTv()) {
                //乐视 自启动管理
                Log.d("WhiteIntent", "乐视手机");
                Intent letvIntent = new Intent();
                letvIntent.setAction("com.letv.android.permissionautoboot");
                letvIntent.putExtra("packageName", context.getPackageName());
                letvIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("WhiteIntent", "尝试通过Action=com.letv.android.permissionautoboot跳转自启动设置");
                if (doesActivityExists(context, letvIntent)) {
                    Log.d("WhiteIntent", "可通过Action=com.letv.android.permissionautoboot跳转自启动设置");
                    sIntentWrapperList.add(new WhiteIntentWrapper(letvIntent, LETV));
                } else {
                    Log.e("WhiteIntent", "不可通过Action=com.letv.android.permissionautoboot跳转自启动设置");
                    letvIntent = new Intent();
                    letvIntent.setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity"));
                    letvIntent.putExtra("packageName", context.getPackageName());
                    letvIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Log.d("WhiteIntent", "尝试通过com.letv.android.letvsafe.AutobootManageActivity跳转自启动设置");
                    if (doesActivityExists(context, letvIntent)) {
                        Log.d("WhiteIntent", "可通过com.letv.android.letvsafe.AutobootManageActivity跳转自启动设置");
                        sIntentWrapperList.add(new WhiteIntentWrapper(letvIntent, LETV));
                    } else {
                        Log.e("WhiteIntent", "不可通过com.letv.android.letvsafe.AutobootManageActivity跳转自启动设置");
                        letvIntent = new Intent();
                        letvIntent.setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.PermissionAndApps"));
                        letvIntent.putExtra("packageName", context.getPackageName());
                        letvIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Log.d("WhiteIntent", "尝试通过com.letv.android.letvsafe.PermissionAndApps跳转自启动设置");
                        if (doesActivityExists(context, letvIntent)) {
                            Log.d("WhiteIntent", "可通过com.letv.android.letvsafe.PermissionAndApps跳转自启动设置");
                            sIntentWrapperList.add(new WhiteIntentWrapper(letvIntent, LETV));
                        } else {
                            Log.e("WhiteIntent", "不可通过com.letv.android.letvsafe.PermissionAndApps跳转自启动设置");
                        }
                    }
                }
                //乐视 应用保护
                Intent letvGodIntent = new Intent();
                letvGodIntent.setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.BackgroundAppManageActivity"));
                letvGodIntent.putExtra("packageName", context.getPackageName());
                letvGodIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("WhiteIntent", "尝试通过com.letv.android.letvsafe.BackgroundAppManageActivity跳转后台应用保护设置");
                if (doesActivityExists(context, letvGodIntent)) {
                    Log.d("WhiteIntent", "可通过com.letv.android.letvsafe.BackgroundAppManageActivity跳转后台应用保护设置");
                    sIntentWrapperList.add(new WhiteIntentWrapper(letvGodIntent, LETV_GOD));
                } else {
                    Log.e("WhiteIntent", "不可通过com.letv.android.letvsafe.BackgroundAppManageActivity跳转后台应用保护设置");
                }
            } else if (RomUtils.INSTANCE.isFlyme()) {
                //魅族 自启动管理
                Log.d("WhiteIntent", "魅族手机");
                Intent meizuIntent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
                meizuIntent.addCategory(Intent.CATEGORY_DEFAULT);
                meizuIntent.putExtra("packageName", context.getPackageName());
                meizuIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("WhiteIntent", "尝试通过Action=com.meizu.safe.security.SHOW_APPSEC跳转自启动设置");
                if (doesActivityExists(context, meizuIntent)) {
                    Log.d("WhiteIntent", "可通过Action=com.meizu.safe.security.SHOW_APPSEC跳转自启动设置");
                    sIntentWrapperList.add(new WhiteIntentWrapper(meizuIntent, MEIZU));
                } else {
                    Log.e("WhiteIntent", "不可通过Action=com.meizu.safe.security.SHOW_APPSEC跳转自启动设置");
                    meizuIntent = new Intent();
                    meizuIntent.setComponent(ComponentName.unflattenFromString("com.meizu.safe/.permission.PermissionMainActivity"));
                    meizuIntent.putExtra("packageName", context.getPackageName());
                    meizuIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Log.d("WhiteIntent", "尝试通过com.meizu.safe.permission.PermissionMainActivity跳转自启动设置");
                    if (doesActivityExists(context, meizuIntent)) {
                        Log.d("WhiteIntent", "可通过com.meizu.safe.permission.PermissionMainActivity跳转自启动设置");
                        sIntentWrapperList.add(new WhiteIntentWrapper(meizuIntent, MEIZU));
                    } else {
                        Log.e("WhiteIntent", "不可通过com.meizu.safe.permission.PermissionMainActivity跳转自启动设置");
                    }
                }
                //魅族 待机耗电管理
                Intent meizuGodIntent = new Intent();
                meizuGodIntent.setComponent(new ComponentName("com.meizu.safe", "com.meizu.safe.powerui.PowerAppPermissionActivity"));
                meizuGodIntent.putExtra("packageName", context.getPackageName());
                meizuGodIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("WhiteIntent", "尝试通过com.meizu.safe.powerui.PowerAppPermissionActivity跳转待机耗电管理页");
                if (doesActivityExists(context, meizuGodIntent)) {
                    Log.d("WhiteIntent", "可通过com.meizu.safe.powerui.PowerAppPermissionActivity跳转待机耗电管理页");
                    sIntentWrapperList.add(new WhiteIntentWrapper(meizuGodIntent, MEIZU_GOD));
                } else {
                    Log.e("WhiteIntent", "不可通过com.meizu.safe.powerui.PowerAppPermissionActivity跳转待机耗电管理页");
                }
            } else if (RomUtils.INSTANCE.isVivo()) {
                //Vivo 后台高耗电
                Log.d("WhiteIntent", "Vivo手机");
                Intent vivoIntent = new Intent();
                vivoIntent.setComponent(ComponentName.unflattenFromString("com.iqoo.secure/.ui.phoneoptimize.AddWhiteListActivity"));
                vivoIntent.putExtra("packageName", context.getPackageName());
                vivoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("WhiteIntent", "尝试通过com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity跳转自启动设置");
                if (doesActivityExists(context, vivoIntent)) {
                    Log.d("WhiteIntent", "可通过com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity跳转自启动设置");
                    sIntentWrapperList.add(new WhiteIntentWrapper(vivoIntent, VIVO));
                } else {
                    Log.e("WhiteIntent", "不可通过com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity跳转自启动设置");
                    vivoIntent = new Intent();
                    vivoIntent.setComponent(ComponentName.unflattenFromString("com.iqoo.secure/.safeguard.PurviewTabActivity"));
                    vivoIntent.putExtra("packageName", context.getPackageName());
                    vivoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Log.d("WhiteIntent", "尝试通过com.iqoo.secure.safeguard.PurviewTabActivity跳转自启动设置");
                    if (doesActivityExists(context, vivoIntent)) {
                        Log.d("WhiteIntent", "可通过com.iqoo.secure.safeguard.PurviewTabActivity跳转自启动设置");
                        sIntentWrapperList.add(new WhiteIntentWrapper(vivoIntent, VIVO));
                    } else {
                        Log.e("WhiteIntent", "不可通过com.iqoo.secure.safeguard.PurviewTabActivity跳转自启动设置");
                        vivoIntent = new Intent();
                        vivoIntent.setComponent(ComponentName.unflattenFromString("com.android.settings/com.vivo.settings.DevelpmentSettingsActivity2"));
                        vivoIntent.putExtra("packageName", context.getPackageName());
                        vivoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Log.d("WhiteIntent", "尝试通过com.vivo.settings.DevelpmentSettingsActivity2跳转自启动设置");
                        if (doesActivityExists(context, vivoIntent)) {
                            Log.d("WhiteIntent", "可通过com.vivo.settings.DevelpmentSettingsActivity2跳转自启动设置");
                            sIntentWrapperList.add(new WhiteIntentWrapper(vivoIntent, VIVO));
                        } else {
                            Log.e("WhiteIntent", "不可通过com.vivo.settings.DevelpmentSettingsActivity2跳转自启动设置");
                        }
                    }
                }

                Intent vivoGodIntent = new Intent();
                vivoGodIntent.setComponent(new ComponentName("com.vivo.abe", "com.vivo.applicationbehaviorengine.ui.ExcessivePowerManagerActivity"));
                vivoGodIntent.putExtra("packageName", context.getPackageName());
                vivoGodIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("WhiteIntent", "尝试通过com.vivo.applicationbehaviorengine.ui.ExcessivePowerManagerActivity跳转高耗电设置");
                if (doesActivityExists(context, vivoGodIntent)) {
                    Log.d("WhiteIntent", "可通过com.vivo.applicationbehaviorengine.ui.ExcessivePowerManagerActivity跳转高耗电设置");
                    sIntentWrapperList.add(new WhiteIntentWrapper(vivoGodIntent, VIVO_GOD));
                } else {
                    Log.e("WhiteIntent", "不可通过com.vivo.applicationbehaviorengine.ui.ExcessivePowerManagerActivity跳转高耗电设置");
                }
            } else if (RomUtils.INSTANCE.isLenovo()) {
                //联想 后台管理
                Log.d("WhiteIntent", "联想手机");
                Intent lenovoIntent = new Intent();
                lenovoIntent.setComponent(new ComponentName("com.lenovo.security", "com.lenovo.security.purebackground.PureBackgroundActivity"));
                lenovoIntent.putExtra("packageName", context.getPackageName());
                lenovoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("WhiteIntent", "尝试通过com.lenovo.security.purebackground.PureBackgroundActivity跳转后台管理页");
                if (doesActivityExists(context, lenovoIntent)) {
                    Log.d("WhiteIntent", "尝试通过com.lenovo.security.purebackground.PureBackgroundActivity跳转后台管理页");
                    sIntentWrapperList.add(new WhiteIntentWrapper(lenovoIntent, LENOVO));
                } else {
                    Log.e("WhiteIntent", "不可通过com.lenovo.security.purebackground.PureBackgroundActivity跳转后台管理页");
                }
                //联想 后台耗电优化
                Intent lenovoGodIntent = new Intent();
                lenovoGodIntent.setComponent(new ComponentName("com.lenovo.powersetting", "com.lenovo.powersetting.ui.Settings$HighPowerApplicationsActivity"));
                lenovoIntent.putExtra("packageName", context.getPackageName());
                lenovoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("WhiteIntent", "尝试通过com.lenovo.powersetting.ui.Settings$HighPowerApplicationsActivity跳转后台耗电优化");
                if (doesActivityExists(context, lenovoGodIntent)) {
                    Log.d("WhiteIntent", "可通过com.lenovo.powersetting.ui.Settings$HighPowerApplicationsActivity跳转后台耗电优化");
                    sIntentWrapperList.add(new WhiteIntentWrapper(lenovoGodIntent, LENOVO_GOD));
                } else {
                    Log.e("WhiteIntent", "不可通过com.lenovo.powersetting.ui.Settings$HighPowerApplicationsActivity跳转后台耗电优化");
                }
            } else if (RomUtils.INSTANCE.isZTE()) {
                //中兴 自启管理
                Log.d("WhiteIntent", "中兴手机");
//                Intent zteIntent = new Intent();
//                zteIntent.setComponent(new ComponentName("com.zte.heartyservice", "com.zte.heartyservice.autorun.AppAutoRunManager"));
//                zteIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                Log.d("WhiteIntent", "尝试通过com.lenovo.security.purebackground.PureBackgroundActivity跳转后台管理页");
//                if (doesActivityExists(context, zteIntent)) {
//                    sIntentWrapperList.add(new WhiteIntentWrapper(zteIntent, ZTE));
//                }
//                //中兴 锁屏加速受保护应用
//                Intent zteGodIntent = new Intent();
//                zteGodIntent.setComponent(new ComponentName("com.zte.heartyservice", "com.zte.heartyservice.setting.ClearAppSettingsActivity"));
//                zteGodIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                if (doesActivityExists(context, zteGodIntent)) {
//                    sIntentWrapperList.add(new WhiteIntentWrapper(zteGodIntent, ZTE_GOD));
//                }
            } else if (RomUtils.INSTANCE.isGionee()) {
                //金立 应用自启
                Log.d("WhiteIntent", "金立手机");
                Intent gioneeIntent = new Intent();
                gioneeIntent.setComponent(new ComponentName("com.gionee.softmanager", "com.gionee.softmanager.MainActivity"));
                gioneeIntent.putExtra("packageName", context.getPackageName());
                gioneeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("WhiteIntent", "尝试通过com.gionee.softmanager.MainActivity跳转自启动设置");
                if (doesActivityExists(context, gioneeIntent)) {
                    Log.d("WhiteIntent", "可通过com.gionee.softmanager.MainActivity跳转自启动设置");
                    sIntentWrapperList.add(new WhiteIntentWrapper(gioneeIntent, GIONEE));
                } else {
                    Log.e("WhiteIntent", "不可通过com.gionee.softmanager.MainActivity跳转自启动设置");
                }
            } else if (RomUtils.INSTANCE.isKupai()) {
                //酷派 自启动管理
                Log.d("WhiteIntent", "酷派手机");
                Intent coolpadIntent = context.getPackageManager().getLaunchIntentForPackage("com.yulong.android.security");
                Log.d("WhiteIntent", "尝试通过getLaunchIntentForPackage(com.yulong.android.security)跳转酷管家");
                if (coolpadIntent != null) {
                    coolpadIntent.putExtra("packageName", context.getPackageName());
                    coolpadIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Log.d("WhiteIntent", "可通过getLaunchIntentForPackage(com.yulong.android.security)跳转酷管家");
                    sIntentWrapperList.add(new WhiteIntentWrapper(coolpadIntent, COOLPAD));
                } else {
                    Log.e("WhiteIntent", "不可通过getLaunchIntentForPackage(com.yulong.android.security)跳转酷管家");
                }
            } else if (RomUtils.INSTANCE.isSamsung()) {
                //三星自启动应用程序管理
                Log.d("WhiteIntent", "三星手机");
                Intent samsungIntent = new Intent();
                samsungIntent.setComponent(new ComponentName("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.ram.AutoRunActivity"));
                samsungIntent.putExtra("packageName", context.getPackageName());
                samsungIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("WhiteIntent", "尝试通过com.samsung.android.sm.ui.ram.AutoRunActivity跳转自启动管理页");
                if (doesActivityExists(context, samsungIntent)) {
                    Log.d("WhiteIntent", "可通过com.samsung.android.sm.ui.ram.AutoRunActivity跳转自启动管理页");
                } else {
                    Log.e("WhiteIntent", "不可通过com.samsung.android.sm.ui.ram.AutoRunActivity跳转自启动管理页");
                    samsungIntent = context.getPackageManager().getLaunchIntentForPackage("com.samsung.android.sm");
                    Log.d("WhiteIntent", "尝试通过getLaunchIntentForPackage(com.samsung.android.sm)跳转自启动管理页");
                    if (samsungIntent != null) {
                        Log.d("WhiteIntent", "可通过getLaunchIntentForPackage(com.samsung.android.sm)跳转自启动管理页");
                        samsungIntent.putExtra("packageName", context.getPackageName());
                        samsungIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        sIntentWrapperList.add(new WhiteIntentWrapper(samsungIntent, SAMSUNG));
                    } else {
                        Log.e("WhiteIntent", "不可通过getLaunchIntentForPackage(com.samsung.android.sm)跳转自启动管理页");
                        samsungIntent = new Intent();
                        samsungIntent.setComponent(new ComponentName("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.battery.BatteryActivity"));
                        samsungIntent.putExtra("packageName", context.getPackageName());
                        samsungIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Log.d("WhiteIntent", "尝试通过com.samsung.android.sm.ui.battery.BatteryActivity跳转自启动管理页");
                        if (doesActivityExists(context, samsungIntent)) {
                            Log.d("WhiteIntent", "可通过com.samsung.android.sm.ui.battery.BatteryActivity跳转自启动管理页");
                            sIntentWrapperList.add(new WhiteIntentWrapper(samsungIntent, SAMSUNG));
                        } else {
                            Log.e("WhiteIntent", "不可通过com.samsung.android.sm.ui.battery.BatteryActivity跳转自启动管理页");
                            Bundle bundle = new Bundle();
                            bundle.putString("package", context.getPackageName());

                            samsungIntent = new Intent();
                            samsungIntent.setClassName("com.android.settings",
                                    "com.android.settings.Settings");
                            samsungIntent.setAction(Intent.ACTION_MAIN);
                            samsungIntent.addCategory(Intent.CATEGORY_DEFAULT);
                            samsungIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                    Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                            samsungIntent.putExtra(":android:show_fragment",
                                    "com.android.settings.applications.AppOpsDetails");
                            samsungIntent.putExtra(":android:show_fragment_as_shortcut", false);
                            samsungIntent.putExtra(":android:show_fragment_args", bundle);
                            Log.d("WhiteIntent", "尝试通过com.android.settings.applications.AppOpsDetails跳转应用详情页-->" + samsungIntent.toString());
                            if (doesActivityExists(context, samsungIntent)) {
                                Log.d("WhiteIntent", "可通过com.android.settings.applications.AppOpsDetails跳转应用详情页");
                                sIntentWrapperList.add(new WhiteIntentWrapper(samsungIntent, SYSTEM));
                            } else {
                                Log.e("WhiteIntent", "不可通过com.android.settings.applications.AppOpsDetails跳转应用详情页");
                            }
                        }
                    }
                }
            } else if (RomUtils.INSTANCE.isSony()) {
                //索尼自启动应用程序管理
                Log.d("WhiteIntent", "索尼手机");
                Intent sonyIntent = new Intent();
                sonyIntent.setComponent(new ComponentName("com.sonymobile.cta", "com.sonymobile.cta.SomcCTAMainActivity"));
                sonyIntent.putExtra("packageName", context.getPackageName());
                sonyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("WhiteIntent", "尝试通过com.sonymobile.cta.SomcCTAMainActivity跳转自启动设置");
                if (doesActivityExists(context, sonyIntent)) {
                    Log.d("WhiteIntent", "可通过com.sonymobile.cta.SomcCTAMainActivity跳转自启动设置");
                    sIntentWrapperList.add(new WhiteIntentWrapper(sonyIntent, SONY));
                } else {
                    Log.e("WhiteIntent", "不可通过com.sonymobile.cta.SomcCTAMainActivity跳转自启动设置");
                }
            } else if (RomUtils.INSTANCE.isLG()) {
                //LG自启动应用程序管理
                Log.d("WhiteIntent", "LG手机");
                Intent lgIntent = new Intent("android.intent.action.MAIN");
                lgIntent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$AccessLockSummaryActivity"));
                lgIntent.putExtra("packageName", context.getPackageName());
                lgIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("WhiteIntent", "尝试通过com.android.settings.Settings$AccessLockSummaryActivity跳转自启动设置");
                if (doesActivityExists(context, lgIntent)) {
                    Log.d("WhiteIntent", "可通过com.android.settings.Settings$AccessLockSummaryActivity跳转自启动设置");
                    sIntentWrapperList.add(new WhiteIntentWrapper(lgIntent, LG));
                } else {
                    Log.e("WhiteIntent", "不可通过com.android.settings.Settings$AccessLockSummaryActivity跳转自启动设置");
                }
            } else if (RomUtils.INSTANCE.isHTC()) {
                //HTC 无需配置
            } else {
                //其他机器
                if (sIntentWrapperList.isEmpty()) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.fromParts("package", context.getPackageName(), null));
                    Log.d("WhiteIntent", "尝试通过android.settings.APPLICATION_DETAILS_SETTINGS Action跳转应用详情页");
                    if (doesActivityExists(context, intent)) {
                        Log.d("WhiteIntent", "可通过android.settings.APPLICATION_DETAILS_SETTINGS Action跳转应用详情页");
                        sIntentWrapperList.add(new WhiteIntentWrapper(intent, OTHER));
                    } else {
                        Log.e("WhiteIntent", "不可通过android.settings.APPLICATION_DETAILS_SETTINGS Action跳转应用详情页");
                    }
                }
            }
        }
        return sIntentWrapperList;
    }

    private static String sApplicationName;

    private static String getApplicationName(Context context) {
        if (sApplicationName == null) {
            PackageManager pm;
            ApplicationInfo ai;
            try {
                pm = context.getPackageManager();
                ai = pm.getApplicationInfo(context.getPackageName(), 0);
                sApplicationName = pm.getApplicationLabel(ai).toString();
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                sApplicationName = context.getPackageName();
            }
        }
        return sApplicationName;
    }

//    public static List<WhiteIntentWrapper> whiteListMatters(String reason) {
//        return whiteListMatters(DaemonManager.sActivity, reason);
//    }

    /**
     * 处理白名单.
     *
     * @return 弹过框的 IntentWrapper.
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static List<WhiteIntentWrapper> whiteListMatters(Activity activity, String reason) {
        List<WhiteIntentWrapper> showed = new ArrayList<>();
        final Activity a = activity;
        if (a == null) {
            return showed;
        }

        List<WhiteIntentWrapper> intentWrapperList = getIntentWrapperList(a);
        for (final WhiteIntentWrapper intent : intentWrapperList) {
            switch (intent.type) {
                case DOZE:
                    PowerManager pm = (PowerManager) a.getSystemService(Context.POWER_SERVICE);
                    if (pm.isIgnoringBatteryOptimizations(a.getPackageName())) {
                        break;
                    }

                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_doze_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_doze_content", reason, getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case SYSTEM:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_system_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_system_content", reason, getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case HUAWEI_GOD:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_hw_god_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_hw_god_content", reason, getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case HUAWEI:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_hw_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_hw_content", reason, getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case XIAOMI_GOD:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_xm_god_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_xm_god_content", reason, getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case XIAOMI:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_xm_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_xm_content", reason, getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case SAMSUNG:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_ss_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_ss_content", reason, getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case SONY:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_sony_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_sony_content", reason, getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case LG:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_lg_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_lg_content", reason, getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case MEIZU_GOD:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_mz_god_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_mz_god_content", reason, getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case MEIZU:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_mz_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_mz_content", reason, getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case ZTE_GOD:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_zte_god_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_zte_god_content", reason, getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case ZTE:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_zte_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_zte_content", reason, getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case LETV_GOD:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_le_god_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_le_god_content", reason, getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case LETV:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_le_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_le_content", reason, getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case OPPO_SM:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_oppo_sm_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_oppo_sm_content", reason, getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case OPPO_PM:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_oppo_pm_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_oppo_pm_content", reason, getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case OPPO_GOD:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_oppo_god_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_oppo_god_content", reason, getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case COOLPAD:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_cp_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_cp_content", reason, getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case VIVO:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_vv_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_vv_content", reason, getApplicationName(a), getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case VIVO_GOD:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_vv_god_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_vv_god_content", reason, getApplicationName(a), getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case GIONEE:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_jl_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_jl_content", reason, getApplicationName(a), getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case LENOVO_GOD:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_lv_god_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_lv_god_content", reason, getApplicationName(a), getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case LENOVO:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_lv_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_lv_content", reason, getApplicationName(a), getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case OTHER:
                    try {
                        new AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_system_title", getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_system_content", reason, getApplicationName(a), getApplicationName(a), getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        intent.startActivitySafely(a);
                                    }
                                })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show();
                        showed.add(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        return showed;
    }

    private static String getString(Context context, String name, Object... format) {
        try {
            int resId = context.getResources().getIdentifier(name, "string", context.getPackageName());
            if (resId > 0) {
                return context.getString(resId, format);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    private static String getMiuiVersion() {
        String propName = "ro.miui.ui.version.name";
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(
                    new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return line;
    }
}
