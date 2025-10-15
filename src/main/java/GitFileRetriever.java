import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;
import org.eclipse.jgit.revwalk.RevTree;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class GitFileRetriever {
    public static String getFileContentAtCommit(String repoPath, String filePath, String commitId) throws Exception {
        Repository repo = new FileRepositoryBuilder()
                .setGitDir(new File(repoPath + "/.git"))
                .build();
        try (Git git = new Git(repo)) {
            RevCommit commit = git.log().add(repo.resolve(commitId)).setMaxCount(1).call().iterator().next();
            RevTree tree = commit.getTree();
            try (TreeWalk treeWalk = new TreeWalk(repo)) {
                treeWalk.addTree(tree);
                treeWalk.setRecursive(true);
                treeWalk.setFilter(PathFilter.create(filePath));
                if (!treeWalk.next()) return null;
                try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                    repo.open(treeWalk.getObjectId(0)).copyTo(out);
                    return out.toString();
                }
            }
        }
    }
}
