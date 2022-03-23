package com.zero.hintmgr.vo;

public class StatisUserVO {
    private String date;
    private Long totalUserCount;
    private Long totalSaleCount;
    private Long totalSpreadCount;

    public StatisUserVO(String date, Long totalUserCount, Long totalSaleCount, Long totalSpreadCount) {
        super();
        this.date = date;
        this.totalUserCount = totalUserCount;
        this.totalSaleCount = totalSaleCount;
        this.totalSpreadCount = totalSpreadCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getTotalUserCount() {
        return totalUserCount;
    }

    public void setTotalUserCount(Long totalUserCount) {
        this.totalUserCount = totalUserCount;
    }

    public Long getTotalSaleCount() {
        return totalSaleCount;
    }

    public void setTotalSaleCount(Long totalSaleCount) {
        this.totalSaleCount = totalSaleCount;
    }

    public Long getTotalSpreadCount() {
        return totalSpreadCount;
    }

    public void setTotalSpreadCount(Long totalSpreadCount) {
        this.totalSpreadCount = totalSpreadCount;
    }

    @Override
    public String toString() {
        return "StatisUserVO [date=" + date + ", totalUserCount=" + totalUserCount + ", totalSaleCount=" + totalSaleCount + ", totalSpreadCount="
                + totalSpreadCount + "]";
    }

}
