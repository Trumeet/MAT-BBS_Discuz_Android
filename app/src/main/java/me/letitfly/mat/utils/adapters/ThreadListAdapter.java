package me.letitfly.mat.utils.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import me.letitfly.mat.R;
import me.letitfly.mat.model.ForumDisplay;

/**
 * Created by Trumeet on 2017/3/2.
 * Thread list array adapter.
 * @see me.letitfly.mat.fragments.PostListFragment
 * @see android.widget.ArrayAdapter
 * @see me.letitfly.mat.model.ForumDisplay
 * @author Trumeet
 */

public class ThreadListAdapter extends ArrayAdapter {
    private Context mContext;
    private List<ForumDisplay.Thread> mThreadList;

    public ThreadListAdapter (Context context, List<ForumDisplay.Thread> list) {
        super(context, 0, list);
        mContext = context;
        mThreadList = list;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_discussion, parent, false);
        }
        ForumDisplay.Thread discussion = mThreadList.get(position);

        TextView title = (TextView) convertView.findViewById(android.R.id.text1);
        title.setText(discussion.getSubject());

        //CircleImageView avatar = (CircleImageView) convertView.findViewById(R.id.avatar);
        //NetworkUtil.loadImage(discussion, avatar);

        TextView context = (TextView) convertView.findViewById(android.R.id.text2);
        context.setText(mContext.getString(R.string.text_post_summary
                , discussion.getLastposter(), discussion.getLastpost()));

        return convertView;
    }
}
