package com.yltx.modulewd.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yltx.modulebase.util.ACache;
import com.yltx.modulewd.authentication.OpenMemberYActivity;
import com.yltx.modulewd.borrow.borrowoperate.OperateStatusActivity;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//		setContentView(R.layout.pay_result);
        String wxappid = ACache.get(this).getAsString("WXAPPID");
        api = WXAPIFactory.createWXAPI(this, wxappid);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
            Toast.makeText(this, "code = " + ((SendAuth.Resp) baseResp).code, Toast.LENGTH_SHORT).show();
            Log.e("WXPayEntryActivity", "code = " + ((SendAuth.Resp) baseResp).code);
        }

        String result;

        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = "微信支付成功";
//                startActivity(new Intent(this, OpenMemberYActivity.class));
//                finish();
                toSuccess();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "微信支付取消";
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "微信拒绝访问";
                finish();
                break;
            default:
                result = "微信未知错误";
                finish();
                break;
        }

        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

    private void toSuccess() {
        String WxType = ACache.get(WXPayEntryActivity.this).getAsString("WXTYPE");
        switch (WxType) {
            case "OPENVIP":
                startActivity(new Intent(WXPayEntryActivity.this, OpenMemberYActivity.class));
                finish();
                break;
            case "REPAYMENT":
                Intent intent = new Intent(WXPayEntryActivity.this, OperateStatusActivity.class);
//                intent.putExtra("borrowId", borrowId);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

}