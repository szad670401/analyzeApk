package cn.xiaochuankeji.tieba.ui.discovery.a;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.topic.Category;
import java.util.ArrayList;

public class a extends Adapter<b> implements OnClickListener {
    private Context a;
    private LayoutInflater b;
    private ArrayList<Category> c;
    private long d;
    private a e = null;

    public interface a {
        void a(View view, long j);
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((b) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public a(Context context, ArrayList<Category> arrayList, long j) {
        this.a = context;
        this.b = LayoutInflater.from(this.a);
        this.c = arrayList;
        this.d = j;
    }

    public b a(ViewGroup viewGroup, int i) {
        View inflate = this.b.inflate(R.layout.view_item_topic_category, viewGroup, false);
        b bVar = new b(inflate);
        bVar.a = (TextView) inflate.findViewById(R.id.tvPgcCategoryName);
        bVar.b = inflate.findViewById(R.id.vTopicBottomLine);
        return bVar;
    }

    public void a(b bVar, int i) {
        Category category = (Category) this.c.get(i);
        bVar.a.setText(category.categoryName);
        bVar.itemView.setTag(Long.valueOf(category.categoryId));
        if (this.d == category.categoryId) {
            bVar.a.setTextColor(c.a.d.a.a.a().a((int) R.color.CM));
            bVar.b.setVisibility(0);
        } else {
            bVar.a.setTextColor(c.a.d.a.a.a().a((int) R.color.CT_2));
            bVar.b.setVisibility(4);
        }
        bVar.itemView.setOnClickListener(this);
    }

    public int getItemCount() {
        return this.c.size();
    }

    public void onClick(View view) {
        if (this.e != null) {
            this.d = ((Long) view.getTag()).longValue();
            notifyDataSetChanged();
            this.e.a(view, this.d);
        }
    }

    public void a(long j) {
        this.d = j;
        notifyDataSetChanged();
    }

    public void a(a aVar) {
        this.e = aVar;
    }
}
