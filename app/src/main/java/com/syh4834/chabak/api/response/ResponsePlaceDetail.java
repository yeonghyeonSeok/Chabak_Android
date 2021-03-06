package com.syh4834.chabak.api.response;

import com.syh4834.chabak.api.data.PlaceDetailData;
import com.syh4834.chabak.api.data.SigninData;

public class ResponsePlaceDetail {
    private final int status;
    private final boolean success;
    private final String message;
    private final PlaceDetailData data;

    public ResponsePlaceDetail(int status, boolean success, String message, PlaceDetailData data) {
        this.status = status;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public int getStatus() { return status; }

    public boolean getSuccess() { return success; }

    public String getMessage() { return message; }

    public PlaceDetailData getData() { return data; }

}
