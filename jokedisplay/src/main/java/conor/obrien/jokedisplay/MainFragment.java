package conor.obrien.jokedisplay;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MainFragment extends Fragment {

    MainActivityCallbacks mMainActivityCallbacks;
    TextView mTextViewJoke;

    public MainFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mMainActivityCallbacks = (MainActivityCallbacks) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement MainActivityCallbacks");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_jokedisplay, container, false);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mTextViewJoke = (TextView) getActivity().findViewById(R.id.textView_jokeText);
    }

    @Override
    public void onResume() {
        super.onResume();

        setJokeText(mMainActivityCallbacks.getJokeText());
    }

    private void setJokeText(String jokeText) {
        if (mTextViewJoke != null) {
            mTextViewJoke.setText(jokeText);
        }
    }

    public interface MainActivityCallbacks {
        String getJokeText();
    }
}
