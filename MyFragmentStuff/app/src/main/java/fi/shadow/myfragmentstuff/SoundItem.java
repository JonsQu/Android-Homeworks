package fi.shadow.myfragmentstuff;

public class SoundItem {
    private int soundID;
    private String name;

    public SoundItem(int soundID, String name) {
        this.soundID = soundID;
        this.name = name;
    }

    @Override
    public String toString() {
        return "SoundItem{" +
                "name='" + name + '\'' +
                '}';
    }

    public int getSoundID() {
        return soundID;
    }

    public void setSoundID(int soundID) {
        this.soundID = soundID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
