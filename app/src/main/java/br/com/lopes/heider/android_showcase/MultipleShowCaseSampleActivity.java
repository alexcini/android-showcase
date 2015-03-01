package br.com.lopes.heider.android_showcase;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.amlcurran.showcaseview.ApiUtils;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

public class MultipleShowCaseSampleActivity extends Activity implements View.OnClickListener {

    private ShowcaseView showcaseView;
    private int counter = 0;
    private Button button ;
    private TextView textView1;
    private TextView textView2;
    private final ApiUtils apiUtils = new ApiUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_show_case);

        button = (Button) findViewById(R.id.button);
        textView1 = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);

        RelativeLayout.LayoutParams lps = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lps.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lps.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        int margin = ((Number) (getResources().getDisplayMetrics().density * 12)).intValue();
        lps.setMargins(margin, margin + 36, margin, 180);

        showcaseView = new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(findViewById(R.id.button)))
                .setContentTitle(getString(R.string.title) + 1)
                .setContentText(getString(R.string.summary) + 1)
                .setOnClickListener(this)
                .build();
        showcaseView.setButtonPosition(lps);
        showcaseView.setButtonText(getString(R.string.next));
        setAlpha(0.1f, textView1, textView2);

    }


    private void setAlpha(float alpha, View... views) {
        if (apiUtils.isCompatWithHoneycomb()) {
            for (View view : views) {
                view.setAlpha(alpha);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (counter) {
            case 0:
                showcaseView.setContentTitle(getString(R.string.title) + 2);
                showcaseView.setContentText(getString(R.string.summary) + 2);
                showcaseView.setShowcase(new ViewTarget(textView1), true);
                setAlpha(0.1f, button, textView2);
                setAlpha(1.0f, textView1);
                break;

            case 1:
                showcaseView.setContentTitle(getString(R.string.title) + 3);
                showcaseView.setContentText(getString(R.string.summary) + 3);
                showcaseView.setShowcase(new ViewTarget(textView2), true);
                setAlpha(0.1f, button, textView1);
                setAlpha(1.0f, textView2);
                break;

            case 2:
                showcaseView.setTarget(Target.NONE);
                showcaseView.setContentTitle(getString(R.string.title_finish));
                showcaseView.setContentText(getString(R.string.sum_finish));
                showcaseView.setButtonText(getString(R.string.close));
                setAlpha(0.1f, button, textView1, textView2);
                break;

            case 3:
                showcaseView.hide();
                setAlpha(1.0f, button, textView1, textView2);
                break;
        }
        counter++;
    }
}
