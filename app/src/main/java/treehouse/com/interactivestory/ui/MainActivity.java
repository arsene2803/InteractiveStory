package treehouse.com.interactivestory.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import treehouse.com.interactivestory.R;

public class MainActivity extends AppCompatActivity {
    private EditText mNameField;
    private Button mStartbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNameField=(EditText)findViewById(R.id.editTextButton);
        mStartbutton=(Button)findViewById(R.id.startButton);

        mStartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=mNameField.getText().toString();
                Toast.makeText(MainActivity.this,name,Toast.LENGTH_LONG).show();
                startStory(name);

            }
        });
    }

    private void startStory(String name) {
        Intent intent=new Intent(this,StoryActivity.class);
        intent.putExtra(getString(R.string.key_name),name);
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //mNameField.setText("");
    }
}
