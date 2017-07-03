package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.R.attr.x;

public class colorActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private AudioManager maudioManager;

    // AudioManger applies a inner class OnAudioFocusChangeListener
    //which is a interface used to override onAudioFocus change
    // to handle the interruptions
    // like on click listeners

    final AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                mediaPlayer.pause();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                mediaPlayer.stop();
                releaseMedia();

            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            }


        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMedia();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_color);
        maudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> colorsarray = new ArrayList<Word>();
        colorsarray.add(new Word("weṭeṭṭi", "Red", R.drawable.color_red, R.raw.color_red));
        colorsarray.add(new Word("chokokki", "Green", R.drawable.color_green, R.raw.color_green));
        colorsarray.add(new Word("ṭakaakki", "Brown", R.drawable.color_brown, R.raw.color_brown));
        colorsarray.add(new Word("ṭopoppi", "Gray", R.drawable.color_gray, R.raw.color_gray));
        colorsarray.add(new Word("kululli", "Black", R.drawable.color_black, R.raw.color_black));
        colorsarray.add(new Word("kelelli", "White", R.drawable.color_white, R.raw.color_white));
        colorsarray.add(new Word("ṭopiisә", "Dusty Yellow", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colorsarray.add(new Word("chiwiiṭә", "Mustard Yellow", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        Wordadapter coloradapter = new Wordadapter(this, colorsarray, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list_color);
        listView.setAdapter(coloradapter);

        /**
         * setOnitemClickListener
         *
         * @param parent takes the parent view type ;
         * @param view taked the view which is present within the parent
         *  @position takes in the position where the item was clicked
         *
         *  @id returns the id of the psoition which was clicked
         *
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Word oncall = colorsarray.get(position);

                /** setup audioFocusRequst
                 @param afchangeListener is passsed to let know the the function where we have to pass the output
                  * to make a change when audiofocus change occurs
                  * @param AudioManager.STREAM_MUSIC to get the volume of the music or app or whatever is playing
                 * @param AudioManager.AUDIOFOCUS_GAIN_TRANSIENT to get the the duration of time for rquesting audio focus
                 *
                 */


                int result = maudioManager.requestAudioFocus(afChangeListener,
                        //Use the music stream to play
                        AudioManager.STREAM_MUSIC,
                        //Request transient focus duration
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                // if the request is granted

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // setUp the music on the device
                    mediaPlayer = MediaPlayer.create(colorActivity.this, oncall.getMaudioResourceID());

                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(mCompletionListener);

                }


            }
        });


        // setting up an navigatiojn bar
        ActionBar actionBar =getSupportActionBar() ;
        if( actionBar != null)
        {   actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

        }





    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true ;

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMedia();

    }


    private void releaseMedia() {
        if (mediaPlayer != null) {

            mediaPlayer.release();
            mediaPlayer = null;
            maudioManager.abandonAudioFocus(afChangeListener);

        }
    }





}
