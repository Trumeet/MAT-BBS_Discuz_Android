package me.letitfly.mat.utils;

import android.view.MenuItem;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.commons.MenuSheetView;

import java.util.List;

import me.letitfly.mat.R;
import me.letitfly.mat.model.ForumNav;

/**
 * Created by Trumeet on 2017/3/4.
 * Create a bottom sheet to switch forums.
 * @author Trumeet
 */

public class ForumSwitchUtils {
    public static final int ID_ALL = -1;

    public interface OnTagSelectedListener {
        void onSelected (ForumNav.Forum forum);
        void onSpecialSelected (int id);
    }

    public static void pickTags (final BottomSheetLayout layout
            , final OnTagSelectedListener listener
            , final List<ForumNav.Forum> forumList) {
        MenuSheetView view = new MenuSheetView(layout.getContext()
                , MenuSheetView.MenuType.GRID
                , R.string.title_forums
                , new MenuSheetView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == ID_ALL) {
                    listener.onSpecialSelected(item.getItemId());
                    layout.dismissSheet();
                    return true;
                }
                listener.onSelected(forumList.get(item.getItemId()));
                layout.dismissSheet();
                return true;
            }
        });
        // DZ BBS Not support "All" tag.
        /*
        view.getMenu().add(0, ID_ALL, 0, R.string.filter_all)
                .setIcon(R.drawable.ic_apps_black_24dp);
                */
        for (int i = 0; i < forumList.size(); i ++) {
            ForumNav.Forum forum = forumList.get(i);
            // TODO: Icon load
            view.getMenu().add(0, i, 0, forum.getName());
        }
        view.updateMenu();
        layout.showWithSheetView(view);
    }
}
