package com.alkemy.ong.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class PaginationUtil<T, ID, R extends JpaRepository<T,ID>> {

    @Autowired
    private R repository;
    protected static final Integer PAGE_SIZE = 10;

    protected static final String PATH_NEWS = "/get-all?page=%d";

    protected Page<T> getPage (Integer page) {
        return repository.findAll(PageRequest.of(page-1, PAGE_SIZE));
    }

    protected String urlGetPrevious (String path, Integer page){
        if (page > 1) return String.format(path, page-1);
        return null;
    }

    protected String urlGetNext (Page<T> pageT, String path, Integer page){
        if (pageT.hasNext()) return String.format(path, page+1);
        return null;
    }




}
