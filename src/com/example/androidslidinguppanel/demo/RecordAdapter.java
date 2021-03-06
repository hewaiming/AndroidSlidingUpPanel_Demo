package com.example.androidslidinguppanel.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RecordAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<eRecord> mList;
	private int id_row_layout;
	// private ImageLoadingListener animateFirstListener = new
	private   DisplayImageOptions options;
	protected ImageLoader imageLoader;
	public RecordAdapter(Context mContext, int id_row_layout, List<eRecord> mList) {
		this.inflater = LayoutInflater.from(mContext);
		this.mList = mList;
		this.id_row_layout = id_row_layout;
		//options=new ImageLoadOptions();
		/*options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnLoading(R.drawable.ic_launcher).showImageOnFail(R.drawable.ic_launcher)
				.resetViewBeforeLoading(true).cacheOnDisc(true).cacheInMemory(true)
				.imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true)
				.displayer(new FadeInBitmapDisplayer(300)).build();*/
		
		ImageConfig config = new ImageConfig(mContext);
		config.initImageLoader(mContext);		
		options = new ImageLoadOptions().getOptions();
		imageLoader = ImageLoader.getInstance();  
	}

	@Override
	public int getCount() {

		return mList.size();
	}

	@Override
	public Object getItem(int position) {

		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		eRecord entity = mList.get(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(id_row_layout, null);
			holder.name_tv = (TextView) convertView.findViewById(R.id.tv_name);
			holder.address_tv = (TextView) convertView.findViewById(R.id.tv_address);
			holder.content_tv = (TextView) convertView.findViewById(R.id.tv_content);
			holder.date_tv = (TextView) convertView.findViewById(R.id.tv_date);
			holder.unread_tv = (TextView) convertView.findViewById(R.id.tv_unread);
			holder.time_tv = (TextView) convertView.findViewById(R.id.tv_time);
			holder.image_iv = (ImageView) convertView.findViewById(R.id.iv_photo);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		TextPaint tPaint = holder.name_tv.getPaint();
		tPaint.setFakeBoldText(true);
		holder.name_tv.setText(entity.getName());
		holder.address_tv.setText(entity.getAddress());
		holder.content_tv.setText(entity.getContent());
		holder.date_tv.setText(entity.getRecordDate());
		holder.time_tv.setText(entity.getRecordTime());
		holder.unread_tv.setVisibility(View.GONE);
		holder.image_iv.setImageResource(R.drawable.ic_launcher);
		// ImageLoader.getInstance().displayImage(mList.get(position).getImage(),holder.image_iv );		
		// ���ͼƬ
		String mImagePath = entity.getImage();
		if (mImagePath!= null && !mImagePath.equals("")) {
			//ImageLoader.getInstance().displayImage(mImagePath, holder.image_iv, options);
			imageLoader.displayImage(mImagePath, holder.image_iv, options);
		} else {
			holder.image_iv.setImageResource(R.drawable.no_image);
		}
		return convertView;
	}

	class ViewHolder {
		TextView name_tv;
		ImageView image_iv;
		TextView address_tv;
		TextView content_tv;
		TextView date_tv;
		TextView time_tv;
		TextView unread_tv;
	}

	public void onDateChange(List<eRecord> mList) {
		this.mList = mList;
		this.notifyDataSetChanged();

	}

	/*
	 * private static class AnimateFirstDisplayListener extends
	 * SimpleImageLoadingListener {
	 * 
	 * static final List<String> displayedImages =
	 * Collections.synchronizedList(new LinkedList<String>());
	 * 
	 * @Override public void onLoadingComplete(String imageUri, View view,
	 * Bitmap loadedImage) { if (loadedImage != null) { ImageView imageView =
	 * (ImageView) view; boolean firstDisplay =
	 * !displayedImages.contains(imageUri); if (firstDisplay) {
	 * FadeInBitmapDisplayer.animate(imageView, 500);
	 * displayedImages.add(imageUri); } } } }
	 */
}
