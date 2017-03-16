package me.letitfly.mat.fragments;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.Arrays;
import java.util.List;

import me.letitfly.mat.activities.ThreadViewActivity;
import me.letitfly.mat.api.APIManager;
import me.letitfly.mat.model.ForumDisplay;
import me.letitfly.mat.model.ForumNav;
import me.letitfly.mat.utils.Logger;
import me.letitfly.mat.utils.ProgressSubscriber;
import me.letitfly.mat.utils.adapters.ThreadListAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Trumeet on 2017/3/2.
 * @author Trumeet
 */

public class PostListFragment extends ListFragment {
    private static final String TAG = "PostListFragment";
    private static final String EXTRA_FORUM =
            PostListFragment.class.getSimpleName()
            + ".EXTRA_FORUM";

    private List<ForumDisplay.Thread> mThreadList;
    private ThreadListAdapter mAdapter;
    private Subscriber<ForumDisplay> mGetListSubscriber;

    private ForumNav.Forum mForum;

    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Logger.i(TAG, "onCreate");
        mForum = getArguments().getParcelable(EXTRA_FORUM);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refresh();
    }

    @Override
    public void onDestroy() {
        if (mGetListSubscriber != null)
            mGetListSubscriber.unsubscribe();
        super.onDestroy();
    }

    public static PostListFragment newInstance (ForumNav.Forum forum) {
        PostListFragment fragment = new PostListFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_FORUM, forum);
        fragment.setArguments(args);
        return fragment;
    }

    public synchronized void refresh () {
        Logger.i(TAG, "-> refresh:" + mForum);
        if (mGetListSubscriber != null)
            mGetListSubscriber.unsubscribe();
        setListAdapter(null);
        mAdapter = null;
        ProgressSubscriber.SubscriberOnNextListener<ForumDisplay> listener
                = new ProgressSubscriber.SubscriberOnNextListener<ForumDisplay>() {
            @Override
            public void onNext(final ForumDisplay display) {
                ForumDisplay.Thread[] threads = display.getForum_threadlist();
                mThreadList = Arrays.asList(threads);
                mAdapter = new ThreadListAdapter(getActivity(), mThreadList);
                getListView().setAdapter(mAdapter);
                getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        startActivity(new Intent(getActivity(), ThreadViewActivity.class)
                        .putExtra(ThreadViewActivity.EXTRA_TID, mThreadList.get(i).getTid()));
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                // TODO: Show error
            }
        };
        mGetListSubscriber = new ProgressSubscriber<>(listener);
        int fid = Integer.parseInt(mForum.getFid());
        Logger.i(TAG, "Refresh -> fid:" + fid);
        APIManager.getForumDisplay(
                fid
                , 0, mGetListSubscriber);
    }
}
