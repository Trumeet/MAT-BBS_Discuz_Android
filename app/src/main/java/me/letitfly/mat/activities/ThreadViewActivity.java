package me.letitfly.mat.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.letitfly.mat.R;
import me.letitfly.mat.api.APIManager;
import me.letitfly.mat.model.ForumDisplay;
import me.letitfly.mat.model.ThreadView;
import me.letitfly.mat.utils.FormatUtils;
import me.letitfly.mat.utils.Logger;
import me.letitfly.mat.utils.ProgressSubscriber;

/**
 * Created by Trumeet on 2017/3/15.
 * A activity to view thread replies.
 * @author Trumeet
 */

public class ThreadViewActivity extends AppCompatActivity {
    private static final String TAG = ThreadViewActivity.class.getSimpleName();
    public static final String EXTRA_TID = ThreadViewActivity.class.getSimpleName()
             + ".EXTRA_TID";
    @BindView(R.id.messagesList)
    MessagesList mMessagesList;
    private MessagesListAdapter<IMessage> mAdapter;
    private int mTid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        try {
            mTid = Integer.parseInt(intent.getStringExtra(EXTRA_TID));
        } catch (Exception e) {
            finish();
            return;
        }
        setContentView(R.layout.activity_thread_view);
        ButterKnife.bind(this);
        mAdapter = new MessagesListAdapter<>("0", new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                Glide.with(ThreadViewActivity.this)
                        .load(url)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(imageView);
            }
        });

        loadThread(0);
    }

    private void loadThread (int page) {
        ProgressSubscriber<ThreadView> subscriber = new ProgressSubscriber<>(
                new ProgressSubscriber.SubscriberOnNextListener<ThreadView>() {
                    @Override
                    public void onNext(ThreadView threadView) {
                        convertThreadViewToIMessage(threadView);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO: Show error
                    }
                }
        );
        APIManager.getThreadView(mTid, page, subscriber);
    }

    private void convertThreadViewToIMessage (ThreadView threadView) {
        ArrayList<IMessage> list = new ArrayList<>();
        for (final ThreadView.Post post : threadView.getPostlist()) {
            list.add(new IMessage() {
                @Override
                public String getId() {
                    return post.getPid();
                }

                @Override
                public String getText() {
                    return post.getMessage();
                }

                @Override
                public IUser getUser() {
                    return new IUser() {
                        @Override
                        public String getId() {
                            return post.getAuthorid();
                        }

                        @Override
                        public String getName() {
                            return post.getAuthor();
                        }

                        @Override
                        public String getAvatar() {
                            return null;
                        }
                    };
                }

                @Override
                public Date getCreatedAt() {
                    return FormatUtils.convertDateLine(post.getDateline());
                }
            });
        }
        mAdapter.addToEnd(list, true);
        mMessagesList.setAdapter(mAdapter);
        setTitle(threadView.getThread().getSubject());
    }
}
