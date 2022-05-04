package com.bilmatch.bilfoot.view.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bilmatch.bilfoot.R;
import com.bilmatch.bilfoot.controllers.RegistrationDefiningController;

import java.util.ArrayList;

public class PositionSelectionActivity extends AppCompatActivity {

    //Registration controller singleton
    RegistrationDefiningController registrationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_selection);

        registrationController = RegistrationDefiningController.getInstance();

        givePositionListeners();




    }

    private void givePositionListeners() {
        RelativeLayout positionLayout = findViewById(R.id.positionLayout);

        ArrayList<View> positions = getViewsByTag(positionLayout,"position_layout");

        View.OnClickListener viewClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtPosition = view.findViewWithTag("position_text");
                String positionText = txtPosition == null ? null : txtPosition.getText().toString();


                if(registrationController.preferredPositions.contains(positionText)) {
                    view.setAlpha(0.7f);
                    registrationController.preferredPositions.remove(positionText);
                }else {
                    view.setAlpha(1);
                    registrationController.preferredPositions.add(positionText);
                }

            }
        };

        for (View view:positions) {
            view.setOnClickListener(viewClickListener);
        }
    }

    public static ArrayList<View> getViewsByTag(ViewGroup root, String tag) {
        ArrayList<View> views = new ArrayList<View>();
        final int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = root.getChildAt(i);
            if (child instanceof ViewGroup) {
                views.addAll(getViewsByTag((ViewGroup) child, tag));
            }

            final Object tagObj = child.getTag();
            if (tagObj != null && tagObj.equals(tag)) {
                views.add(child);
            }

        }
        return views;
    }
}