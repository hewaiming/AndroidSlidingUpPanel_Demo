package com.example.androidslidinguppanel.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class NextActivity extends ActionBarActivity {
	private SlidingUpPanelLayout mLayout;
	private static final String TAG = "NextActivity";
	private Button hidebtn;
	private ListView lv;
	private ArrayList<eRecord> listBean=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_next);
		setSupportActionBar((Toolbar) findViewById(R.id.main_toolbar));
       // init_listview();   
        init_listviewImage();
		mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
		mLayout.addPanelSlideListener(new PanelSlideListener() {
			@Override
			public void onPanelSlide(View panel, float slideOffset) {
				Log.i(TAG, "onPanelSlide, offset " + slideOffset);
			}

			@Override
			public void onPanelStateChanged(View panel, PanelState previousState, PanelState newState) {
				Log.i(TAG, "onPanelStateChanged " + newState);
			}
		});
		mLayout.setFadeOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				mLayout.setPanelState(PanelState.COLLAPSED);
			}
		});

		hidebtn = (Button) findViewById(R.id.follow);
		hidebtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (mLayout != null) {
					if (mLayout.getPanelState() != PanelState.HIDDEN) {
						mLayout.setPanelState(PanelState.HIDDEN);

					} else {
						mLayout.setPanelState(PanelState.COLLAPSED);

					}
				}
			}
		});

	}

	private void init_listviewImage() {
		lv=(ListView) findViewById(R.id.list);
		listBean = new ArrayList<eRecord>();	
		String imgAddress="http://125.64.59.11:8000/scgy/android/BANNER/";
		for(int i=0;i<20;i++){
			eRecord mRecord=new eRecord();		
			mRecord.setAddress("address"+i);
			mRecord.setContent("content:"+i);
			mRecord.setName("name:"+i);
			mRecord.setRecordDate("recordDate:"+i);
			mRecord.setImage(imgAddress+i+".JPG");
			listBean.add(mRecord);
		}
		
		RecordAdapter mAdapter = new RecordAdapter(this, R.layout.item_image_records, listBean);

		lv.setAdapter(mAdapter);
		
	}

	private void init_listview() {
		 lv = (ListView) findViewById(R.id.list);
	        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	            @Override
	            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	                Toast.makeText(NextActivity.this, "onItemClick", Toast.LENGTH_SHORT).show();
	            }
	        });

	        List<String> your_array_list = Arrays.asList(
	                "This",
	                "Is",
	                "An",
	                "Example",
	                "ListView",
	                "That",
	                "You",
	                "Can",
	                "Scroll",
	                ".",
	                "It",
	                "Shows",
	                "How",
	                "Any",
	                "Scrollable",
	                "View",
	                "Can",
	                "Be",
	                "Included",
	                "As",
	                "A",
	                "Child",
	                "Of",
	                "SlidingUpPanelLayout"
	        );

	        // This is the array adapter, it takes the context of the activity as a
	        // first parameter, the type of list view as a second parameter and your
	        // array as a third parameter.
	        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
	                this,
	                android.R.layout.simple_list_item_1,
	                your_array_list);

	        lv.setAdapter(arrayAdapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.next, menu);
		MenuItem item = menu.findItem(R.id.action_show);
		if (mLayout != null) {
			if (mLayout.getPanelState() == PanelState.HIDDEN) {
				item.setTitle(R.string.action_show);
			} else {
				item.setTitle(R.string.action_hide);
			}
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_show) {
			if (mLayout != null) {
				if (mLayout.getPanelState() != PanelState.HIDDEN) {
					mLayout.setPanelState(PanelState.HIDDEN);
					item.setTitle(R.string.action_show);
				} else {
					mLayout.setPanelState(PanelState.COLLAPSED);
					item.setTitle(R.string.action_hide);
				}
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
