package me.letitfly.mat;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.flipboard.bottomsheet.BottomSheetLayout;

import me.letitfly.mat.utils.ForumSwitchUtils;
import me.letitfly.mat.utils.ProgressSubscriber;
import rx.Subscriber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.letitfly.mat.api.APIManager;
import me.letitfly.mat.fragments.PostListFragment;
import me.letitfly.mat.model.ForumNav;
import me.letitfly.mat.utils.Logger;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.toolbar_title)
    TextView mToolBarTitle;
    @BindView(R.id.swipe)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private Subscriber<ForumNav> mRefreshForumsConsumer;

    // Forum fragment list
    private ArrayList<ForumNav.Forum> mForums;
    private List<PostListFragment> mPostListFragments;
    private int mCurrentForumIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mCurrentForumIndex == -1) {
                    refreshForums();
                } else {
                    mRefreshLayout.setRefreshing(false);
                    mRefreshLayout.setEnabled(false);
                }
            }
        });
        refreshForums();
    }

    private void refreshForums () {
        if (mRefreshForumsConsumer != null) {
            mRefreshForumsConsumer.unsubscribe();
        }
        mRefreshLayout.setRefreshing(true);
        ProgressSubscriber.SubscriberOnNextListener<ForumNav> listener
                = new ProgressSubscriber.SubscriberOnNextListener<ForumNav>() {
            @Override
            public void onNext(ForumNav forumNav) {
                Logger.i(TAG, "onNext:" + forumNav.toString());
                List<ForumNav.Forum> forumList = Arrays.asList(forumNav.getForums());
                if (mForums != null)
                    mForums.clear();
                else
                mForums = new ArrayList<>();
                for (ForumNav.Forum forum : forumList) {
                    if ("forum".equals(forum.getType())) {
                        mForums.add(forum);
                    }
                }
                if (mPostListFragments != null)
                    mPostListFragments.clear();
                mPostListFragments = new ArrayList<>();
                for (ForumNav.Forum forum : mForums) {
                    mPostListFragments.add(PostListFragment.newInstance(forum));
                }

                mToolBarTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mRefreshLayout.isRefreshing()) {
                            Logger.e(TAG, "Refreshing, not show sheet");
                            return;
                        }
                        Logger.i(TAG, "Showing bottom sheet");
                        BottomSheetLayout bottomSheet = (BottomSheetLayout)findViewById(R.id.bottomsheet);
                        ForumSwitchUtils.pickTags(bottomSheet, new ForumSwitchUtils.OnTagSelectedListener() {
                            @Override
                            public void onSelected(ForumNav.Forum forum) {
                                switchForum(mForums.indexOf(forum));
                            }

                            @Override
                            public void onSpecialSelected(int id) {
                                // DZ BBS Not support "All" tag.
                            }
                        }, mForums);
                    }
                });
                switchForum(0);
                mRefreshLayout.setRefreshing(false);
                mRefreshLayout.setEnabled(false);
            }

            @Override
            public void onError(Throwable e) {
                mRefreshLayout.setRefreshing(false);
                // TODO: Show error
            }
        };
        mRefreshForumsConsumer = new ProgressSubscriber<>(listener);
        APIManager.getForums(0, mRefreshForumsConsumer);
    }

    private void switchForum (int index) {
        Logger.i(TAG, "switchForum:" + index);
        Logger.i(TAG, "forum:" + mForums.size() + ",fragments:" + mPostListFragments.size());
        mCurrentForumIndex = index;
        ForumNav.Forum forum = mForums.get(index);
        Logger.i(TAG, forum.toString());
        mToolBarTitle.setText(forum.getName());
        getFragmentManager().beginTransaction()
                .replace(R.id.frame, mPostListFragments.get(index))
                .commitAllowingStateLoss();
        mRefreshLayout.setEnabled(true);
    }
}
