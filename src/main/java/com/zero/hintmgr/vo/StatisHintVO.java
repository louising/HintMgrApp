package com.zero.hintmgr.vo;

public class StatisHintVO {
    private String date;
    private Long askHintCount;
    private Long traceHintCount;

    public StatisHintVO(String date, Long askHintCount, Long traceHintCount) {
        super();
        this.date = date;
        this.askHintCount = askHintCount;
        this.traceHintCount = traceHintCount;
    }

    @Override
    public String toString() {
        return "StatisHintVO [date=" + date + ", askHintCount=" + askHintCount + ", traceHintCount=" + traceHintCount + "]";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getAskHintCount() {
        return askHintCount;
    }

    public void setAskHintCount(Long askHintCount) {
        this.askHintCount = askHintCount;
    }

    public Long getTraceHintCount() {
        return traceHintCount;
    }

    public void setTraceHintCount(Long traceHintCount) {
        this.traceHintCount = traceHintCount;
    }

}
