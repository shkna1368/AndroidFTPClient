package com.sanandaj.androidftp.adapters;

import com.sanandaj.androidftp.models.FtpFile;

/**
 * Created by a on 8/24/2017.
 */

public interface FileTap {

    public void onItemTap(FtpFile ftpFile);
    public void onLongItemClick(FtpFile ftpFile);
}
