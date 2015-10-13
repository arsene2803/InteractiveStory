package treehouse.com.interactivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import treehouse.com.interactivestory.R;
import treehouse.com.interactivestory.model.Page;
import treehouse.com.interactivestory.model.Story;

public class StoryActivity extends AppCompatActivity {
    private Page mCurrentPage;
    public static final String TAG = StoryActivity.class.getSimpleName();
    private String name;
    private Story mStory;
    private ImageView mImageView;
    private TextView mTextView;
    private Button mChoiceButton1;
    private Button mChoiceButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        mStory = new Story();
        mImageView = (ImageView) findViewById(R.id.storyImageView);
        mTextView = (TextView) findViewById(R.id.storyText);
        mChoiceButton1 = (Button) findViewById(R.id.choiceButton1);
        mChoiceButton2 = (Button) findViewById(R.id.choiceButton2);
        Intent intent = getIntent();
        name = intent.getStringExtra(getString(R.string.key_name));
        loadPage(0);


    }

    private void loadPage(int pageNumber) {
        mCurrentPage = mStory.getPage(pageNumber);
        Drawable drawable = getResources().getDrawable(mCurrentPage.getImageId());
        String pageText = mCurrentPage.getText();
        pageText = String.format(pageText, name);
        mTextView.setText(pageText);
        mImageView.setImageDrawable(drawable);


        if (mCurrentPage.isFinal()) {
            mChoiceButton1.setVisibility(View.INVISIBLE);
            mChoiceButton2.setText("PLAY AGAIN");
            mChoiceButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        } else {
            mChoiceButton1.setText(mCurrentPage.getChoice1().getText());
            mChoiceButton2.setText(mCurrentPage.getChoice2().getText());
            mChoiceButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nextPage = mCurrentPage.getChoice1().getNextPage();
                    loadPage(nextPage);

                }
            });
            mChoiceButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nextPage = mCurrentPage.getChoice2().getNextPage();
                    loadPage(nextPage);

                }
            });


        }


    }

}
