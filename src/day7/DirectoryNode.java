package day7;

public abstract class DirectoryNode {
    String name;
    Long size;

    public String getName() {
        return name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    abstract String getType();

    @Override
    public String toString() {
        return getType() + "{" +
                "name='" + name + '\'' +
                ", size=" + size +
                "}\n";
    }
}
