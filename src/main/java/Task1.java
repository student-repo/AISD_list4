import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class Task1 {

    public static void main(String[] args){
        BST bst = new BST();


        try {
            try (Stream<String> stream = Files.lines(Paths.get("./src/main/java/config1"))) {
                stream.forEach(item -> bst.handleIntput(bst, item));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



//        try {
//            try (Stream<String> stream = Files.lines(Paths.get("./src/main/java/config2"))) {
//                stream.forEach(item -> bst.handleIntput(bst, item));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
