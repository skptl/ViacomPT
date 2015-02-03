package com.viacom.viacompt.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.viacom.viacompt.R;
import com.viacom.viacompt.models.Records;

import java.util.List;

import static com.viacom.viacompt.util.LogUtils.LOGD;
import static com.viacom.viacompt.util.LogUtils.makeLogTag;

/**
 * Created by Shilpan Patel on 2/2/15.
 */
public class VineListAdapter extends ArrayAdapter<Records> {

    private static final String TAG = makeLogTag("VineListAdapter");
    private List<Records> records;
    private Context mContext;

    public VineListAdapter(Context context, List<Records> records) {
        super(context, R.layout.vine_cell, records);
        this.mContext = context;
        this.records = records;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.vine_cell, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.imgView = (ImageView) view.findViewById(R.id.thumbnail);
            viewHolder.title = (TextView) view.findViewById(R.id.title);
            viewHolder.details = (TextView) view.findViewById(R.id.details);
            viewHolder.moreDetails = (TextView) view.findViewById(R.id.more_details);
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
        }

        //LOGD(TAG, position+"");
        Records record = records.get(position);
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        Glide.with(mContext).load(record.getThumbnailUrl()).into(viewHolder.imgView);
        viewHolder.title.setText(record.getUsername());
        viewHolder.details.setText(record.getDescription());
        LOGD(TAG, record.getLikes().toString());
        //String moreDetails = "Likes : " + record.getLikes().getCount() + "\n"
        //                   + "Comments : " + record.getComments().getCount() + "\n"
        //                   + "Revines : " + record.getLoops().getCount() + "\t"
        //                   + "Speed : " + record.getLoops().getVelocity();
        //viewHolder.moreDetails.setText(moreDetails);

        return view;
    }

    private class ViewHolder {
        private ImageView imgView;
        private TextView title;
        private TextView details;
        private TextView moreDetails;

    }
}
