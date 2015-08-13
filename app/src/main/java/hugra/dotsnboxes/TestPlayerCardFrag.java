package hugra.dotsnboxes;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TestPlayerCardFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TestPlayerCardFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestPlayerCardFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "username";
    private static final String ARG_PARAM2 = "gamesWon";

    // TODO: Rename and change types of parameters
    private String username;
    private int gamesWon;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param username Parameter 1.
     * @param gamesWon Parameter 2.
     * @return A new instance of fragment TestPlayerCardFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static TestPlayerCardFrag newInstance(String username, int gamesWon) {
        TestPlayerCardFrag fragment = new TestPlayerCardFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, username);
        args.putInt(ARG_PARAM2, gamesWon);
        fragment.setArguments(args);
        return fragment;
    }

    public TestPlayerCardFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString(ARG_PARAM1);
            gamesWon = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View viewToReturn = inflater.inflate(R.layout.fragment_test_player_card, container, false);
        ((TextView) viewToReturn.findViewById(R.id.PlayerCardFrag_UserNameTextView)).setText(username);
//        ((TextView) viewToReturn.findViewById(R.id.PlayerCardFrag_GameCountTextView)).setText(gamesWon);
        return viewToReturn;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
         void onFragmentInteraction(Uri uri);
    }

}
