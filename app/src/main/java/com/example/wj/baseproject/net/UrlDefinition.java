package com.example.wj.baseproject.net;

import com.example.wj.baseproject.BuildConfig;

/**
 * 服务器接口定义类
 */
public interface UrlDefinition {

    /** 正式环境 */
    String API_RELEASE = "www.baidu.com";
    /** 测试环境 */
    String API_DEBUG = "www.baidu.com";
    /** 服务器url */
    String API_DOMAIN = (BuildConfig.DEBUG ? API_DEBUG : API_RELEASE);

    /** 标记、是否使用https */
    boolean USE_SSL = false;
    /** Scheme */
    String SCHEME = USE_SSL ? "https://" : "http://";
    /** 请求跟路径 */
    String BASE_URL = SCHEME + API_DOMAIN;

    String API_KEY = BuildConfig.API_KEY; // add your API key here
    String GET_POPULAR_MOVIES = "http://api.themoviedb.org/3/discover/movie?language=zh&sort_by=popularity.desc&api_key=" + API_KEY;
    String GET_HIGHEST_RATED_MOVIES = "http://api.themoviedb.org/3/discover/movie?vote_count.gte=500&language=zh&sort_by=vote_average.desc&api_key=" + API_KEY;
    String GET_TRAILERS = "http://api.themoviedb.org/3/movie/%s/videos?api_key=" + API_KEY;
    String GET_REVIEWS = "http://api.themoviedb.org/3/movie/%s/reviews?api_key=" + API_KEY;
    String POSTER_PATH = "http://image.tmdb.org/t/p/w342";
    String BACKDROP_PATH = "http://image.tmdb.org/t/p/w780";
    String YOUTUBE_VIDEO_URL = "http://www.youtube.com/watch?v=%1$s";
    String YOUTUBE_THUMBNAIL_URL = "http://img.youtube.com/vi/%1$s/0.jpg";
}
