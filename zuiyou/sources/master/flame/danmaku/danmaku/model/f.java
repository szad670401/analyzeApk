package master.flame.danmaku.danmaku.model;

public class f {
    public long a;
    private long b;

    public long a(long j) {
        this.b = j - this.a;
        this.a = j;
        return this.b;
    }

    public long b(long j) {
        return a(this.a + j);
    }
}
