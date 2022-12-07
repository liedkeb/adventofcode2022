package day7;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tree {
    private Node root;

    public Tree(DirectoryNode rootData) {
        root = new Node(rootData);
        root.children = new ArrayList<>();
    }

    public Node getRoot() {
        return root;
    }

    public void calculate() {
        root.getSize();
    }

    public Long getSizeUpto() {
        return root.getSizeUpto();
    }

    public Long getSmallestDirAbove( Long value) {
        return root.getSmallestDirAbove( value, root.getData().getSize());
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }

    public static class Node {
        private DirectoryNode data;
        private Node parent;
        private List<Node> children;

        public Node(DirectoryNode data) {
            this.data = data;
        }

        public DirectoryNode getData() {
            return data;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public List<Node> getChildren() {
            return children;
        }

        public Node findChildByName(String name) {
            return children.stream().filter(n -> n.data.getName().equals(name)).findFirst().get();
        }

        public void addChild(Node node) {
            if (children == null) {
                children = new ArrayList<>();
            }
            node.setParent(this);
            children.add(node);
        }

        String getString(int level) {
            StringBuilder result = new StringBuilder(data.toString());
            if (children != null) {
                for (Node node : children) {
                    result.append("  ".repeat(Math.max(0, level)));
                    result.append(node.getString(level + 1));
                }
            }
            return result.toString();
        }

        Long getSize() {
            Long size = data.getSize() == null ? 0 : data.getSize();
            if (children != null) {
                for (Node node : children) {
                    size += node.getSize();
                }
            }
            data.setSize(size);
            return size;
        }

        Long getSizeUpto() {
            Long size = 0L;
            if (data.getSize() <= 100000L && data instanceof Directory) {
                size += data.getSize();
            }
            if (children != null) {
                for (Node node : children) {
                    if (node.data instanceof Directory) {
                        size += node.getSizeUpto();
                    }
                }
            }
            return size;
        }


        @Override
        public String toString() {
            return getString(0);
        }

        public Long getSmallestDirAbove(Long minValue, Long currentSmallest) {
            if (data.getSize() >= minValue && data.getSize() < currentSmallest && data instanceof Directory) {
                currentSmallest = data.getSize();
            }
            if (children != null) {
                for (Node node : children) {
                    if (node.data instanceof Directory) {
                        currentSmallest = node.getSmallestDirAbove( minValue, currentSmallest );
                    }
                }
            }
            return currentSmallest;
        }
    }
}
