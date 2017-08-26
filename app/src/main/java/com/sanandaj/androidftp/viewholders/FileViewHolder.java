package com.sanandaj.androidftp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanandaj.androidftp.R;


/**
 * Created by Sh-Java on 12/10/2016.
 */
public class FileViewHolder extends RecyclerView.ViewHolder {

 public    TextView textFileName;
public    ImageView imageType;
    View itemView;

    public View getItemView() {
        return itemView;
    }

    public void setItemView(View itemView) {
        this.itemView = itemView;
    }

    public FileViewHolder(View itemView) {
        super(itemView);
        this.itemView=itemView;
        this.textFileName = (TextView) itemView.findViewById(R.id.textView);
        this.imageType = (ImageView) itemView.findViewById(R.id.imageView);





    }
}
