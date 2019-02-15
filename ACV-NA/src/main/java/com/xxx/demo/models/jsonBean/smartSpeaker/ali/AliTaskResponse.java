package com.xxx.demo.models.jsonBean.smartSpeaker.ali;


import com.xxx.demo.Enum.ExecuteCode;
import com.xxx.demo.models.jsonBean.smartSpeaker.TaskResponse;

import java.io.Serializable;

public class AliTaskResponse extends TaskResponse implements Serializable {

    public AliTaskResponse(String returnCode, String reply, ExecuteCode executeCode) {
        super(returnCode, reply, executeCode);
    }
}
