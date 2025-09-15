package design_patterns.Bridge;

public class BridgePattern {
    public interface Device {
        public boolean isEnabled();
        public void enable();
        public void disable();
        public int getVolume();
        public void setVolume(int percent);
    }

    public class TV implements Device {
        private boolean on = false;
        private int volume = 30;

        @Override
        public boolean isEnabled() {
            return on;
        }

        @Override
        public void enable() {
            on=true;
        }

        @Override
        public void disable() {
            on=false;
        }

        @Override
        public int getVolume() {
            return volume;
        }

        @Override
        public void setVolume(int percent) {
            volume = percent;
        }
    
        
    }

    public class  Remote {
        protected Device device;

        public Remote(Device device){
            this.device = device;
        }

        public void onPowerBtnPress(){
            
        }
        
    }

}
