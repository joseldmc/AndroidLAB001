package unosquare.actionbarnavigationdrawerlab;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MyFragment extends Fragment {

    public static String ARGS_COLOR = "ARGS_COLOR";
    public static String ARGS_TITLE = "ARGS_TITLE";
    public static String ARGS_SUBTITLE = "ARGS_SUBTITLE";
    public onFragmentInteraction fragmentInteraction;

    public static MyFragment getInstance(int color,String title,String subtitle){
        MyFragment fragment = new MyFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_COLOR, color);
        bundle.putString(ARGS_TITLE,title);
        bundle.putString(ARGS_SUBTITLE,subtitle);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_layout,container, false);

        int color = getArguments().getInt(ARGS_COLOR);
        String title = getArguments().getString(ARGS_TITLE);
        String subtitle = getArguments().getString(ARGS_SUBTITLE);

        //getActivity().getActionBar().setSubtitle(subtitle);
        fragmentInteraction.setTitle(title,subtitle);
        RelativeLayout layout = (RelativeLayout) rootView.findViewById(R.id.layout);
        layout.setBackgroundColor(getResources().getColor(color));

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            fragmentInteraction = (onFragmentInteraction) activity;
        }catch (ClassCastException e){

        }
    }

    public interface onFragmentInteraction{
        public void setTitle(String title, String subtitle);
    }
}