package qsbk.app.adapter;

import qsbk.app.adapter.ArticleAdapter.ViewHolder;
import qsbk.app.model.Article;
import qsbk.app.widget.ObservableTextView.OnTextMoreListener;

class q implements OnTextMoreListener {
    final /* synthetic */ ViewHolder a;
    final /* synthetic */ Article b;
    final /* synthetic */ ArticleAdapter c;

    q(ArticleAdapter articleAdapter, ViewHolder viewHolder, Article article) {
        this.c = articleAdapter;
        this.a = viewHolder;
        this.b = article;
    }

    public void onHasEllipsize() {
        this.a.content.setOnTextMoreListener(null);
        this.c.a(this.a, this.b, false);
    }

    public void onTextMore() {
        this.a.content.setOnTextMoreListener(null);
        this.c.a(this.a, this.b, false);
    }
}
