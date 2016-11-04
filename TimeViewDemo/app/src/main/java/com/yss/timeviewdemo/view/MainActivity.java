package com.yss.timeviewdemo.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yss.timeviewdemo.view.GestureLockViewGroup.GestureLockViewGroupListener;
import com.yss.timeviewdemo.R;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}


	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			GestureLockViewGroup mylayout = (GestureLockViewGroup) rootView.findViewById(R.id.mylayout);
			mylayout.setGestureViewGroupListener(new GestureLockViewGroupListener() {
				
				@Override
				public void onBlockSelected(int cid) {
					
				}
				
				@Override
				public void isOutOfTryTime(boolean outOfTryTime) {
					if (outOfTryTime) {
						Toast.makeText(getActivity(), "�����˳��ԵĴ���", Toast.LENGTH_SHORT).show();
					}
				}
				
				@Override
				public void isGestureMatched(boolean matched) {
					Toast.makeText(getActivity(), matched+"", Toast.LENGTH_SHORT).show();
				}
			});
			return rootView;
		}
	}

}
