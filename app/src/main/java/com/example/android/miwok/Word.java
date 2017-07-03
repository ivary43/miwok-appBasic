package com.example.android.miwok;

/**
 * Created by manish on 23/6/17.
 * this is a generic class to return
 * values wrapped in objects like we will not always need
 * a String or integer to set in the arraylist
 * we may require a bunch of data together
 * for that we will need a class
 *

 */
//
//
public class Word {
    private  String MiwokWord ;
    private  String DefalutWord ;
    private Integer imageResourceId;
    private  int maudioResourceID ;

    private  Boolean check = false ;



    Word( String name1 , String name2 ,Integer image ,int maudioResourceID ) {
        this.MiwokWord = name1;
        this.DefalutWord = name2;
        this.imageResourceId=image ;
        this.maudioResourceID=maudioResourceID ;
        check = true ;


    }
    Word( String name1 ,String name2  ,int audioID)
    {
        this.MiwokWord = name1;
        this.DefalutWord = name2;
        this.maudioResourceID=audioID ;

    }

    public String getDefalutWord() {
        return DefalutWord;
    }

    public String getMiwokWord() {
        return MiwokWord;
    }

    public Integer getImageResourceId() {
        return imageResourceId;
    }

    public boolean checkStatus ()
    {
        if( check == true )
        { return  true ;}
        else {
            return  false ;
        }
    }

    public int getMaudioResourceID() {
        return maudioResourceID;
    }

}
