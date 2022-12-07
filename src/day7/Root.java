package day7;

public class Root extends DirectoryNode {

    public Root() {
        name = "/";
    }

    @Override
    String getType() {
        return "Root";
    }
}
