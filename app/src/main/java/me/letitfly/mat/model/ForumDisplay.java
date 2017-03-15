package me.letitfly.mat.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Trumeet on 2017/3/2.
 * forumdisplay
 * @author Trumeet
 */

public class ForumDisplay implements Parcelable{
    private String tpp;

    protected ForumDisplay(Parcel in) {
        tpp = in.readString();
        page = in.readString();
        reward_unit = in.readString();
        forum = in.readParcelable(Forum.class.getClassLoader());
        forum_threadlist = in.createTypedArray(Thread.CREATOR);
    }

    public static final Creator<ForumDisplay> CREATOR = new Creator<ForumDisplay>() {
        @Override
        public ForumDisplay createFromParcel(Parcel in) {
            return new ForumDisplay(in);
        }

        @Override
        public ForumDisplay[] newArray(int size) {
            return new ForumDisplay[size];
        }
    };

    public String getTpp() {
        return tpp;
    }

    public String getPage() {
        return page;
    }

    public String getReward_unit() {
        return reward_unit;
    }

    public Forum getForum() {
        return forum;
    }

    public Thread[] getForum_threadlist() {
        return forum_threadlist;
    }

    private String page;
    private String reward_unit;
    private Forum forum;
    private Thread[] forum_threadlist;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(tpp);
        parcel.writeString(page);
        parcel.writeString(reward_unit);
        parcel.writeParcelable(forum, i);
        parcel.writeTypedArray(forum_threadlist, i);
    }

    public static class Thread implements Parcelable {
        public String getTid() {
            return tid;
        }

        public String getTypeid() {
            return typeid;
        }

        public String getReadperm() {
            return readperm;
        }

        public String getPrice() {
            return price;
        }

        public String getAuthor() {
            return author;
        }

        public String getAuthorid() {
            return authorid;
        }

        public String getSubject() {
            return subject;
        }

        public String getDateline() {
            return dateline;
        }

        public String getLastpost() {
            return lastpost;
        }

        public String getLastposter() {
            return lastposter;
        }

        public String getViews() {
            return views;
        }

        public String getReplies() {
            return replies;
        }

        public String getDisplayorder() {
            return displayorder;
        }

        public String getDigest() {
            return digest;
        }

        public String getSpecial() {
            return special;
        }

        public String getAttachment() {
            return attachment;
        }

        public String getRecommend_add() {
            return recommend_add;
        }

        public String getReplycredit() {
            return replycredit;
        }

        public String getDbdateline() {
            return dbdateline;
        }

        public String getDblastpost() {
            return dblastpost;
        }

        public String getRushreply() {
            return rushreply;
        }

        public Reply[] getReply() {
            return reply;
        }

        private String tid;
        private String typeid;
        private String readperm;
        private String price;
        private String author;
        private String authorid;
        private String subject;
        private String dateline;
        private String lastpost;
        private String lastposter;
        private String views;
        private String replies;
        private String displayorder;
        private String digest;
        private String special;
        private String attachment;
        private String recommend_add;
        private String replycredit;
        private String dbdateline;
        private String dblastpost;
        private String rushreply;
        private Reply[] reply;

        public static class Reply implements Parcelable {
            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            private String pid;
            private String author;
            private String authorid;
            private String message;
            private String avatar = "";

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getAuthorid() {
                return authorid;
            }

            public void setAuthorid(String authorid) {
                this.authorid = authorid;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            protected Reply(Parcel in) {
                pid = in.readString();
                author = in.readString();
                authorid = in.readString();
                message = in.readString();
                avatar = in.readString();
            }

            public static final Creator<Reply> CREATOR = new Creator<Reply>() {
                @Override
                public Reply createFromParcel(Parcel in) {
                    return new Reply(in);
                }

                @Override
                public Reply[] newArray(int size) {
                    return new Reply[size];
                }
            };

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(pid);
                parcel.writeString(author);
                parcel.writeString(authorid);
                parcel.writeString(message);
                parcel.writeString(avatar);
            }
        }

        protected Thread(Parcel in) {
            tid = in.readString();
            typeid = in.readString();
            readperm = in.readString();
            price = in.readString();
            author = in.readString();
            authorid = in.readString();
            subject = in.readString();
            dateline = in.readString();
            lastpost = in.readString();
            lastposter = in.readString();
            views = in.readString();
            replies = in.readString();
            displayorder = in.readString();
            digest = in.readString();
            special = in.readString();
            attachment = in.readString();
            recommend_add = in.readString();
            replycredit = in.readString();
            dbdateline = in.readString();
            dblastpost = in.readString();
            rushreply = in.readString();
            reply = in.createTypedArray(Reply.CREATOR);
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(tid);
            dest.writeString(typeid);
            dest.writeString(readperm);
            dest.writeString(price);
            dest.writeString(author);
            dest.writeString(authorid);
            dest.writeString(subject);
            dest.writeString(dateline);
            dest.writeString(lastpost);
            dest.writeString(lastposter);
            dest.writeString(views);
            dest.writeString(replies);
            dest.writeString(displayorder);
            dest.writeString(digest);
            dest.writeString(special);
            dest.writeString(attachment);
            dest.writeString(recommend_add);
            dest.writeString(replycredit);
            dest.writeString(dbdateline);
            dest.writeString(dblastpost);
            dest.writeString(rushreply);
            dest.writeTypedArray(reply, flags);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Thread> CREATOR = new Creator<Thread>() {
            @Override
            public Thread createFromParcel(Parcel in) {
                return new Thread(in);
            }

            @Override
            public Thread[] newArray(int size) {
                return new Thread[size];
            }
        };
    }

    private static class Forum implements Parcelable {
        private String fid;
        private String description;
        private String rules;
        private String name;
        private String threads;

        protected Forum(Parcel in) {
            fid = in.readString();
            description = in.readString();
            rules = in.readString();
            name = in.readString();
            threads = in.readString();
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
            parcel.writeString(description);
            parcel.writeString(rules);
            parcel.writeString(name);
            parcel.writeString(threads);
        }
    }
}
