package backend.sellerB.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
public class RequestOpenviduBody {

    public class DefaultRecordingProperties{
        @Builder.Default
        public String name = "MyRecording";
        @Builder.Default
        public boolean hasAudio = true;
        @Builder.Default
        public boolean hasVideo = true;
        @Builder.Default
        public String outputMode = "COMPOSED";
        @Builder.Default
        public String recordingLayout = "BEST_FIT";
        @Builder.Default
        public String resolution = "1280x720";
        @Builder.Default
        public int frameRate = 25;
        @Builder.Default
        public int shmSize = 536870912;
        public MediaNode mediaNode;
    }



    public class MediaNode{
        @Builder.Default
        public String id = "media_i-0c58bcdd26l11d0sd";
    }

    @Builder.Default
    public String mediaMode = "ROUTED";
    @Builder.Default
    public String recordingMode = "MANUAL";
    @Builder.Default
    public String customSessionId = "defaultSessionId";
    @Builder.Default
    public String forcedVideoCodec = "VP8";
    @Builder.Default
    public boolean allowTranscoding = false;
    public DefaultRecordingProperties defaultRecordingProperties;
    public MediaNode mediaNode;


}
