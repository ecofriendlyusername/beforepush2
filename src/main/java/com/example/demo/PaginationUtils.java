package com.example.demo;

import org.springframework.data.domain.Slice;
import org.springframework.http.HttpHeaders;

public class PaginationUtils {
    public static HttpHeaders generateSliceHttpHeaders(Slice<?> slice) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Has-Next-Page", "" + slice.hasNext());
        headers.add("X-Has-Previous-Page", "" + slice.hasPrevious());
        return headers;
    }
}
