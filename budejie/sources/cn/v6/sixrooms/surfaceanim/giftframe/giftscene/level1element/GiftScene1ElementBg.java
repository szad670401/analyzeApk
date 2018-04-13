package cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level1element;

import android.graphics.Bitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.GiftSceneElementBg;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import java.io.File;

public class GiftScene1ElementBg extends GiftSceneElementBg {
    public GiftScene1ElementBg(AnimScene animScene) {
        super(animScene);
    }

    public Bitmap elementBg() {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mGiftScene.getSceneParameter().getResPath() + File.separator + "bg_black.png");
    }
}