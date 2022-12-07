package day7;

public class File extends DirectoryNode {

    public File(String name, Long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    String getType() {
        return "File";
    }
}
