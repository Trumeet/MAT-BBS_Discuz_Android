package me.letitfly.mat.fragments;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

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
        mForum = getArguments().getParcelable(EXTRA_FORUM);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refresh(null);
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

    public interface RefreshListener {
        void finish();
        void err();
    }

    public synchronized void refresh (@Nullable final RefreshListener refreshListener) {
        Logger.i(TAG, "-> refresh");
        if (mGetListSubscriber != null)
            mGetListSubscriber.unsubscribe();
        setListAdapter(null);
        mAdapter = null;
        ProgressSubscriber.SubscriberOnNextListener<ForumDisplay> listener
                = new ProgressSubscriber.SubscriberOnNextListener<ForumDisplay>() {
            @Override
            public void onNext(ForumDisplay display) {
                ForumDisplay.Thread[] threads = display.getForum_threadlist();
                mThreadList = Arrays.asList(threads);
                mAdapter = new ThreadListAdapter(getActivity(), mThreadList);
                getListView().setAdapter(mAdapter);
                if (refreshListener != null)
                    refreshListener.finish();
            }

            @Override
            public void onError(Throwable e) {
                // TODO: Show error
                if (refreshListener != null)
                    refreshListener.err();
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
