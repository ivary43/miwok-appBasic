package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;

public class phrasesActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        final ArrayList<Word> phrases= new ArrayList<Word>() ;
        phrases.add(new Word("minto wuksus","Where are you going?",R.raw.phrase_where_are_you_going));
        phrases.add(new Word("tinnә oyaase'nә","What is your name?",R.raw.phrase_what_is_your_name));
        phrases.add(new Word("oyaaset...","My name is...",R.raw.phrase_my_name_is));
        phrases.add(new Word("michәksәs?","How are you feeling?",R.raw.phrase_how_are_you_feeling));
        phrases.add(new Word("kuchi achit","I’m feeling good.",R.raw.phrase_im_feeling_good));
        phrases.add(new Word("әәnәs'aa?","Are you coming?",R.raw.phrase_are_you_coming));
        phrases.add(new Word("hәә’ әәnәm","Yes, I’m coming.",R.raw.phrase_yes_im_coming));
        phrases.add(new Word("yoowutis", "Let’s go.",R.raw.phrase_lets_go));
        phrases.add(new Word("әnni'nem","Come here.",R.raw.phrase_come_here));


        // setting up the wordaadpter plus arraylist on the object

        Wordadapter phrasesadapter = new Wordadapter(this ,phrases,R.color.category_phrases);

        ListView listView = ( ListView)findViewById(R.id.list_phrases) ;

        listView.setAdapter(phrasesadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Word oncall = phrases.get(position);
                mediaPlayer=MediaPlayer.create(phrasesActivity.this,oncall.getMaudioResourceID()) ;

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
