package Proxy;

import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.Map;

// interface for third party devices
interface ThirdPartyYouTubeLib{
    String listVideos();
    String getVideoInfo(String id);
    void downloadVideo(String id);
}

// The concrete implementation of a service connector
class ThirdPartyYouTubeClass implements ThirdPartyYouTubeLib{

    @Override
    public String listVideos() {
        return "List of videos from YouTube.";
    }

    @Override
    public String getVideoInfo(String id) {
        return "Video info for " + id;
    }

    @Override
    public void downloadVideo(String id) {
        System.out.println("Downloading video " + id);
    }
}

// put the caching code into a new proxy class which implements the same interface as the
// service class. It delegates to the service object only when the real requests have to be sent.
class CachedYouTubeClass implements ThirdPartyYouTubeLib{
    private ThirdPartyYouTubeLib service;
    private String listCache;
    private Map<String, String> videoCache;
    private boolean needReset;

    public CachedYouTubeClass(ThirdPartyYouTubeLib service){
        this.service = service;
        this.videoCache = new HashMap<>();
    }

    @Override
    public String listVideos() {
        if (listCache == null || needReset) {
            listCache = service.listVideos();
            needReset = false;
        }
        return listCache;
    }

    @Override
    public String getVideoInfo(String id) {
        if (videoCache.containsKey(id) || needReset){
            videoCache.put(id,service.getVideoInfo(id));
            needReset = false;
        }
        return videoCache.get(id);
    }

    @Override
    public void downloadVideo(String id) {
        // Check if the download exists in cache (simulation)
        service.downloadVideo(id);
    }
}

// We can safely pass a proxy object instead of a real service object since they both
// implement the same interface.
class YouTubeManger {
    protected ThirdPartyYouTubeLib service;

    public YouTubeManger (ThirdPartyYouTubeLib service){
        this.service = service;
    }

    public void renderVideoPage(String id){
        String info = service.getVideoInfo(id);
        System.out.println("Rendering video page with info: " + info);
    }

    public void renderListPanel(){
        String list = service.listVideos();
        System.out.println("Rendering list of videos: " + list);
    }

    public void reactOnUserInput(){
        renderVideoPage("exampleVideoId");
        renderListPanel();
    }
}

// The application can configure proxies on the fly.
public class Application {
    public static void main(String[] args){
        ThirdPartyYouTubeLib thirdPartyYouTubeLib = new ThirdPartyYouTubeClass();
        ThirdPartyYouTubeLib aYouTubeProxy = new CachedYouTubeClass(thirdPartyYouTubeLib);
        YouTubeManger youTubeManger = new YouTubeManger(aYouTubeProxy);
        youTubeManger.reactOnUserInput();
    }
}
