package com.open.mcp.server.service;

import com.open.mcp.server.dto.JinaReadAndFetchContentResponse;
import com.open.mcp.server.dto.JinaSearchAndGetSERPResponse;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @ClassDescription:
 * @Author: song.chang
 * @Created: 2025/3/12 16:38
 */
public interface IJinaReaderApi {

    /**
     * e.g. curl <a href="https://r.jina.ai/https://example.com">...</a> \
     * -H "Accept: application/json" \
     * -H "Authorization: Bearer jina_7c2096090ce14586ab4193e376588eeat9lPKEeomlMin_lONzpwnPsUHf7d"
     *
     * @param url   url
     * @return result
     */
    @Headers({
            "Accept: application/json",
    })
    @GET("https://r.jina.ai/{url}")
    Call<JinaReadAndFetchContentResponse> readAndFetchContent(@Path("url") String url);

    /**
     * e.g. curl <a href="https://s.jina.ai/?q=Jina+AI&num=10&page=1">...</a> \
     * -H "Accept: application/json" \
     * -H "Authorization: Bearer jina_7c2096090ce14586ab4193e376588eeat9lPKEeomlMin_lONzpwnPsUHf7d" \
     * -H "X-Respond-With: no-content"
     *
     * @param question q
     * @param num      num
     * @param page     page
     * @return
     */
    @Headers({
            "Accept: application/json",
            "X-Respond-With: no-content"
    })
    @GET("https://s.jina.ai")
    Call<JinaSearchAndGetSERPResponse> searchAndGetSERP(@Query("q") String question,
                                                       @Query("num") Integer num,
                                                       @Query("page") Integer page);

}
