/*
 * Copyright (C) 2023 bhagc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package labs.fh.data;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.time.Instant;
import java.util.Comparator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

/**
 *
 * @author bhagc
 */
public class FileSystem {

    public void fileSystemPath() throws IOException {
        Path someFile = Path.of("E:/", "RCAT", "some.txt");
        Path justSomeFile = someFile.getFileName();
        Path docsFolder = someFile.getParent();
        Path currentFolder = Path.of(".");
        Path acmeFile = docsFolder.resolve("../Nptel/Bhagchand.jpg");
        Path otherFile = someFile.resolveSibling("other.txt");
        Path normalisedAcmeFile = acmeFile.normalize();
        Path verifiedPath = acmeFile.toRealPath();
        Path betweenSomeAndAcme = someFile.relativize(acmeFile);
        System.out.println(someFile);
        System.out.println(justSomeFile);
        System.out.println(docsFolder);
        System.out.println(currentFolder);
        System.out.println(acmeFile);
        System.out.println(otherFile);
        System.out.println(normalisedAcmeFile);
        System.out.println(verifiedPath);
        System.out.println(betweenSomeAndAcme);

    }

    public void fileNavigation() throws IOException {
        Path rcat = Path.of("E:/", "RCAT");
        Path p1 = Path.of("E:/", "RCAT", "some.txt");
        for (int i = 0; i < p1.getNameCount(); i++) {
            System.out.println(p1.getName(i));
        }

        Files.list(rcat).forEach(p -> System.out.println(p));
        Files.walk(rcat).map(p -> p.toString())
                .filter(s -> s.endsWith("txt"))
                .forEach(p -> System.out.println(p));

        System.out.println("p1");

        Path p2 = Path.of("E:/", "Nptel", "s.txt");
        // Files.createSymbolicLink(p2, p1);

        System.out.println("p2");

        // Path p3=Files.readSymbolicLink(p2);
        // System.out.println(p3);
        System.out.println("p3");
    }

    public void fileAttributes() throws IOException {
        Path p1 = Path.of("E:/", "RCAT", "some.txt");
        Path p2 = Path.of("E:/RCAT/some.txt");
        Path p3 = Path.of("E:/Nptel/s.txt");
        System.out.println(Files.isDirectory(p1));
        System.out.println(Files.isExecutable(p1));
        System.out.println(Files.isHidden(p1));
        System.out.println(Files.isReadable(p1));
        System.out.println(Files.isWritable(p1));
        System.out.println(Files.isRegularFile(p1));
        System.out.println(Files.isSymbolicLink(p3));
        System.out.println(Files.isSameFile(p1, p2));
        System.out.println(Files.probeContentType(p1));
        PosixFileAttributes fa = Files.readAttributes(p1, PosixFileAttributes.class);
        long size = fa.size();
        FileTime t1 = fa.creationTime();
        FileTime t2 = fa.lastModifiedTime();
        FileTime t3 = fa.lastAccessTime();
        UserPrincipal user = fa.owner();
        GroupPrincipal group = fa.group();
        Set<PosixFilePermission> permissions = fa.permissions();
        String t = PosixFilePermissions.toString(permissions);
        System.out.println(size);
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
        System.out.println(user);
        System.out.println(group);
        permissions.forEach(s -> System.out.println(s.name()));
        System.out.println(t);

    }

    public void modifyFileAttributes() throws IOException {
        Path p1 = Path.of("E:/", "RCAT", "some.txt");
        Files.setLastModifiedTime(p1, FileTime.from(Instant.now()));
        Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rw-rw-r--");
        // Files.setPosixFilePermissions(p1, permissions);
        java.nio.file.FileSystem fs = p1.getFileSystem();
        UserPrincipalLookupService uls = fs.getUserPrincipalLookupService();
        // UserPrincipal user=uls.lookupPrincipalByName("user");
//        GroupPrincipal group=uls.lookupPrincipalByGroupName("staff");
        // Files.setOwner(p1, user);
        //  Files.getFileAttributeView(p1, PosixFileAttributeView.class).setGroup(group);
        System.out.println(Files.getLastModifiedTime(p1));

    }

    public void fileOperations() throws IOException {
        Path source = Path.of("E:/RCAT");
        Path backup = Path.of("E:/Backup/");
        if (!Files.exists(backup)) {
            Files.createDirectory(backup);

        }
        Path docs = backup.resolve("./docs").normalize();
        System.out.println(docs);
        if (!Files.exists(docs)) {
            Files.createDirectory(docs);
        }
        Path readme = backup.resolve("./readme.txt").normalize();
        if (!Files.exists(readme)) {
            Files.createFile(readme);
        }
        Files.writeString(readme, "Backup time: " + Instant.now());
        Files.lines(readme, Charset.forName("UTF-8"))
                .forEach(line -> System.out.println(line));

        Path p1 = Files.createTempDirectory("TEMP");
        Path p2 = Files.createTempFile(p1, "TEMP", ".tmp");
        System.out.println(p1 + " " + p2);
        Files.deleteIfExists(p2);
        Files.deleteIfExists(p1);
    }

    public void fileCopyAndMove() {
        Path source = Path.of("E:/RCAT");
        Path backup = Path.of("E:/Backup/docs");
        try {
            Files.list(source).filter(file -> !Files.isDirectory(file))
                    .forEach(file -> {
                        System.out.println(file.getFileName());
                        try {

                            Path cp = Files.copy(file, backup.resolve(file.getFileName().toString()),
                                    StandardCopyOption.COPY_ATTRIBUTES,
                                    StandardCopyOption.REPLACE_EXISTING);
                            System.out.println(cp);
                        } catch (IOException ex) {
                            Logger.getLogger(FileSystem.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

            Files.list(backup).forEach(
                    file -> {
                try {
                    Files.move(file, backup.resolve("../" + file.getFileName()),
                            StandardCopyOption.COPY_ATTRIBUTES,
                            StandardCopyOption.REPLACE_EXISTING,
                            StandardCopyOption.ATOMIC_MOVE);
                } catch (IOException ex) {
                    Logger.getLogger(FileSystem.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }
            );

        } catch (IOException ex) {
            Logger.getLogger(FileSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletePath() {
        Path backup = Path.of("E:/Backup");
        try {
            Files.walk(backup)
                    .sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            System.out.println(path);
                            Files.delete(path);
                        } catch (IOException ex) {
                            Logger.getLogger(FileSystem.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
        } catch (IOException ex) {
            Logger.getLogger(FileSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void zipHandling(){
       
        Path path=Path.of("E:/Backup");
        Path source=Path.of("E:/Backup/docs");
        Path zip=Path.of(path.resolve("./joe.zip").toString());
        if(!Files.exists(zip)){
            try {
                Files.createFile(zip);
                System.out.println("File Created Successfully");
            } catch (IOException ex) {
                Logger.getLogger(FileSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try(ZipOutputStream out=new ZipOutputStream(Files.newOutputStream(zip))){
            out.setLevel(Deflater.DEFAULT_COMPRESSION);
            Files.walk(path).filter(p->!Files.isDirectory(p))
                    .forEach(p->{
                    ZipEntry zipEntry=new ZipEntry(source.relativize(p).toString());
                try {
                    out.putNextEntry(zipEntry);
                    out.write(Files.readAllBytes(p));
                    out.closeEntry();
                } catch (IOException ex) {
                    Logger.getLogger(FileSystem.class.getName()).log(Level.SEVERE, null, ex);
                }
                    });
            
        } catch (IOException ex) {
            Logger.getLogger(FileSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void httpHandle() throws IOException, InterruptedException{
        Path path=Path.of("E:/Backup/docs/index.html");
        URI uri=URI.create("http://openjdk.java.net");
        HttpRequest req=HttpRequest.newBuilder(uri).GET().build();
        HttpClient client=HttpClient.newHttpClient();
        HttpResponse<Path> res=client.send(req, HttpResponse.BodyHandlers.ofFile(path));
        System.out.println(res.body());
    }
}
