package me.letitfly.mat.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Trumeet on 2017/3/2.
 * A forum.
 * @author Trumeet
 */

public class ForumNav implements Parcelable{
    private Forum[] forums;
    public Forum[] getForums () {
        return forums;
    }

    protected ForumNav(Parcel in) {
        forums = in.createTypedArray(Forum.CREATOR);
    }

    public static final Creator<ForumNav> CREATOR = new Creator<ForumNav>() {
        @Override
        public ForumNav createFromParcel(Parcel in) {
            return new ForumNav(in);
        }

        @Override
        public ForumNav[] newArray(int size) {
            return new ForumNav[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(forums, i);
    }

    public static class Forum implements Parcelable{
        String fid;
        String type;
        String name;
        String fup;
        String status;

        @Override
        public String toString () {
            return String.valueOf(getFid());
        }

        public String getFid() {
            return fid;
        }

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public String getFup() {
            return fup;
        }

        public String getStatus() {
            return status;
        }

        protected Forum(Parcel in) {
            fid = in.readString();
            type = in.readString();
            name = in.readString();
            fup = in.readString();
            status = in.readString();
        }

        public static final Creator<Forum> CREATOR = new Creator<Forum>() {
            @Override
            public Forum createFromParcel(Parcel in) {
                return new Forum(in);
            }

            @Override
            public Forum[] newArray(int size) {
                return new Forum[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(fid);
            parcel.writeString(type);
            parcel.writeString(name);
            parcel.writeString(fup);
            parcel.writeString(status);
        }
    }

    @Override
    public String toString () {
        return "Forums:" + forums.length;
    }
}
