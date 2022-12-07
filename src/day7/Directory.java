package day7;

public class Directory extends DirectoryNode {

    public Directory(String name){
        this.name = name;
    }

    @Override
    String getType() {
        return "Dir";
    }
}
