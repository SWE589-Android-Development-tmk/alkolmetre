
package com.example.mk0730.alkolmetre.lcbo;

import java.util.List;
import com.squareup.moshi.Json;

public class LcboApiResponse {
    @Json(name = "status")
    private Integer status;
    @Json(name = "message")
    private Object message;
    @Json(name = "pager")
    private LcboApiResponsePager pager;
    @Json(name = "result")
    private List<LcboApiResponseResult> result = null;
    @Json(name = "suggestion")
    private Object suggestion;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public LcboApiResponsePager getPager() {
        return pager;
    }

    public void setPager(LcboApiResponsePager pager) {
        this.pager = pager;
    }

    public List<LcboApiResponseResult> getResult() {
        return result;
    }

    public void setResult(List<LcboApiResponseResult> result) {
        this.result = result;
    }

    public Object getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(Object suggestion) {
        this.suggestion = suggestion;
    }
}
