package javaio;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.Iterator;

public class FileUtils {

    public void printNioFileDetails(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        Path path1 = FileSystems.getDefault().getPath(fileName);
        Path path2 = Paths.get(System.getProperty("user.dir"), fileName);

        Path absolutePath = path.toAbsolutePath();

        System.out.println("File name is " + path.getFileName());
        System.out.println("Root dir is " + absolutePath.getRoot());
        System.out.println("Absolute path is " + absolutePath);
        System.out.println("Parent dir from absolute path is " + absolutePath.getParent());
        System.out.println("Name count " + absolutePath.getNameCount());

        System.out.println("Sub-path is " + absolutePath.subpath(0, 3));
        Path path3 = Paths.get("../../");
        System.out.println("Real path " + path3.toRealPath());

        System.out.println("File exist " + Files.exists(path));
        System.out.println("File does not exist " + Files.notExists(path));
        System.out.println("Is readable " + Files.isReadable(path));
        System.out.println("Is writable " + Files.isWritable(path));
        System.out.println("Is executable " + Files.isExecutable(path));

        System.out.println("Is the same files " + Files.isSameFile(path, path1));

        Path parentPath = absolutePath.getParent();
        Path filesPath = parentPath.resolve("files");

        System.out.println(filesPath);

        if (Files.notExists(filesPath)) {
            Files.createDirectory(filesPath);
        }
        Files.move(absolutePath, filesPath.resolve(path), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(filesPath.resolve(path));
        Files.delete(filesPath);
    }

    public boolean deleteFileByName(String fileName) throws IOException {
        Path filePath = Paths.get(fileName);
        if (Files.exists(filePath.toAbsolutePath())) {
            Files.delete(filePath);
        }
        return Files.notExists(filePath.toAbsolutePath());
    }

    public void processDir() throws IOException {
        Path dir = Paths.get("temp");
        if (Files.notExists(dir)) {
            Files.createDirectory(dir);
        }

        Files.createDirectories(Paths.get(dir + "/a/b/c"));
        Files.createTempDirectory(dir, "tmp");

        Iterable<Path> rootDirectories = FileSystems.getDefault().getRootDirectories();
        for (Path rootDirectory : rootDirectories) {
            System.out.println(rootDirectory);
        }

        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return Files.isDirectory(entry);
            }
        };

        try (DirectoryStream<Path> paths = Files.newDirectoryStream(dir, filter)) {
            for (Path p :
                    paths) {
                System.out.println(p);
            }
        }
    }
}
