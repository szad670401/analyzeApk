package cn.v6.sixrooms.hall;

final class av implements Runnable {
    final /* synthetic */ MineFragment a;

    av(MineFragment mineFragment) {
        this.a = mineFragment;
    }

    public final void run() {
        MineFragment.e(this.a).sendEmptyMessage(1);
    }
}
