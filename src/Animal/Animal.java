package Animal;

public abstract class Animal {
        String petName;
        boolean fed=false;
        boolean walked=false;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    String Type;

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public boolean isFed() {
        return fed;
    }

    public void setFed(boolean fed) {
        this.fed = fed;
    }

    public boolean isWalked() {
        return walked;
    }

    public void setWalked(boolean walked) {
        this.walked = walked;
    }
}
