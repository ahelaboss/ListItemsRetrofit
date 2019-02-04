package com.yourgains.listitemsretrofit.data.model;

import android.os.Parcel;

/**
 * Created by alexeyshishov
 * on 2/3/19.
 */
public class AnimalModel extends BaseModel{

    public String mAvatar;
    public String mTitle;
    public String mDescription;

    public AnimalModel(String avatar, String title, String description) {
        mAvatar = avatar;
        mTitle = title;
        mDescription = description;
    }

    protected AnimalModel(Parcel in) {
        mAvatar = in.readString();
        mTitle = in.readString();
        mDescription = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mAvatar);
        dest.writeString(mTitle);
        dest.writeString(mDescription);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AnimalModel> CREATOR = new Creator<AnimalModel>() {
        @Override
        public AnimalModel createFromParcel(Parcel in) {
            return new AnimalModel(in);
        }

        @Override
        public AnimalModel[] newArray(int size) {
            return new AnimalModel[size];
        }
    };
}
