package com.east2west.game.inApp;
import com.east2west.game.inApp.APPBaseInterface;
import com.east2west.game.inApp.InAppBase;

//commentimport android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.RemoteException;
import android.util.Log;

import com.east2west.game.E2WApp;
import com.east2west.game.QinConst;
import com.east2west.game.SdkApplication;
import com.nearme.game.sdk.GameCenterSDK;
import com.nearme.game.sdk.callback.GameExitCallback;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
//endpublic class InAppOPPOPAY20181016 extends InAppBase {
	
//comment	private InAppBase mBaseInApp = null;
	private String Channelname="InAppOPPOPAY20181016";
	private String mUid;
	private String mSession;
	private int mAccountType;
	private String oppoAppsecret="";
	@Override
	public void init(Context appContext, Activity context, final String strAppId, final String strAppSecret, APPBaseInterface appinterface)
	{		
		super.init(appContext, context, strAppId, strAppSecret,appinterface);	
		
		E2WApp.LogLocal("["+Channelname+"] strAppKey="+strAppId);
		E2WApp.LogLocal("["+Channelname+"] strAppSecret="+strAppSecret);
		E2WApp.LogLocal("["+Channelname+"] QinConst.Restoreappid="+QinConst.Restoreappid);

		//GameCenterSDK.init(oppoAppsecret,mContext);
		mBaseInApp = E2WApp.activityforappbase.getBaseInApp();
		if(QinConst.Restoreappid.equals("Samorost3"))
		{
			onPurchaseSuccess("1700");
			onPurchaseSuccess("17");
		}

		if(QinConst.Restoreappid.equals("Machinarium"))
		{
			ShowTime();
		}
		if(QinConst.Restoreappid.equals("Shadowmatic"))
		{
			onPurchaseSuccess("com.triadastudio.shadowmatic.e2w.unlock");
			onFunctionCallBack("com.triadastudio.shadowmatic.e2w.unlock");
		}
	}
	public void ShowTime()
	{

		new Thread(new Runnable()
		{
			public void run()
			{
				int count=0;
				while(true)
				{
					try
					{
						count++;
						Thread.sleep(1000L);
						Log.e("IAP", "count="+count);
						if(count==60)
						{
							//handler.sendEmptyMessage(0);
							onPurchaseSuccess("UnlockGame");
							break;
						}

					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public void AnalysisID(String IDString)
	{
		try
		{
			String[] strArray=null;
			strArray = convertStrToArray(IDString,",");
			oppoAppsecret=strArray[0].toString();
			E2WApp.LogLocal("["+Channelname+"] oppoAppsecret="+oppoAppsecret);
		}
		catch(Exception E)
		{
			E2WApp.LogLocal("[AnalysisID]Error="+E);
		}
	}
	public static String[] convertStrToArray(String str,String symbol){
		String[] strArray = null;
		strArray = str.split(symbol); //拆分字符为symbol 可以是 "," ,然后把结果交给数组strArray
		return strArray;
	}
	public void ApplicationInit(Application appcontext)
	{
		mAppContext=appcontext;
		AnalysisID(QinConst.APPID);
		E2WApp.LogLocal("["+Channelname+"]------------------>init:InAppBase.ApplicationInit="+oppoAppsecret);
		GameCenterSDK.init(oppoAppsecret,appcontext);
	}
	@Override
	public void purchase(final String strProductId, final String strProductDescription, final float fPrice)
	{
		super.purchase(strProductId, strProductDescription, fPrice);
		purchaseProduct();
	}
	@Override
	public void onPause()
	{		
		E2WApp.LogLocal("["+Channelname+"] onPause");
	}
	
	@Override
	public void onResume()
	{
		E2WApp.LogLocal("["+Channelname+"] onResume");
	}
	@Override
	public void onDestroy()
	{
		E2WApp.LogLocal("["+Channelname+"] onDestroy");
	}
	@Override
	public void onStop() 
	{
		E2WApp.LogLocal("["+Channelname+"] onStop");
	}
	@Override
	public void onStart() 
	{
		E2WApp.LogLocal("["+Channelname+"] onStart");
	}
	@Override
	public void onRestart()
	{
		E2WApp.LogLocal("["+Channelname+"] onRestart");
		
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		E2WApp.LogLocal("["+Channelname+"] onActivityResult(int requestCode, int resultCode, Intent data)");
	}
	@Override
	public void onNewIntent(Intent intent) 
	{
		E2WApp.LogLocal("["+Channelname+"] onNewIntent(Intent intent) ");
	}
	


	@Override
	public void ExitGame()
	{

		GameCenterSDK.getInstance().onExit((Activity) E2WApp.mContext,
				new GameExitCallback() {

					@Override
					public void exitGame() {
						// CP 实现游戏退出操作，也可以直接调用
						// AppUtil工具类里面的实现直接强杀进程~
						mBaseInApp = E2WApp.activityforappbase.getBaseInApp();
						mBaseInApp.ExitGame();
					}
				});
	}


	private void purchaseProduct()
	{
		E2WApp.LogLocal("["+Channelname+"] CarriersPayLock="+QinConst.CarriersPayLock);
		E2WApp.LogLocal("["+Channelname+"] SDKPayLock="+QinConst.SDKPayLock);
		if(QinConst.Restoreappid.equals("Samorost3"))
		{
			onPurchaseSuccess("1700");
			onPurchaseSuccess("17");
		}

		if(QinConst.Restoreappid.equals("Machinarium"))
		{
			ShowTime();
		}
		if(QinConst.Restoreappid.equals("Shadowmatic"))
		{
			onPurchaseSuccess("com.triadastudio.shadowmatic.e2w.unlock");
			onFunctionCallBack("com.triadastudio.shadowmatic.e2w.unlock");
		}
		if(QinConst.CarriersPayLock.equals("0")&&QinConst.SDKPayLock.equals("0"))
		{
			
		}
		else if(QinConst.CarriersPayLock.equals("1")&&QinConst.SDKPayLock.equals("0"))
		{
			 CarriersPay();
		}
		else if(QinConst.CarriersPayLock.equals("0")&&QinConst.SDKPayLock.equals("1"))
		{
			ChannelPay();
		}
		else if(QinConst.CarriersPayLock.equals("1")&&QinConst.SDKPayLock.equals("1"))
		{
			DoublePay();
		}
	}
	public void CarriersPay()
	{
		if (mBaseInApp != null&&SdkApplication.iscarriersneed.equals("open")) 
		{
			mBaseInApp.purchase(QinConst.CarriersID, mProductDescription, mProductPrice);
		}
		else
		{
			E2WApp.LogLocal("["+Channelname+"] MOBILE_SPLASH Closed...Can't Use Carrier's Pay");
		}
	}
	public void ChannelPay()
	{
		Log.e(QinConst.TAG,"mProductId="+mProductId);
		if(QinConst.Restoreappid.equals("Samorost3"))
		{
			onPurchaseSuccess("1700");
			onPurchaseSuccess("17");
		}

		if(QinConst.Restoreappid.equals("Machinarium"))
		{
			ShowTime();
		}
		if(QinConst.Restoreappid.equals("Shadowmatic"))
		{
			onPurchaseSuccess("com.triadastudio.shadowmatic.e2w.unlock");
			onFunctionCallBack("com.triadastudio.shadowmatic.e2w.unlock");
		}
	}
	public void DoublePay()
	{
		try {
			AlertDialog.Builder builder = new Builder(mContext);
			builder.setMessage("选择支付方式");
			builder.setTitle("提示");
			builder.setPositiveButton("小米支付", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					ChannelPay();
				}
			});
			builder.setNeutralButton("短信支付", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					CarriersPay();
				}
			});
			builder.setNegativeButton("取消", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					onPurchaseFailed(Channelname);
					dialog.dismiss();
				}
			});
			builder.create().show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//end}
