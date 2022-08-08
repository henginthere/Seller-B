package backend.sellerB.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpenviduSessionDto {

    public class Connections{
        public int numberOfElements;
        public ArrayList<Object> content;
    }

    public class DefaultRecordingProperties{
        public String name;
        public boolean hasAudio;
        public boolean hasVideo;
        public String outputMode;
        public String recordingLayout;
        public String resolution;
        public int frameRate;
        public int shmSize;
        public String mediaNode;
    }

    public String id;
    public String object;
    public String sessionId;
    public long createdAt;
    public boolean recording;
    public String mediaMode;
    public String recordingMode;
    public DefaultRecordingProperties defaultRecordingProperties;
    public String customSessionId;
    public String forcedVideoCodec;
    public String forcedVideoCodecResolved;
    public boolean allowTranscoding;
    public Connections connections;




}
