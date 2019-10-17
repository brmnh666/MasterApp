package com.ying.administrator.masterappdemo.app;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

public class SampleApplication extends TinkerApplication {
    public SampleApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.ying.administrator.masterappdemo.app.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}