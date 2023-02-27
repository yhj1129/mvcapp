package mvcapp.config;

public class ViewResolver {
    private static String prefix = "/WEB-INF/views";
    private static String suffix = ".jsp";

    public static String resolve(String viewName){ // /board/list가 들어오면
        return prefix + viewName + suffix; ///WEB-INF/views/board/list.jsp
    }
}
