import org.gitlab4j.api.GitLabApi;

public class GitLabClient {
    public static void main(String[] args) {
        String hostUrl = "https://github.com/vart54321-tech/translate-test.git";
        String personalAccessToken = "ВАШ_ТОКЕН";

        GitLabApi gitLabApi = new GitLabApi(hostUrl, personalAccessToken);

        try {
            gitLabApi.getProjectApi().getProjects().forEach(translate -> {
                System.out.println(translate.getName());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
