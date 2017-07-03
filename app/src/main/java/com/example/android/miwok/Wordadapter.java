package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by manish on 24/6/17.
 */

public class Wordadapter extends ArrayAdapter<Word> {
    private  int mResourceId ;


    public  Wordadapter(Activity context , ArrayList<Word> trWord,int colorId)
     {



        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context,0,trWord);
        this.mResourceId = colorId ;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView =convertView ;


        if( listItemView == null )
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false) ;

        }
        Word currentWord=getItem(position) ;




        TextView miwokTextview = (TextView)listItemView.findViewById(R.id.miwokTransaltion) ;
        miwokTextview.setText(currentWord.getMiwokWord());




        TextView defaultTextView = ( TextView)listItemView.findViewById(R.id.defaultTranslation) ;
        defaultTextView.setText(currentWord.getDefalutWord());




        View containerview = listItemView.findViewById(R.id.root_linearView) ;



             int color = ContextCompat.getColor(getContext(),mResourceId) ;
            containerview.setBackgroundColor(color);




        ImageView numberImageview = (ImageView)listItemView.findViewById(R.id.number_image_view) ;
        if( currentWord.checkStatus() ) {
            numberImageview.setImageResource(currentWord.getImageResourceId());
            numberImageview.setVisibility(View.VISIBLE);
        }
        else {
            numberImageview.setVisibility(View.GONE);
        }





        return  listItemView ;


    }
}
