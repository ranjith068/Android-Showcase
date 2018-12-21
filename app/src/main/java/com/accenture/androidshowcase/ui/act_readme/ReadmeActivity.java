package com.accenture.androidshowcase.ui.act_readme;

import android.os.Bundle;

import com.accenture.androidshowcase.R;
import com.accenture.androidshowcase.databinding.ActivityReadmeBinding;
import com.accenture.androidshowcase.ui.base.BaseActivity;

import androidx.annotation.Nullable;
import br.tiagohm.markdownview.css.styles.Github;

public class ReadmeActivity extends BaseActivity<ActivityReadmeBinding> {

    private final static String README_URL
            = "https://raw.githubusercontent.com/ugurcany/Android-Showcase/develop/README.md";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getBinding().mdview.addStyleSheet(new Github());
        getBinding().mdview.loadMarkdownFromUrl(README_URL);
    }

    @Override
    public int layoutRes() {
        return R.layout.activity_readme;
    }

    @Override
    public boolean doubleClickToExitEnabled() {
        return false;
    }

}