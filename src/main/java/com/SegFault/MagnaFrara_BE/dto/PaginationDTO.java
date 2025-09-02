package com.SegFault.MagnaFrara_BE.dto;

public class PaginationDTO {
    private int current_page;
    private int per_page;
    private long total;
    private int total_pages;
    private boolean has_next;
    private boolean has_prev;

    public PaginationDTO(int currentPage, int perPage, long total) {
        this.current_page = currentPage;
        this.per_page = perPage;
        this.total = total;
        this.total_pages = (int) Math.ceil((double) total / perPage);
        this.has_next = currentPage < total_pages;
        this.has_prev = currentPage > 1;
    }

    // Getters
    public int getCurrent_page() { return current_page; }
    public int getPer_page() { return per_page; }
    public long getTotal() { return total; }
    public int getTotal_pages() { return total_pages; }
    public boolean isHas_next() { return has_next; }
    public boolean isHas_prev() { return has_prev; }
}
