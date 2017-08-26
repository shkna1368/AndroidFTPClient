package com.sanandaj.androidftp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.sanandaj.androidftp.R;
import com.sanandaj.androidftp.models.FtpFile;
import com.sanandaj.androidftp.viewholders.FileViewHolder;

import java.util.List;

/**
 * Created by Sh-Java on 12/10/2016.
 */
public class FileAdapter extends RecyclerView.Adapter<FileViewHolder>  {
    private List<FtpFile> ftpFileList;

    Context ctx;
  int  malekId;
    FileTap fileTap;
   // private EventBus bus = EventBus.getDefault();

    @Override
    public FileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.file_row, parent, false);
 ctx=view.getContext();


        FileViewHolder myViewHolder = new FileViewHolder(view);
        return myViewHolder;
    }


    private String getType(String fileName){
        int lastDot = fileName.lastIndexOf('.');
        String extension =   fileName.substring(lastDot+1);

      return extension;

    }

    @Override
    public void onBindViewHolder(FileViewHolder holder, int position) {
        TextView textName = holder.textFileName;

        ImageView imgType = holder.imageType;


  final FtpFile selectedFtpFile=ftpFileList.get(position);
        String fileName=selectedFtpFile.getName();
        textName.setText(fileName);
imgType.setImageResource(selectedFtpFile.getImageresource());
        Log.e("file name==",fileName);
/*
String format =getType(fileName);



        if (format.equals("mp3")||format.equals("MP3")){
            imgType.setImageResource(R.drawable.mpthree);
        }

        else if (format.equals("png")||format.equals("PNG")||format.equals("jpg")||format.equals("jpeg")){
            imgType.setImageResource(R.drawable.pic);
        }*/



        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //     bus.post(malekTo);
fileTap.onItemTap(selectedFtpFile);

           /*     Intent i=new Intent(ctx,AddMalekActivity.class);
                i.putExtra("melkId",selectedMalek.getMelkId());
                ctx.startActivity(i);*/

            }
        });



  holder.getItemView().setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View view) {
          fileTap.onLongItemClick(selectedFtpFile);
          return true;
      }
  });


    }

    public FileAdapter(List<FtpFile> ftpFiles, FileTap fileTap) {
        this.ftpFileList = ftpFiles;

        this.fileTap=fileTap;
    }

    @Override
    public int getItemCount() {
        return ftpFileList.size();
    }



}
