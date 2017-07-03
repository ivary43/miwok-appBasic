package com.example.android.miwok;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class familyMember extends AppCompatActivity {
    private MediaPlayer mediaPlayer ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_member);


        final ArrayList<Word> family_array = new ArrayList<Word> ();
        family_array.add(new Word("әpә","Father",R.drawable.family_father,R.raw.family_father));
        family_array.add(new Word("әṭa","Mother",R.drawable.family_mother,R.raw.family_mother));
        family_array.add(new Word("angsi","Son",R.drawable.family_son,R.raw.family_son));
        family_array.add(new Word( "tune","Daughter",R.drawable.family_daughter,R.raw.family_daughter));
        family_array.add(new Word( "taachi","Older Brother",R.drawable.family_older_brother,R.raw.family_older_brother));
        family_array.add( new Word( "chalitti","Younger brother",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        family_array.add( new Word( "teṭe","Older Sister",R.drawable.family_older_sister,R.raw.family_older_sister));
        family_array.add(new Word( "kolliti","Younger sister",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        family_array.add(new Word( "ama","Grandmother",R.drawable.family_grandmother,R.raw.family_grandmother));
        family_array.add( new Word( "paapa","grandfather",R.drawable.family_grandfather,R.raw.family_grandfather));




        // data parsing to the family adapter
        Wordadapter familyadapter = new Wordadapter(this , family_array,R.color.category_family) ;

        ListView listView = (ListView) findViewById(R.id.list_family);

        listView.setAdapter(familyadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Word oncall = family_array.get(position);
                mediaPlayer=MediaPlayer.create(familyMember.this,oncall.getMaudioResourceID()) ;

                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        if( mediaPlayer != null )
                        {

                            mediaPlayer.release();

                            mediaPlayer = null ;



                        }
                    }
                });


            }
        });


        ActionBar actionBar =getSupportActionBar() ;
        if( actionBar != null)
        {   actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return  true ;
    }
}
