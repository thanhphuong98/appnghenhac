package com.example.chesminnguyen.app_edm_music.Service;

@SuppressWarnings("deprecation")
public class APIService {
    private static String base_url="https://thanhphuongnguyen.000webhostapp.com/Server/PackPinkMusicServer/";
    public static DataService getService()
    {
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
