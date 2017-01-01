package com.egos.samples.loader;

import android.content.Context;
import android.widget.SearchView;

/**
 * Created by Egos on 2016/12/26.
 */
public class MySearchView extends SearchView {
    public MySearchView(Context context) {
        super(context);
    }

    // The normal SearchView doesn't clear its search text when
    // collapsed, so we will do this for it.
    @Override
    public void onActionViewCollapsed() {
        setQuery("", false);
        super.onActionViewCollapsed();
    }
}
