package me.letitfly.mat.model;

/**
 * Created by Trumeet on 2017/3/15.
 * A thread
 * @author Trumeet
 */

public class ThreadView {
    private Thread thread;
    private String fid;
    private Post[] postlist;

    public Thread getThread() {
        return thread;
    }

    public String getFid() {
        return fid;
    }

    public Post[] getPostlist() {
        return postlist;
    }

    public static class Thread {
        private String tid;
        private String fid;
        private String posttableid;
        private String typeid;
        private String sortid;
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
        private String highlight;
        private String digest;
        private String rate;
        private String special;
        private String attachment;
        private String moderated;
        private String closed;
        private String stickreply;
        private String recommends;
        private String recommend_add;
        private String recommend_sub;
        private String heats;
        private String status;
        private String isgroup;
        private String favtimes;
        private String sharetimes;
        private String stamp;
        private String icon;
        private String pushedaid;
        private String cover;
        private String replycredit;
        private String relatebytag;
        private String maxposition;
        private String bgcolor;
        private String comments;
        private String hidden;
        private String threadtable;
        private String threadtableid;
        private String posttable;
        private String addviews;
        private String allreplies;
        private String is_archived;
        private String archiveid;
        private String subjectenc;
        private String short_subject;
        private String recommendlevel;
        private String heatlevel;
        private String relay;
        private String ordertype;
        private String recommend;

        public String getTid() {
            return tid;
        }

        public String getFid() {
            return fid;
        }

        public String getPosttableid() {
            return posttableid;
        }

        public String getTypeid() {
            return typeid;
        }

        public String getSortid() {
            return sortid;
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

        public String getHighlight() {
            return highlight;
        }

        public String getDigest() {
            return digest;
        }

        public String getRate() {
            return rate;
        }

        public String getSpecial() {
            return special;
        }

        public String getAttachment() {
            return attachment;
        }

        public String getModerated() {
            return moderated;
        }

        public String getClosed() {
            return closed;
        }

        public String getStickreply() {
            return stickreply;
        }

        public String getRecommends() {
            return recommends;
        }

        public String getRecommend_add() {
            return recommend_add;
        }

        public String getRecommend_sub() {
            return recommend_sub;
        }

        public String getHeats() {
            return heats;
        }

        public String getStatus() {
            return status;
        }

        public String getIsgroup() {
            return isgroup;
        }

        public String getFavtimes() {
            return favtimes;
        }

        public String getSharetimes() {
            return sharetimes;
        }

        public String getStamp() {
            return stamp;
        }

        public String getIcon() {
            return icon;
        }

        public String getPushedaid() {
            return pushedaid;
        }

        public String getCover() {
            return cover;
        }

        public String getReplycredit() {
            return replycredit;
        }

        public String getRelatebytag() {
            return relatebytag;
        }

        public String getMaxposition() {
            return maxposition;
        }

        public String getBgcolor() {
            return bgcolor;
        }

        public String getComments() {
            return comments;
        }

        public String getHidden() {
            return hidden;
        }

        public String getThreadtable() {
            return threadtable;
        }

        public String getThreadtableid() {
            return threadtableid;
        }

        public String getPosttable() {
            return posttable;
        }

        public String getAddviews() {
            return addviews;
        }

        public String getAllreplies() {
            return allreplies;
        }

        public String getIs_archived() {
            return is_archived;
        }

        public String getArchiveid() {
            return archiveid;
        }

        public String getSubjectenc() {
            return subjectenc;
        }

        public String getShort_subject() {
            return short_subject;
        }

        public String getRecommendlevel() {
            return recommendlevel;
        }

        public String getHeatlevel() {
            return heatlevel;
        }

        public String getRelay() {
            return relay;
        }

        public String getOrdertype() {
            return ordertype;
        }

        public String getRecommend() {
            return recommend;
        }
    }
    public static class Post {
        private String pid;
        private String tid;
        private String first;
        private String author;
        private String authorid;
        private String dateline;
        private String message;
        private String anonymous;
        private String attachment;
        private String status;
        private String replycredit;
        private String position;
        private String username;
        private String adminid;
        private String groupid;
        private String memberstatus;
        private String number;
        private String dbdateline;
        private String groupiconid;

        public String getPid() {
            return pid;
        }

        public String getTid() {
            return tid;
        }

        public String getFirst() {
            return first;
        }

        public String getAuthor() {
            return author;
        }

        public String getAuthorid() {
            return authorid;
        }

        public String getDateline() {
            return dateline;
        }

        public String getMessage() {
            return message;
        }

        public String getAnonymous() {
            return anonymous;
        }

        public String getAttachment() {
            return attachment;
        }

        public String getStatus() {
            return status;
        }

        public String getReplycredit() {
            return replycredit;
        }

        public String getPosition() {
            return position;
        }

        public String getUsername() {
            return username;
        }

        public String getAdminid() {
            return adminid;
        }

        public String getGroupid() {
            return groupid;
        }

        public String getMemberstatus() {
            return memberstatus;
        }

        public String getNumber() {
            return number;
        }

        public String getDbdateline() {
            return dbdateline;
        }

        public String getGroupiconid() {
            return groupiconid;
        }
    }
}
