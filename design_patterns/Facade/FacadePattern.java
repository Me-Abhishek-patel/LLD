package design_patterns.Facade;

public class FacadePattern {

    // subsystems

    public static class MPEGCompression {
        public String compress(String bit){
            return bit;
        }
        
    }
    public static class Bitreader {
        public String read(String video){
            return video;
        }
        
    }
    //Facade
    public static class VideoCompressor{
        public String convert(String video){
            Bitreader bitreader = new Bitreader();
            
            MPEGCompression compresser = new MPEGCompression();

            return compresser.compress(bitreader.read(video));
        }
    };

    public static void main(String[] args) {
        VideoCompressor videoCompressor = new VideoCompressor();
        videoCompressor.convert("VIdep");
    }


    
}
