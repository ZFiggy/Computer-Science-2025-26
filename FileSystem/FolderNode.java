import java.util.ArrayList;
import java.util.List;

/**
 * Represents a directory in the file system tree.
 * A directory can contain other directories and files as children.
 */
public class FolderNode extends FileSystemNode {

    private List<FileSystemNode> children;

    public FolderNode(String name, FolderNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }


    @Override
    public boolean isFolder() {
        if (this instanceof FolderNode) {
            return true;
        }
        return false;
    }

    /**
     * Returns a list view of the children contained directly inside this directory.
     * Modifying the returned list is not required to be supported.
     */
    public List<FileSystemNode> getChildren() {
        List<FileSystemNode> childrenCopy = new ArrayList<FileSystemNode>(children.size());
        for (FileSystemNode fileSystemNode : children) {
            childrenCopy.add(fileSystemNode);
        }
        return childrenCopy;
    }

    /**
     * Searches the children of this directory for a node whose name matches the input.
     * Only direct children are considered, not deeper descendants.
     */
    public FileSystemNode getChildByName(String childName) {
        for (FileSystemNode fileSystemNode : children) {
            if (fileSystemNode.getName().equals(childName)) {
                return fileSystemNode;
            }
        }
        return null;
    }

    /**
     * Creates a new file directly inside this directory with the given name and size.
     * If a child with the same name already exists, no file is created and false is returned.
     * Otherwise the new file is added and true is returned.
     */
    public boolean addFile(String fileName, int size) {
        if (getChildByName(fileName) != null ) {
            return false;
        }
        children.add(new FileNode(fileName, getParent(), size));
        return true;
    }

    /**
     * Creates a new subdirectory directly inside this directory with the given name.
     * If a child with the same name already exists, no folder is created and false is returned.
     * Otherwise the new folder is added and true is returned.
     */
    public boolean addFolder(String folderName) {
        if (getChildByName(folderName) != null ) {
            return false;
        }
        children.add(new FolderNode(folderName, getParent()));
        return true;
    }

    /**
     * Searches this directory and all of its descendants for nodes whose name matches the input.
     * When a match is found, its full path can be printed by the caller using toString().
     */
    public boolean containsNameRecursive(String searchName) {
        if (this.getName().equals(searchName)) {
            this.toString();
            return true;
        } 
        for (FileSystemNode fileSystemNode : children) {
            if (fileSystemNode.isFolder()) {
                FolderNode folder = (FolderNode) fileSystemNode;
                folder.containsNameRecursive(searchName);
            } else {
                if (fileSystemNode.getName().equals(searchName)) {
                    fileSystemNode.toString();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getHeight() {
        List<FileSystemNode> kids = this.getChildren();
        int greatestHeight = 0;
        if (!this.isFolder() || kids.size() == 0) {
            return 0;
        } else if (this.isFolder()) {
            for (int i = 0; i < kids.size(); i++) {
                int thisKidHeight = kids.get(i).getHeight();
                if (thisKidHeight > greatestHeight) {
                    greatestHeight = thisKidHeight;
                }
            }
        }
        return greatestHeight + 1;
    }

    @Override
    public int getSize() {
        List<FileSystemNode> kids = this.getChildren();
        int size = 0;
        if (!this.isFolder()) {
            return this.getSize();
        } else if (kids.size() == 0) {
            return 0;
        } else if (this.isFolder()) {
            for (int i = 0; i < kids.size(); i++) {
                size += kids.get(i).getSize();
            }
        }
        return size;
    }

    @Override
    public int getTotalNodeCount() {
        List<FileSystemNode> kids = this.getChildren();
        int nodeCount = 0;
        if (!this.isFolder() || kids.size() == 0) {
            return 1;
        } else if (this.isFolder()) {
            for (int i = 0; i < kids.size(); i++) {
                nodeCount += kids.get(i).getTotalNodeCount();
            }
        }
        return nodeCount + 1;
    }
}
