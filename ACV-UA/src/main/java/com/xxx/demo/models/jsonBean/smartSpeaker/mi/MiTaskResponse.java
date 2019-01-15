package com.xxx.demo.models.jsonBean.smartSpeaker.mi;



import com.xxx.demo.models.jsonBean.smartSpeaker.TaskResponse;

import java.io.Serializable;

public class MiTaskResponse extends TaskResponse implements Serializable  {

    public MiTaskResponse(boolean open_mic, boolean is_session_end, String speaker) {
        super(open_mic, is_session_end, speaker);
    }
}
