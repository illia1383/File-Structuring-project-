/**
 * Author @Illia Lotfalain
 * Date: 03/30/2023
 */




import java.util.Iterator;
public class FileStructure {
    private NLNode<FileObject> root; // Reference to the Root note

    /**
     * This method uses a recursive method to make the fileSturcture
     * @param fileObjectName
     * @throws FileObjectException
     */
    public FileStructure(String fileObjectName) throws FileObjectException{
        FileObject fileObject = new FileObject(fileObjectName);//This will throw exception if it cant make the object
       root = new NLNode<>(fileObject,null); //This is null , becauce at first it is the root so there is no parent node
       recursiveFileStructure(root);

    }

    /**
     * This is the recursive method that is used in FileStructure
     * @param r
     */
    private void recursiveFileStructure(NLNode<FileObject> r ) {
        // assuming r is a directory
        FileObject fileObject = r.getData(); //letting fileObject be the fileObject stored in r
        //Base case
        if (fileObject.isFile()) {
            return;
        } else {//recursive case
            Iterator<FileObject> iterator = fileObject.directoryFiles(); // to get an iterator containing all the object of type fileobject
            Boolean hasnext = iterator.hasNext(); //Making a boolean condition for my while loop
            while (hasnext) { //While loop to go through the iterator of fileobject
                FileObject f = iterator.next();//making f = next to keep going through the iterator
                NLNode<FileObject> n = new NLNode<>(f, null);
                r.addChild(n);
                n.setParent(r);
                recursiveFileStructure(n);
                hasnext = iterator.hasNext();

            }
        }
    }


    /**
     * Getter
     * @return Root
     */
    public NLNode<FileObject> getRoot(){
        return root; //Returning root
    }


    /**
     * Uses a recursive method to return a string iterator with the names of all the files of the specified type
     * @param type
     * @return
     */
    public Iterator<String> filesOfType(String type){
        ListNodes<String> fileList = new ListNodes<>(); //making an empty containor
        recursiveFilesOfType(root,type,fileList);
        return fileList.getList(); // converting the list to an iterator and returning it
    }

    /**
     * The recursive method that is used by filesOfType
     * @param r
     * @param type
     * @param fileList
     */
    private void recursiveFilesOfType(NLNode<FileObject> r , String type, ListNodes<String> fileList ){
        //Base Case
        if (r.getData().isFile()){
            String fileName = r.getData().getLongName(); //This gets the absolute path to the file represented by fileName
            if(fileName.endsWith(type)){
                fileList.add(fileName);
            //Recursive Case
            }
        }else{
            // r represents a folder
            Iterator<NLNode<FileObject>> children = r.getChildren(); //Getting a iterator by using r.getchildren
            boolean hasNextChild = children.hasNext(); //Using a boolean for my while loop conditional
            while(hasNextChild){
                NLNode<FileObject> n = children.next();
                recursiveFilesOfType(n ,type , fileList); // This is done to traverse the entire tree and fine all files of the specified type.
                hasNextChild = children.hasNext();
            }
        }
    }

    /**
     * Find File uses a recursive method that finds the specific file by name
     * @param name
     * @return
     */
    public String findFile(String name){
        ListNodes<String> fileList = new ListNodes<>();
        recursiveOfFindFile(root,name,fileList); // Recursively search for the file with the given name
        if(!fileList.getList().hasNext()){
            return ""; //If the list is empty
        }else{
            return fileList.getList().next();
        }
    }

    /**
     * The recursive method that  findFile uses
     * @param r
     * @param name
     * @param fileList
     */
    private void recursiveOfFindFile(NLNode<FileObject> r , String name, ListNodes<String> fileList){
        //Base case
        if(r.getData().isFile()){//Checks for it being a file
            String filename = r.getData().getLongName(); // since its a file filename is the longname
            if(filename.endsWith(name)){ //to find the specific file
                fileList.add(filename);// if it is the fiile were looking for add it ot the list
            }
            //Recursive case
        }else{
            Iterator<NLNode<FileObject>> children = r.getChildren();
            boolean hasNextChild = children.hasNext(); //Making a boolean to use for my conditional in the while loop
            while(hasNextChild){
                NLNode<FileObject> child = children.next();
                recursiveOfFindFile(child,name,fileList);
                hasNextChild = children.hasNext(); //updating hasNextChild to keep goig throuigh the itterator

            }
        }
    }
}
